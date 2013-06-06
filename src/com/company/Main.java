package com.company;

import com.company.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static com.company.domain.Instruction.parseInstruction;

public class Main {
    private static final String COORDINATE_SEPARATOR = " ";

    static final String NUMBER_OF_ROBOTS_PROMPT = "Enter number of robots: ";
    static final String COORDINATES_OF_PLATEAU_PROMPT = "Enter size of plateau as co-ordinates: ";
    static final String ROBOT_STARTING_POSITION_PROMPT = "Enter starting position: ";
    static final String ROBOT_INSTRUCTIONS_PROMPT = "Enter instructions: ";
    static final String ROBOTS_FINAL_POSITION_MESSAGE = "Robots final co-ordinates and direction: %d %d %s";

    private BufferedReader inputStream;
    private PrintStream outputStream;

    //todo: move input/output responsibility to a separate class
    public Main(BufferedReader inputStream, PrintStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader((new InputStreamReader(System.in)));

        Main explorePlateau = new Main(in, System.out);

        explorePlateau.readNumberOfRobots();

        //todo: validate robots movements using size of plateau
        explorePlateau.readSizeOfPlateau();

        //todo: iterate over number of robots
        RobotInstructions instructions = explorePlateau.readRobotInstructions();
        RobotPosition robotsFinalPosition = explorePlateau.instructRobot(instructions);
        explorePlateau.displayRobotsFinalCoordinatesAndDirection(robotsFinalPosition);
    }

    public int readNumberOfRobots() throws IOException {
        outputStream.println(NUMBER_OF_ROBOTS_PROMPT);
        return Integer.parseInt(inputStream.readLine());
    }

    public Coordinates readSizeOfPlateau() throws IOException {
        outputStream.println(COORDINATES_OF_PLATEAU_PROMPT);
        String[] coordinatesOfPlateau = inputStream.readLine().split(COORDINATE_SEPARATOR);

        int xCoordinate = Integer.parseInt(coordinatesOfPlateau[0]);
        int yCoordinate = Integer.parseInt(coordinatesOfPlateau[1]);

        return new Coordinates(xCoordinate, yCoordinate);
    }

    public RobotPosition instructRobot(RobotInstructions robotInstructions) {
        return new Robot(robotInstructions).executeInstructions();
    }

    public RobotInstructions readRobotInstructions() throws IOException {
        RobotPosition robotPosition = readPosition();
        List<Instruction> instructions = readInstructions();

        return new RobotInstructions(robotPosition, instructions);
    }

    public void displayRobotsFinalCoordinatesAndDirection(RobotPosition position) {
        outputStream.printf(ROBOTS_FINAL_POSITION_MESSAGE,
                position.coordinates().x(), position.coordinates().y(), position.direction().directionName());
    }

    private List<Instruction> readInstructions() throws IOException {
        outputStream.println(ROBOT_INSTRUCTIONS_PROMPT);

        char[] instructionCharacters = inputStream.readLine().toCharArray();
        List<Instruction> instructions = new ArrayList<Instruction>();

        for (char instructionCharacter : instructionCharacters) {
            instructions.add(parseInstruction(String.valueOf(instructionCharacter)));
        }
        return instructions;
    }

    private RobotPosition readPosition() throws IOException {
        outputStream.println(ROBOT_STARTING_POSITION_PROMPT);
        String[] startingPositionOfRobot = inputStream.readLine().split(COORDINATE_SEPARATOR);

        return new RobotPosition(
                Integer.parseInt(startingPositionOfRobot[0]),
                Integer.parseInt(startingPositionOfRobot[1]),
                Direction.parseDirection(startingPositionOfRobot[2]));
    }
}
