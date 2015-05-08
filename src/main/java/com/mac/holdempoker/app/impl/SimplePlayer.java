/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SimplePlayer implements Player {

    private String playerFirstName;
    private String playerLastName;
    private String playerEmail;
    private String playerId;
    private final List<Card> holeCards;
    private int pNumber;
    private int chipStack;
    private boolean isDealer;
    private boolean isBigBlind;
    private boolean isSmallBlind;
    private boolean isAllIn;
    private int actionOrder;
    private Action action;
    private Action[] availableActions;

    public SimplePlayer() {
        this.isAllIn = false;
        this.isDealer = false;
        this.isBigBlind = false;
        this.isSmallBlind = false;
        this.chipStack = 0;
        this.holeCards = new ArrayList();
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
    public void haveHoleCard(Card card) {
        if(holeCards.size() < 2){
            holeCards.add(card);
        }
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

    @Override
    public boolean getIsAllIn() {
        return isAllIn;
    }

    @Override
    public void setIsAllIn(boolean isAllIn) {
        this.isAllIn = isAllIn;
    }

    @Override
    public void setStack(int stack) {
        if (chipStack == -1 && stack > 0) {
            chipStack = stack;
        }
    }

    @Override
    public int getStack() {
        return chipStack;
    }

    @Override
    public void setAvailableActions(Action... possibleActions) {
        this.availableActions = possibleActions;
    }

    @Override
    public Action[] getAvailableActions() {
        return availableActions;
    }

    @Override
    public Card[] getHoleCards() {
        return holeCards.toArray(new Card[2]);
    }

    @Override
    public void clearHand() {
        this.holeCards.clear();
    }

}
