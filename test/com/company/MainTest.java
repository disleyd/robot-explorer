package com.company;

import com.company.domain.Coordinates;
import com.company.domain.RobotInstructions;
import com.company.domain.RobotPosition;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static com.company.domain.Direction.NORTH;
import static com.company.domain.Direction.WEST;
import static com.company.domain.Instruction.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

//todo: add unhappy path to drive design of validation code
public class MainTest {
    private static final RobotPosition STARTING_POSITION = new RobotPosition(0, 0, NORTH);

    private BufferedReader inputStreamMock = mock(BufferedReader.class);
    private PrintStream outputStreamMock = mock(PrintStream.class);

    private Main main = new Main(inputStreamMock, outputStreamMock);

    @Test
    public void readTheNumberOfRobots() throws IOException {
        when(inputStreamMock.readLine()).thenReturn("10");

        assertThat(main.readNumberOfRobots(), is(10));

        verify(outputStreamMock).println(Main.NUMBER_OF_ROBOTS_PROMPT);
        verify(inputStreamMock).readLine();
    }

    @Test
    public void readTheSizeOfThePlateau() throws IOException {
        when(inputStreamMock.readLine()).thenReturn("5 5");
        Coordinates plateauSize = new Coordinates(5, 5);

        assertThat(main.readSizeOfPlateau(), is(plateauSize));

        verify(outputStreamMock).println(Main.COORDINATES_OF_PLATEAU_PROMPT);
        verify(inputStreamMock).readLine();
    }

    @Test
    public void readRobotInstructions() throws IOException {
        when(inputStreamMock.readLine()).thenReturn("0 0 N").thenReturn("LRM");
        RobotInstructions instructions = new RobotInstructions(STARTING_POSITION, LEFT, RIGHT, MOVE);

        assertThat(main.readRobotInstructions(), is(instructions));

        verify(outputStreamMock).println(Main.ROBOT_STARTING_POSITION_PROMPT);
        verify(outputStreamMock).println(Main.ROBOT_INSTRUCTIONS_PROMPT);
        verify(inputStreamMock, times(2)).readLine();
    }

    @Test
    public void createRobotAndGiveItInstructions() {
        RobotPosition finalPosition = new RobotPosition(0, 1, WEST);
        RobotInstructions robotInstructions = new RobotInstructions(STARTING_POSITION, MOVE, RIGHT, MOVE, LEFT, MOVE, LEFT, LEFT, MOVE, RIGHT, MOVE);

        assertThat(main.instructRobot(robotInstructions), is(finalPosition));
    }

    @Test
    public void printRobotsFinalCoordinatesAndDirection() {
        RobotPosition finalPosition = new RobotPosition(4, 3, WEST);

        main.displayRobotsFinalCoordinatesAndDirection(finalPosition);

        verify(outputStreamMock).printf(Main.ROBOTS_FINAL_POSITION_MESSAGE, 4, 3, "W");
    }
}
