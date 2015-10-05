package com.hackerrank.quizzes;

import java.util.Scanner;

/**
 * Solution to the bot saves princess problem
 *
 * @since 10-03-2015
 * @version 2.0v
 * @author Ashish Kumar
 */
public class BotSavesPrincess2 {

    /**
     * Represents components of the game.
     *
     * @since 10-03-2015
     * @version 2.0v
     * @author Ashish Kumar
     */
    enum Components {
	Princess('p'), Bot('m'), Empty('-');

	private final char code;

	private Components(char code) {
	    this.code = code;
	}

	public char getCode() {
	    return code;
	}
    }

    /*
     * displays the path traversed by the bot to the pricess. (static method as
     * per the problem statment)
     *
     * @param n: size of the grid, grid: an array containing the location of the
     * pricess and the bot
     */
    static void displayPathtoPrincess(int n, String[] grid) {
	final Location princess = new Location();
	final Location bot = new Location();

	for (int y = 0; y < n; y++) {
	    for (int x = 0; x < n; x++) {
		if (grid[y].charAt(x) == Components.Princess.getCode()) {
		    princess.setX(x).setY(y);
		}
		if (grid[y].charAt(x) == Components.Bot.getCode()) {
		    bot.setX(x).setY(y);
		}
	    }
	}

	/*
	 * bot does a walk, determine the walk
	 */
	int xTraversal = princess.getX() - bot.getX();
	int yTraversal = princess.getY() - bot.getY();

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

    public static void main(String[] args) {
	final Scanner in = new Scanner(System.in);
	int m;
	m = in.nextInt();
	final String grid[] = new String[m];
	for (int i = 0; i < m; i++) {
	    grid[i] = in.next();
	}
	// close the scanner
	in.close();
	displayPathtoPrincess(m, grid);
    }
}
