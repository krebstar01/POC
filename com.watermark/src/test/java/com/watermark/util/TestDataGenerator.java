package com.watermark.util;

import java.util.Random;

public class TestDataGenerator {

	private static String[] Beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru", "Ray", "Bre", "Zed", "Drak", "Mor", "Jag",
			"Mer", "Jar", "Mjol", "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro", "Mar", "Luk" };
	private static String[] Middle = { "air", "ir", "mi", "sor", "mee", "clo", "red", "cra", "ark", "arc", "miri",
			"lori", "cres", "mur", "zer", "marac", "zoir", "slamar", "salmar", "urak" };
	private static String[] End = { "d", "ed", "ark", "arc", "es", "er", "der", "tron", "med", "ure", "zur", "cred",
			"mur" };

	private static Random rand = new Random();

	public static String generateName() {

		return Beginning[rand.nextInt(Beginning.length)] + " " + Middle[rand.nextInt(Middle.length)] + " "
				+ End[rand.nextInt(End.length)];

	}

	public static String generateRandomWords(int numberOfWords) {
		String[] randomStrings = new String[numberOfWords];
		Random random = new Random();
		for (int i = 0; i < numberOfWords; i++) {
			char[] word = new char[random.nextInt(8) + 3];
			for (int j = 0; j < word.length; j++) {
				word[j] = (char) ('a' + random.nextInt(26));
			}
			randomStrings[i] = new String(word);
		}

		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < randomStrings.length; i++) {
			strBuilder.append(randomStrings[i] + " ");
		}

		String results = strBuilder.toString();

		return results;
	}

	public static String creatDummyId() {
		return String.valueOf((int) (Math.random() * 9000) + 1000);
	}

}
