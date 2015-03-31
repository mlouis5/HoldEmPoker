/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.enums;

import java.util.Arrays;

/**
 *
 * @author Mac
 */
public enum Rank {

    TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6),
    SEVEN("7", 7), EIGHT("8", 8), NINE("9", 9), TEN("10", 10), JACK("J", 11),
    QUEEN("Q", 12), KING("K", 13), ACE("A", 14);

    private static final Rank[][] straights;

    static {
        straights = buildStraights();
    }

    private String value;
    private int rank;

    Rank(String value, int rank) {
        this.value = value;
        this.rank = rank;
    }

    public String rank() {
        return this.value;
    }

    public int value() {
        return rank;
    }

    private static Rank[][] buildStraights() {
        Rank[][] values = new Rank[10][5];
        Rank[] allFaces = Rank.class.getEnumConstants();
        Arrays.sort(allFaces);

        int index = 0;
        for (int i = 0; i < values.length; i++) {
            Rank[] faces = values[i];
            if (i == 9) {
                faces[0] = Rank.ACE;
                for(int j = 1; j < 5; j++){
                    faces[j] = allFaces[j];
                }
            } else {
                for (int j = 0; j < 5; j++) {
                    faces[j] = allFaces[index + j];
                }
            }
            values[i] = faces;
            index++;
        }
        return values;
    }
}
