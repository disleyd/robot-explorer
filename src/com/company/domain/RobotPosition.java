package com.company.domain;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class RobotPosition {
    private final Direction direction;
    private final Coordinates coordinates;

    public RobotPosition(Coordinates coordinates, Direction direction) {
        this.coordinates = coordinates;
        this.direction = direction;
    }

    public RobotPosition(int x, int y, Direction direction) {
        this(new Coordinates(x, y), direction);
    }

    public Coordinates coordinates() {
        return coordinates;
    }

    public Direction direction() {
        return direction;
    }

    //todo: added for development - replace
    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }

    //todo: added for development - replace
    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}
