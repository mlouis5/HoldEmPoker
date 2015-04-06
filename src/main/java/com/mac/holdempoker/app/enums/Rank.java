/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.enums;

/**
 *
 * @author Mac
 */
public enum Rank {

    TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6),
    SEVEN("7", 7), EIGHT("8", 8), NINE("9", 9), TEN("10", 10), JACK("J", 11),
    QUEEN("Q", 12), KING("K", 13), ACE("A", 14);

    private final String value;
    private final int rank;

    Rank(String value, int rank) {
        this.value = value;
        this.rank = rank;
    }

    public String rank() {
        return this.value;
    }
    
    public static Rank getRank(int value){
        Rank[] r = Rank.class.getEnumConstants();
        for(Rank rank : r){
            if(rank.value() == value){
                return rank;
            }
        }
        return null;
    }

    public int value() {
        return rank;
    }
}
