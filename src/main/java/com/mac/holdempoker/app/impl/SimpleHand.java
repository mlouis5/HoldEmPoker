/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Hand;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.hands.AbstractHand;
import com.mac.holdempoker.app.hands.HandEvaluator;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SimpleHand implements Hand {

    private final HandEvaluator evaluator;

    public SimpleHand(Player p, Board b) 
            throws IllegalAccessException, Exception {        
        evaluator = new HandEvaluator();
        Card[] cards = ArrayUtils.addAll(p.getHoleCards(), b.getBoard());
        evaluator.haveCards(cards);
    }

    @Override
    public AbstractHand getHand() throws Exception {
        return evaluator.evaluateHand();
    }
}
