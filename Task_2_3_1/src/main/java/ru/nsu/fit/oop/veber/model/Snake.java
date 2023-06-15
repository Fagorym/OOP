package ru.nsu.fit.oop.veber.model;

import ru.nsu.fit.oop.veber.utils.Direction;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class Snake {

    protected Direction direction;
    protected boolean isNotDead = true;
    Deque<SnakeBlock> body;
    private SnakeBlock headBlock;

    private SnakeBlock tailBlock;


    public Snake(int x, int y) {
        this.body = new ArrayDeque<>();
        this.headBlock = new SnakeBlock(x, y, this);
        this.tailBlock = new SnakeBlock(x - 1, y, this);
        this.direction = Direction.RIGHT;
    }

    static public Snake generate(Box box, CollisionChecker collisionChecker) {
        Snake snake;
        do {
            Random random = new Random();
            int x = random.nextInt(box.getLength() - 2) + 1;
            int y = random.nextInt(box.getHeight() - 2) + 1;
            snake = new Snake(x, y);
        } while (!collisionChecker.addObject(snake.getHeadBlock()));
        return snake;
    }

    public void setDirection(Direction direction) {
        if (direction != null && this.direction.getOpposite() != direction) {
            this.direction = direction;
        }
    }

    public void move() {
        tailBlock.setY(headBlock.getY());
        tailBlock.setX(headBlock.getX());
        direction.getMove().accept(headBlock, tailBlock);
        body.addFirst(headBlock);
        this.headBlock = tailBlock;
        this.tailBlock = body.removeLast();

    }

    public Deque<SnakeBlock> getBody() {
        return body;
    }

    public SnakeBlock getHeadBlock() {
        return headBlock;
    }

    public SnakeBlock getTailBlock() {
        return tailBlock;
    }

    public SnakeBlock generateNewSnakeBlock() {
        SnakeBlock newTail = direction.getGenerateFunction().apply(headBlock);
        body.addLast(tailBlock);
        this.tailBlock = newTail;
        return newTail;
    }

    protected int killBlocksAfter(SnakeBlock deadBlock) {
        if (tailBlock == deadBlock) {
            if (body.isEmpty()) {
                isNotDead = false;
                return 2;
            }
            tailBlock = body.removeLast();
            return body.size() + 2;
        } else {
            Deque<SnakeBlock> newBody = new ArrayDeque<>();
            while (!body.isEmpty() && body.peekFirst() != deadBlock) {
                newBody.addLast(body.removeFirst());
            }
            int killed = body.size() - newBody.size();
            body = newBody;
            return killed;
        }
    }
}
