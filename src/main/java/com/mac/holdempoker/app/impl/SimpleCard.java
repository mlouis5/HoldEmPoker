/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class SimpleCard implements Card {

    private final Suit suit;
    private final Rank rank;
    private String cardString;

    public SimpleCard(Suit suit, Rank rank) {
        if (Objects.isNull(suit) || Objects.isNull(rank)) {
            throw new NullPointerException("Card, my not have null Suit: " + suit + " null Rank: " + rank);
        }
        this.suit = suit;
        this.rank = rank;
        cardString = this.rank.name().toLowerCase() + "_of_" + this.suit.name().toLowerCase() + "s";
    }

    @Override
    public Suit getSuit() {
        return suit;
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.suit);
        hash = 47 * hash + Objects.hashCode(this.rank);
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
        return Objects.equals(suit, other.suit) && Objects.equals(rank, other.rank);
    }

    @Override
    public String print() {
        return (Objects.nonNull(suit) && Objects.nonNull(rank)) ? suit.initial() + ":" + rank.rank() : ":";
    }

    @Override
    public int compareTo(Card o) {
        if (Objects.isNull(o)) {
            return -1;
        }
        return this.rank.value() - o.getRank().value();
    }

    @Override
    public boolean isSameSuit(Card card) {
        return this.getSuit() == card.getSuit();
    }

    @Override
    public boolean isSameFaceValue(Card card) {
        return this.getRank() == card.getRank();
    }

    @Override
    public String toString() {
        return print();
    }

    @Override
    public String getCardString() {
        return cardString;
    }
}
