package com.company.domain;

import static com.company.domain.Direction.*;

public class Robot {
    private final Coordinates coordinates;
    private final Instruction[] instructions;
    private Direction direction;

    public Robot(RobotInstructions robotInstructions) {
        coordinates = robotInstructions.position().coordinates();
        direction = robotInstructions.position().direction();
        instructions = robotInstructions.instructions();
    }

    public RobotPosition executeInstructions() {
        for (Instruction instruction : instructions) {
            switch (instruction) {
                case LEFT: turnLeft(); break;
                case RIGHT: turnRight(); break;
                case MOVE: move(); break;
            }
        }
        return new RobotPosition(coordinates, direction);
    }

    private void turnLeft() {
        switch (direction) {
            case NORTH: direction = WEST; break;
            case EAST: direction = NORTH; break;
            case SOUTH: direction = EAST; break;
            case WEST: direction = SOUTH; break;
        }
    }

    private void turnRight() {
        switch (direction) {
            case NORTH: direction = EAST; break;
            case EAST: direction = SOUTH; break;
            case SOUTH: direction = WEST; break;
            case WEST: direction = NORTH; break;
        }
    }

    private void move() {
        switch (direction) {
            case NORTH: coordinates.moveNorth(); break;
            case EAST: coordinates.moveEast(); break;
            case SOUTH: coordinates.moveSouth(); break;
            case WEST: coordinates.moveWest(); break;
        }
    }
}
