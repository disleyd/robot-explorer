package com.company.domain;

import java.util.List;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class RobotInstructions {
    private final RobotPosition position;
    private final Instruction[] instructions;

    public RobotInstructions(RobotPosition position, Instruction... instructions) {
        this.position = position;
        this.instructions = instructions;
    }

    public RobotInstructions(RobotPosition robotPosition, List<Instruction> instructions) {
        this(robotPosition, instructions.toArray(new Instruction[instructions.size()]));
    }

    public RobotPosition position() {
        return position;
    }

    public Instruction[] instructions() {
        return instructions;
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
