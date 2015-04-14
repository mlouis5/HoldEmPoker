/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.util.BoardObserver;
import java.util.List;

/**
 *
 * @author Mac
 */
public interface Player extends BoardObserver{
    
    void setPlayerId(String pId);
    
    String getPlayerId();
    
    void setPlayerEmail(String pEmail);
    
    String getPlayerEmail();
    
    void setPlayerFirstName(String fName);
    
    String getPlayerFirstName();
    
    void setPlayerLastName(String lName);
    
    String getPlayerLastName();
    
    String getPlayerName();
    
    void setPlayerNumber(int num);
    
    int getPlayerNumber();
    
    void resetHand();
    
    void setStack(int stack);
    
    int getStack();
    
    void decreaseStack(int amount);
    
    void increaseStack(int amount);
    
    boolean isEliminated();
    
    Card[] getHoleCards();
    
    void haveHoleCard(Card card);
    
    void haveSharedCards(Card... card);
    
    void setIsDealer(boolean isDealer);
    
    void setIsBigBlind(boolean isBigBlind);
    
    void setIsSmallBlind(boolean isSmallBlind);
    
    boolean getIsBigBlind();
    
    boolean getIsSmallBlined();
    
    boolean getIsDealer();
    
    void setActionOrder(int betOrder);
    
    int getActionOrder();
    
    void setAction(Action action);
    
    Action getAction();
    
    boolean getIsAllIn();
    
    void setIsAllIn(boolean isAllIn);
    
    void setAvailableActions(Action... possibleActions);
    
    Action[] getAvailableActions();
}
