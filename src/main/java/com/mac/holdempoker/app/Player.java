/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.enums.Status;
import com.mac.holdempoker.app.util.BoardObserver;
import java.util.Set;

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
    
    void addStatus(Status status);
    
    void removeStatus(Status status);
    
    Set<Status> getStatus();
    
    void clearStatus();
    
    void setStack(int stack);
    
    int getStack();
    
    void decreaseStack(int amount);
    
    void increaseStack(int amount);
        
    Card[] getHoleCards();
    
    void clearHand();
    
    void haveHoleCard(Card card);;
    
    void setAvailableActions(Action... possibleActions);
    
    Action[] getAvailableActions();
}
