/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import java.util.List;

/**
 *
 * @author Mac
 */
public interface Player {
    
    void setPlayerName(String pName);
    
    String getPlayerName();
    
    void setPlayerNumber(int num);
    
    int getPlayerNumber();
    
    void haveCard(Card card);
    
    void resetHand();
    
    void deductAmount(int amount);
    
    int getGambleAmount();
}
