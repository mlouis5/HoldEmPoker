/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.util.BoardObserver;

/**
 *
 * @author Mac
 */
public interface Player extends BoardObserver{
    
    void setPlayerName(String pName);
    
    String getPlayerName();
    
    void setPlayerNumber(int num);
    
    int getPlayerNumber();
    
    void resetHand();
    
    void decreaseStack(int amount);
    
    void increaseStack(int amount);
    
    int getBetAmount();
    
    boolean isEliminated();
    
    void haveHoleCard(Card card);
    
    void haveSharedCards(Card... card);
    
    void setIsDealer(boolean isDealer);
    
    void setIsBigBlind(boolean isBigBlind);
    
    void setIsSmallBlind(boolean isSmallBlind);
    
    boolean getIsBigBlind();
    
    boolean getIsSmallBlined();
    
    boolean getIsDealer();
    
    void setBetOrder(int betOrder);
}
