/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.enums;

/**
 *
 * @author MacDerson
 */
public enum HandType {

    ROYAL_FLUSH(Long.MAX_VALUE), STRAIGHT_FLUSH(8000000L),
    FOUR_OF_A_KIND(7000000L), FULL_HOUSE(6000000L),
    FLUSH(5000000L), STRAIGHT(4000000L),
    THREE_OF_A_KIND(3000000L), TWO_PAIR(2000000L),
    PAIR(1000000L), HIGH(0);

    private final long interValue;

    HandType(long value) {
        this.interValue = value;
    }

    public long getInterValue() {
        return this.interValue;
    }
}
