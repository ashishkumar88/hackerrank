package com.hackerrank.competition.godaddy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexagonalWars {

    enum Type {
	Roman, Persian, A
    }

    public static void main(String[] args) {
	final HexagonalWars app = new HexagonalWars();
	app.readInputs();
	app.checkWinningCondition();
    }

    private int numberOfGrids;

    private Type[][] occupancy;
    private Type[][] visitedDFS;
    private Type[][] visitedWFS;

    private boolean checkPersianWinningStatus() {
	for (int count = 0; count < numberOfGrids; count++) {
	    if (occupancy[0][count] == Type.Persian) {
		// do a walk
		final int rowNumber = 0;
		final int columnNumber = count;
		return doDFS(rowNumber, columnNumber);
	    }
	}
	return false;
    }

    private boolean checkRomanWinningStatus() {
	for (int count = 0; count < numberOfGrids; count++) {
	    if (occupancy[count][0] == Type.Roman) {
		// do a walk
		final int rowNumber = count;
		final int columnNumber = 0;
		return doWFS(rowNumber, columnNumber);
	    }
	}
	return false;
    }

    private void checkWinningCondition() {
	if (checkRomanWinningStatus()) {
	    System.out.println("ROMANS");
	} else if (checkPersianWinningStatus()) {
	    System.out.println("PERSIANS");
	} else {
	    System.out.println("NEITHER");
	}
    }

    public boolean doDFS(int row, int column) {
	if ((row + 1) == numberOfGrids) {
	    return true;
	}
	if (!((row > numberOfGrids) || (column > numberOfGrids))) {
	    visitedDFS[row][column] = Type.Persian;
	}
	try {
	    if ((occupancy[row + 1][column - 1] == Type.Persian) && (visitedDFS[row + 1][column - 1] != Type.Persian)) {
		return doDFS(row + 1, column - 1);
	    }
	} catch (final ArrayIndexOutOfBoundsException e) {
	    // do nothing
	}
	try {
	    if ((occupancy[row + 1][column] == Type.Persian) && (visitedDFS[row + 1][column] != Type.Persian)) {
		return doDFS(row + 1, column);
	    }
	} catch (final ArrayIndexOutOfBoundsException e) {
	    // do nothing
	}
	try {
	    if ((occupancy[row][column - 1] == Type.Persian) && (visitedDFS[row][column - 1] != Type.Persian)) {
		return doDFS(row, column - 1);
	    }
	} catch (final ArrayIndexOutOfBoundsException e) {
	    // do nothing
	}
	try {
	    if ((occupancy[row][column + 1] == Type.Persian) && (visitedDFS[row][column + 1] != Type.Persian)) {
		return doDFS(row, column + 1);
	    }
	} catch (final ArrayIndexOutOfBoundsException e) {
	    // do nothing
	}
	try {
	    if ((occupancy[row - 1][column] == Type.Persian) && (visitedDFS[row - 1][column] != Type.Persian)) {
		return doDFS(row - 1, column);
	    }
	} catch (final ArrayIndexOutOfBoundsException e) {
	    // do nothing
	}
	try {
	    if ((occupancy[row - 1][column + 1] == Type.Persian) && (visitedDFS[row - 1][column + 1] != Type.Persian)) {
		return doDFS(row - 1, column + 1);
	    }
	} catch (final ArrayIndexOutOfBoundsException e) {
	    // do nothing
	}
	return false;
    }

    private boolean doWFS(int rowNumber, int columnNumber) {
	if ((columnNumber + 1) == numberOfGrids) {
	    return true;
	}
	if (!((rowNumber > numberOfGrids) || (columnNumber > numberOfGrids))) {
	    visitedWFS[rowNumber][columnNumber] = Type.Roman;
	}
	try {
	    if ((occupancy[rowNumber - 1][columnNumber + 1] == Type.Roman)
		    && (visitedWFS[rowNumber - 1][columnNumber + 1] != Type.Roman)) {
		return doWFS(rowNumber - 1, columnNumber + 1);
	    }
	} catch (final ArrayIndexOutOfBoundsException e) {
	    // do nothing
	}
	try {
	    if ((occupancy[rowNumber][columnNumber + 1] == Type.Roman)
		    && (visitedWFS[rowNumber][columnNumber + 1] != Type.Roman)) {
		return doWFS(rowNumber, columnNumber + 1);
	    }
	} catch (final ArrayIndexOutOfBoundsException e) {
	    // do nothing
	}
	try {
	    if ((occupancy[rowNumber + 1][columnNumber] == Type.Roman)
		    && (visitedWFS[rowNumber + 1][columnNumber] != Type.Roman)) {
		return doWFS(rowNumber + 1, columnNumber);
	    }
	} catch (final ArrayIndexOutOfBoundsException e) {
	    // do nothing
	}

	try {
	    if ((occupancy[rowNumber - 1][columnNumber] == Type.Roman)
		    && (visitedWFS[rowNumber - 1][columnNumber] != Type.Roman)) {
		return doWFS(rowNumber - 1, columnNumber);
	    }
	} catch (final ArrayIndexOutOfBoundsException e) {
	    // do nothing
	}

	try {
	    if ((occupancy[rowNumber + 1][columnNumber - 1] == Type.Roman)
		    && (visitedWFS[rowNumber + 1][columnNumber - 1] != Type.Roman)) {
		return doWFS(rowNumber + 1, columnNumber - 1);
	    }
	} catch (final ArrayIndexOutOfBoundsException e) {
	    // do nothing
	}

	try {
	    if ((occupancy[rowNumber][columnNumber - 1] == Type.Roman)
		    && (visitedWFS[rowNumber][columnNumber - 1] != Type.Roman)) {
		return doWFS(rowNumber, columnNumber - 1);
	    }
	} catch (final ArrayIndexOutOfBoundsException e) {
	    // do nothing
	}
	return false;
    }

    private void readInputs() {
	try {
	    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String input;
	    int lineCount = 1;
	    final Pattern numberPattern = Pattern.compile("\\d+");

	    String firstNumber = null, secondNumber = null;

	    while ((input = br.readLine()) != null) {
		if (!input.matches("^\\s*$")) {
		    final Matcher matcher = numberPattern.matcher(input);
		    matcher.find();
		    firstNumber = matcher.group();
		    matcher.find();
		    secondNumber = matcher.group();

		    if (lineCount == 1) {
			final int firstNumberValue = Integer.valueOf(firstNumber),
				secondNumberValue = Integer.valueOf(secondNumber);
			if ((firstNumberValue < 1) || (firstNumberValue > 1500)) {
			    throw new IllegalArgumentException("The hexagonal grid size is not allowed.");
			}
			numberOfGrids = firstNumberValue;
			occupancy = new Type[numberOfGrids][numberOfGrids];
			visitedDFS = new Type[numberOfGrids][numberOfGrids];
			visitedWFS = new Type[numberOfGrids][numberOfGrids];
			for (int i = 0; i < numberOfGrids; i++) {
			    for (int j = 0; j < numberOfGrids; j++) {
				occupancy[i][j] = Type.A;
				visitedDFS[i][j] = Type.A;
				visitedWFS[i][j] = Type.A;
			    }
			}

			if ((secondNumberValue < 0) || (secondNumberValue > (firstNumberValue * firstNumberValue))) {
			    throw new IllegalArgumentException("This number of moves is not allowed.");
			}
		    } else {
			final int firstNumberValue = Integer.valueOf(firstNumber),
				secondNumberValue = Integer.valueOf(secondNumber);
			if ((firstNumberValue < 1) || (firstNumberValue > numberOfGrids)) {
			    throw new IllegalArgumentException("The row number is not allowed.");
			}

			if ((secondNumberValue < 0) || (secondNumberValue > numberOfGrids)) {
			    throw new IllegalArgumentException("The column number is not allowed.");
			}

			if ((lineCount % 2) == 0) {
			    if (occupancy[firstNumberValue - 1][secondNumberValue - 1] == Type.A) {
				occupancy[firstNumberValue - 1][secondNumberValue - 1] = Type.Roman;
			    }
			} else {
			    if (occupancy[firstNumberValue - 1][secondNumberValue - 1] == Type.A) {
				occupancy[firstNumberValue - 1][secondNumberValue - 1] = Type.Persian;
			    }
			}
		    }
		    lineCount++;
		} else {
		    break;
		}
	    }
	} catch (final Exception e) {
	    e.printStackTrace();
	}
    }
}
