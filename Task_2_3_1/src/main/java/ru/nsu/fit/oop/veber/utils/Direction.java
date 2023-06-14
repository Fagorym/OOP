package ru.nsu.fit.oop.veber.utils;

import ru.nsu.fit.oop.veber.model.SnakeBlock;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Snake direction class.
 */
public enum Direction {
    LEFT(
            (head, tail) -> tail.setX(head.getX() - 1),
            (head) -> new SnakeBlock(head.getX() - 1, head.getY(), head.getOwner())
    ),
    RIGHT(
            (head, tail) -> tail.setX(head.getX() + 1),
            (head) -> new SnakeBlock(head.getX() + 1, head.getY(), head.getOwner())
    ),
    DOWN(
            (head, tail) -> tail.setY(head.getY() + 1),
            (head) -> new SnakeBlock(head.getX(), head.getY() - 1, head.getOwner())
    ),
    UP(
            (head, tail) -> tail.setY(head.getY() - 1),
            (head) -> new SnakeBlock(head.getX(), head.getY() + 1, head.getOwner())
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

    /**
     * Default constructor of direction.
     *
     * @param move     function for snake moving.
     * @param generate function for generating new tail.
     */
    Direction(BiConsumer<SnakeBlock, SnakeBlock> move, Function<SnakeBlock, SnakeBlock> generate) {
        this.moveFunction = move;
        this.generateFunction = generate;
    }

    /**
     * Function for getting opposite direction;
     *
     * @return opposite direction.
     */
    public Direction getOpposite() {
        return opposite;
    }

    /**
     * Function for getting move function.
     *
     * @return move function.
     */
    public BiConsumer<SnakeBlock, SnakeBlock> getMove() {
        return moveFunction;
    }

    /**
     * Function for getting generate function.
     *
     * @return generate function.
     */
    public Function<SnakeBlock, SnakeBlock> getGenerateFunction() {
        return generateFunction;
    }
}
