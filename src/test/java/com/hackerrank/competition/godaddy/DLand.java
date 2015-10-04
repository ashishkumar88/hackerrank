package com.hackerrank.competition.godaddy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DLand {

	public static void main(String[] args) {
		final DLand app = new DLand();
		app.readInputs();
		app.collectMaxPoints();
		System.out.println(Arrays.deepToString(app.diamondLocations));
	}

	private int numberOfSets;

	private int[][] diamondLocations;

	private void collectMaxPoints() {
		// TODO Auto-generated method stub

	}

	private void readInputs() {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input;
			final String inputPattern = "^(\\d+)|(\\d+\\s\\d+\\s\\d+)$";
			final Pattern numberPattern = Pattern.compile("\\d+");
			int count = 0;
			while ((input = br.readLine()) != null) {
				if (input.matches(inputPattern)) {
					if (input.matches("^\\d+$")) {
						numberOfSets = Integer.valueOf(input);
						diamondLocations = new int[numberOfSets][3];
					} else {
						final Matcher matcher = numberPattern.matcher(input);
						int index = 0;
						while (matcher.find()) {
							diamondLocations[count][index++] = Integer.valueOf(matcher.group());
						}
						count++;
					}
				} else {
					break;
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (final IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}
}
