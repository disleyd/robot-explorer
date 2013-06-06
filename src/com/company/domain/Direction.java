package com.company.domain;

public enum Direction {
    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

    private final String directionName;

    Direction(String directionName) {
        this.directionName = directionName;
    }

    public String directionName() {
        return directionName;
    }

    public static Direction parseDirection(String directionString) {
        for (Direction direction : Direction.values()) {

            if (directionString.equals(direction.directionName)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("There is no Direction for value: " + directionString);
    }
}
