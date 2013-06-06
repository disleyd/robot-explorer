package com.company.domain;

public enum Instruction {
    LEFT("L"), RIGHT("R"), MOVE("M");

    private String instructionName;

    Instruction(String instructionName) {
        this.instructionName = instructionName;
    }

    public static Instruction parseInstruction(String instructionString) {
        for (Instruction instruction : Instruction.values()) {

            if (instructionString.equals(instruction.instructionName)) {
                return instruction;
            }
        }
        throw new IllegalArgumentException("There is no Instruction for value: " + instructionString);
    }
}
