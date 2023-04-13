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
        this.direction = Direction.right;
    }

    public int getX() {
        return headBlock.getX();
    }

    public int getY() {
        return headBlock.getY();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public void update() {
        {
            switch (direction) {
                case right -> tailBlock.setX(headBlock.getX() + 1);
                case left -> tailBlock.setX(headBlock.getX() - 1);
                case down -> tailBlock.setY(headBlock.getY() + 1);
                case up -> tailBlock.setY(headBlock.getY() - 1);
            }
        }
        this.headBlock = tailBlock;
        body.addLast(tailBlock);
        this.tailBlock = body.getLast();

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

    public void generateNewSnakeBlock() {
        SnakeBlock newTail = switch (direction) {
            case down -> new SnakeBlock(headBlock.getX(), headBlock.getY() - 1);
            case right -> new SnakeBlock(headBlock.getX() + 1, headBlock.getY());
            case left -> new SnakeBlock(headBlock.getX() - 1, headBlock.getY());
            case up -> new SnakeBlock(headBlock.getX(), headBlock.getY() + 1);
        };
        body.addLast(tailBlock);
        this.tailBlock = newTail;
    }
}
