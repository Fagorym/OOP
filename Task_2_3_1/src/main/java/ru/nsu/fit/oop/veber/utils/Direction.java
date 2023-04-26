package ru.nsu.fit.oop.veber.utils;

import ru.nsu.fit.oop.veber.model.SnakeBlock;

import java.util.function.BiConsumer;
import java.util.function.Function;

public enum Direction {
    LEFT(
            (head, tail) -> tail.setX(head.getX() - 1),
            (head) -> new SnakeBlock(head.getX() - 1, head.getY())
    ),
    RIGHT(
            (head, tail) -> tail.setX(head.getX() + 1),
            (head) -> new SnakeBlock(head.getX() + 1, head.getY())
    ),
    DOWN(
            (head, tail) -> tail.setY(head.getY() + 1),
            (head) -> new SnakeBlock(head.getX(), head.getY() - 1)
    ),
    UP(
            (head, tail) -> tail.setY(head.getY() - 1),
            (head) -> new SnakeBlock(head.getX(), head.getY() + 1)
    );

    private final BiConsumer<SnakeBlock, SnakeBlock> moveFunction;
    private final Function<SnakeBlock, SnakeBlock> generateFunction;

    Direction(BiConsumer<SnakeBlock, SnakeBlock> move, Function<SnakeBlock, SnakeBlock> generate) {
        this.moveFunction = move;
        this.generateFunction = generate;
    }

    public Direction getOpposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }

    public BiConsumer<SnakeBlock, SnakeBlock> getMove() {
        return moveFunction;
    }

    public Function<SnakeBlock, SnakeBlock> getGenerateFunction() {
        return generateFunction;
    }
}
