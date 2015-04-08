/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Hand;
import com.mac.holdempoker.app.Player;
import java.util.Objects;

/**
 *
 * @author MacDerson
 */
public class SimplePlayer implements Player {

    private String playerFirstName;
    private String playerLastName;
    private String playerEmail;
    private String playerId;
    private final Hand hand;
    private int pNumber;
    private int chipStack;
    private boolean isDealer;
    private boolean isBigBlind;
    private boolean isSmallBlind;
    private int actionOrder;
    private Action action;

    public SimplePlayer() {
        this.hand = new SimpleHand();
    }

    @Override
    public String getPlayerName() {
        return playerFirstName + " " + playerLastName;
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
    public void setActionOrder(int actionOrder) {
        this.actionOrder = actionOrder;
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public void setPlayerId(String pId) {
        this.playerId = pId;
    }

    @Override
    public String getPlayerId() {
        return this.playerId;
    }

    @Override
    public void setPlayerEmail(String pEmail) {
        this.playerEmail = pEmail;
    }

    @Override
    public String getPlayerEmail() {
        return this.playerEmail;
    }

    @Override
    public void setPlayerFirstName(String fName) {
        this.playerFirstName = fName;
    }

    @Override
    public String getPlayerFirstName() {
        return this.playerFirstName;
    }

    @Override
    public void setPlayerLastName(String lName) {
        this.playerLastName = lName;
    }

    @Override
    public String getPlayerLastName() {
        return this.playerLastName;
    }

    @Override
    public int getActionOrder() {
        return this.actionOrder;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.playerFirstName);
        hash = 89 * hash + Objects.hashCode(this.playerLastName);
        hash = 89 * hash + Objects.hashCode(this.playerEmail);
        hash = 89 * hash + Objects.hashCode(this.playerId);
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
        final SimplePlayer other = (SimplePlayer) obj;
        return Objects.equals(this.playerFirstName, other.playerFirstName)
                && Objects.equals(this.playerLastName, other.playerLastName)
                && Objects.equals(this.playerEmail, other.playerEmail)
                && Objects.equals(this.playerId, other.playerId);
    }

}
