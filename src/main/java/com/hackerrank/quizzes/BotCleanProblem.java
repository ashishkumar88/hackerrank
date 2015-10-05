package com.hackerrank.quizzes;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Solution to the bot clean problem
 *
 * @since 10-03-2015
 * @version 2.0v
 * @author Ashish Kumar
 */
public class BotCleanProblem {

    /**
     * Represents components of the game.
     *
     * @since 10-03-2015
     * @version 2.0v
     * @author Ashish Kumar
     */
    enum Components {
	Dirt('d'), Bot('b'), Empty('-');

	private final char code;

	private Components(char code) {
	    this.code = code;
	}

	public char getCode() {
	    return code;
	}
    }

    private static final int N = 5;

    private static double calculateDistance(Location bot, Location dirt) {
	return Math.sqrt(Math.pow((dirt.getX() - bot.getX()), 2) + Math.pow((dirt.getY() - bot.getY()), 2));
    }

    public static void main(String[] args) {
	final Scanner in = new Scanner(System.in);
	final int[] pos = new int[2];
	final String board[] = new String[5];
	for (int i = 0; i < 2; i++) {
	    pos[i] = in.nextInt();
	}
	for (int i = 0; i < 5; i++) {
	    board[i] = in.next();
	}
	next_move(pos[0], pos[1], board);
    }

    static void next_move(int posr, int posc, String[] board) {
	// add logic here
	final Location bot = new Location(posc, posr);
	Location dirt = new Location(N + 1, N + 1);
	double distanceBotFromDirt = -1.0d;

	for (int y = 0; y < N; y++) {
	    for (int x = 0; x < N; x++) {
		if (board[y].charAt(x) == Components.Dirt.getCode()) {
		    final Location currentDirt = new Location(x, y);
		    if ((distanceBotFromDirt > calculateDistance(bot, currentDirt)) || (distanceBotFromDirt < 0)) {
			distanceBotFromDirt = calculateDistance(bot, currentDirt);
			dirt = currentDirt;
		    }
		}
	    }
	}
	if (validateDirt(dirt)) {
	    System.out.println("CLEAN ");
	    System.exit(0);
	} else if (Double.valueOf(new DecimalFormat("#.##").format(distanceBotFromDirt)) == 0.00d) {
	    System.out.println("CLEAN ");
	    System.exit(0);
	} else {

	    int xTraversal = dirt.getX() - bot.getX();
	    int yTraversal = dirt.getY() - bot.getY();

	    /*
	     * as per the x traversal difference, do a left or right walk
	     */
	    while (xTraversal != 0) {
		if (xTraversal < 0) {
		    System.out.println("LEFT");
		    xTraversal++;
		    System.exit(0);
		} else {
		    System.out.println("RIGHT");
		    xTraversal--;
		    System.exit(0);
		}
	    }

	    /*
	     * as per the y traversal difference, do a up or down walk
	     */
	    while (yTraversal != 0) {
		if (yTraversal < 0) {
		    System.out.println("UP");
		    yTraversal++;
		    System.exit(0);
		} else {
		    System.out.println("DOWN");
		    yTraversal--;
		    System.exit(0);
		}
	    }
	}
    }

    private static boolean validateDirt(Location dirt) {
	return ((dirt.getX() > 5) && (dirt.getY() > 5));
    }
}
