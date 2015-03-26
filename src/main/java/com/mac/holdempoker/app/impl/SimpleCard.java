/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.FaceValue;
import com.mac.holdempoker.app.enums.Suit;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class SimpleCard implements Card {

    private final Suit suit;
    private final FaceValue value;
    private boolean isBurned;

    public SimpleCard(Suit suit, FaceValue value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public Suit getSuit() {
        return suit;
    }

    @Override
    public FaceValue getValue() {
        return value;
    }

    @Override
    public boolean isIsBurned() {
        return isBurned;
    }

    public void setIsBurned(boolean isBurned) {
        this.isBurned = isBurned;
    }
    
    @Override
    public void reset() {
        this.isBurned = false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.suit);
        hash = 47 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimpleCard other = (SimpleCard) obj;
        return Objects.equals(suit, other.suit) && Objects.equals(value, other.value);
    }

    @Override
    public String print() {
        return (Objects.nonNull(suit) && Objects.nonNull(value)) ? suit.initial() + ":" + value.value() : ":";
    }
}
