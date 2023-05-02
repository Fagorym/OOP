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

    static {
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
        UP.opposite = DOWN;
        DOWN.opposite = UP;
    }

    private final BiConsumer<SnakeBlock, SnakeBlock> moveFunction;
    private final Function<SnakeBlock, SnakeBlock> generateFunction;
    private Direction opposite;

    Direction(BiConsumer<SnakeBlock, SnakeBlock> move, Function<SnakeBlock, SnakeBlock> generate) {
        this.moveFunction = move;
        this.generateFunction = generate;
    }

    public Direction getOpposite() {
        return opposite;
    }

    public BiConsumer<SnakeBlock, SnakeBlock> getMove() {
        return moveFunction;
    }

    public Function<SnakeBlock, SnakeBlock> getGenerateFunction() {
        return generateFunction;
    }
}
