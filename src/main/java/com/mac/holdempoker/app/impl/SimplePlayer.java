/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Hand;
import com.mac.holdempoker.app.Player;

/**
 *
 * @author MacDerson
 */
public class SimplePlayer implements Player {

    private String pName;
    private final Hand hand;
    private int pNumber;
    private int chipStack;
    private int betAmount;
    private boolean isDealer;
    private boolean isBigBlind;
    private boolean isSmallBlind;
    private int betOrder;

    public SimplePlayer() {
        this.hand = new SimpleHand();
    }

    @Override
    public void setPlayerName(String pName) {
        this.pName = pName;
    }

    @Override
    public String getPlayerName() {
        return pName;
    }

    @Override
    public void setPlayerNumber(int num) {
        this.pNumber = num;
    }

    @Override
    public int getPlayerNumber() {
        return pNumber;
    }

    @Override
    public void resetHand() {
        hand.clearHand();
    }

    @Override
    public void decreaseStack(int amount) {
        if (chipStack - amount < 0) {
            chipStack = 0;
        } else {
            chipStack -= amount;
        }
    }

    @Override
    public void increaseStack(int amount) {
        chipStack += amount;
    }

    @Override
    public int getBetAmount() {
        return betAmount;
    }

    @Override
    public void haveSharedCards(Card... cards) {
        for (Card card : cards) {
            hand.addToHand(card);
        }
    }

    @Override
    public void haveHoleCard(Card card) {
        hand.addToHand(card);
    }

    @Override
    public boolean isEliminated() {
        return chipStack == 0;
    }

    @Override
    public void setIsDealer(boolean isDealer) {
        this.isDealer = isDealer;
    }

    @Override
    public void setIsBigBlind(boolean isBigBlind) {
        this.isBigBlind = isBigBlind;
    }

    @Override
    public void setIsSmallBlind(boolean isSmallBlind) {
        this.isSmallBlind = isSmallBlind;
    }

    @Override
    public boolean getIsBigBlind() {
        return this.isBigBlind;
    }

    @Override
    public boolean getIsSmallBlined() {
        return this.isSmallBlind;
    }

    @Override
    public boolean getIsDealer() {
        return this.isDealer;
    }

    @Override
    public void setBetOrder(int betOrder) {
        this.betOrder = betOrder;
    }

}
