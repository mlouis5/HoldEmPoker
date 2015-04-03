/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.HandEvaluator;
import com.mac.holdempoker.app.HandRank;
import com.mac.holdempoker.app.exceptions.InvalidHandException;
import com.mac.holdempoker.app.hands.Boat;
import com.mac.holdempoker.app.hands.Flush;
import com.mac.holdempoker.app.hands.High;
import com.mac.holdempoker.app.hands.Pair;
import com.mac.holdempoker.app.hands.Pairs;
import com.mac.holdempoker.app.hands.Quads;
import com.mac.holdempoker.app.hands.RoyalFlush;
import com.mac.holdempoker.app.hands.Straight;
import com.mac.holdempoker.app.hands.StraightFlush;
import com.mac.holdempoker.app.hands.Trips;

/**
 *
 * @author MacDerson
 */
public class SimpleHandEvaluator implements HandEvaluator {

    private final HandRank[] ranks;

    public SimpleHandEvaluator() {
        ranks = new HandRank[10];
        init();
    }

    @Override
    public void haveCard(Card... cards) {
        for(HandRank hr : ranks){
            for(Card card : cards){
                hr.accept(card);
            }
        }
    }

    @Override
    public HandRank getBestHand() throws InvalidHandException {
        for(HandRank hr : ranks){
            if(hr.isValidHand()){
                return hr;
            }
        }
        throw new InvalidHandException("Unable to obtain a correct hand");
    }

    private void init() {
        ranks[0] = new RoyalFlush();
        ranks[1] = new StraightFlush();
        ranks[2] = new Quads();
        ranks[3] = new Boat();
        ranks[4] = new Flush();
        ranks[5] = new Straight();
        ranks[6] = new Trips();
        ranks[7] = new Pairs();
        ranks[8] = new Pair();
        ranks[9] = new High();
    }
}
