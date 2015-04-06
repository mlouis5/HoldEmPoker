/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Hand;
import com.mac.holdempoker.app.HandEvaluator;
import com.mac.holdempoker.app.HandRank;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.exceptions.InvalidHandException;
import com.mac.holdempoker.app.impl.util.HandMatrix;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
public class SimpleHand implements Hand {

    private final HandMatrix matrix;

    public SimpleHand() {
        matrix = new HandMatrix();
    }

    @Override
    public Card[] getHand() throws Exception {
        HandRank hr = matrix.getHand();
        if (Objects.nonNull(hr)) {
            return hr.getHand();
        }
        throw new InvalidHandException("Unable to obtain a valid hand");
    }

    @Override
    public void addToHand(Card card) {
        matrix.haveCard(card);
    }

    @Override
    public HandType getHandType() throws Exception {
        HandRank hr = matrix.getHand();
        if (Objects.nonNull(hr)) {
            return hr.getHandType();
        }
        throw new InvalidHandException("Unable to obtain a valid HandType");
    }

    @Override
    public void dealt(Card... cards) {
        if (Objects.nonNull(cards)) {
            for (Card card : cards) {
                matrix.haveCard(card);
            }
        }
    }

    @Override
    public void clearHand() {
        matrix.clearHand();
    }

    @Override
    public HandRank getHandRank() throws InvalidHandException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        return matrix.getHand();
    }
}
