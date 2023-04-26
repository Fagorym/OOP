package ru.nsu.fit.oop.veber.model;

import ru.nsu.fit.oop.veber.utils.Direction;

import java.util.ArrayDeque;
import java.util.Deque;

public class Snake {

    Deque<SnakeBlock> body;
    private Direction direction;

    private SnakeBlock headBlock;

    private SnakeBlock tailBlock;

    public Snake(int x, int y) {
        this.body = new ArrayDeque<>();
        this.headBlock = new SnakeBlock(x, y);
        this.tailBlock = new SnakeBlock(x - 1, y);
        this.direction = Direction.RIGHT;
    }

    public void setDirection(Direction direction) {
        if (direction != null && this.direction.getOpposite() != direction) {
            this.direction = direction;
        }
    }


    public void move() {
        tailBlock.setY(headBlock.getY());
        tailBlock.setX(headBlock.getX());
        {
            switch (direction) {
                case RIGHT -> tailBlock.setX(headBlock.getX() + 1);
                case LEFT -> tailBlock.setX(headBlock.getX() - 1);
                case DOWN -> tailBlock.setY(headBlock.getY() + 1);
                case UP -> tailBlock.setY(headBlock.getY() - 1);
            }
        }
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
        SnakeBlock newTail = switch (direction) {
            case DOWN -> new SnakeBlock(headBlock.getX(), headBlock.getY() - 1);
            case RIGHT -> new SnakeBlock(headBlock.getX() + 1, headBlock.getY());
            case LEFT -> new SnakeBlock(headBlock.getX() - 1, headBlock.getY());
            case UP -> new SnakeBlock(headBlock.getX(), headBlock.getY() + 1);
        };
        body.addLast(tailBlock);
        this.tailBlock = newTail;
        return newTail;
    }
}
