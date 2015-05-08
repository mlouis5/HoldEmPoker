/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.enums.RoundType;
import com.mac.holdempoker.game.impl.util.WinnerContainer;

/**
 *
 * @author MacDerson
 */
public interface Pot{
    
    void increasePot(MoneyAction action);
    
    void multiplePlayersWon(WinnerContainer wc);
    
    void newRound(RoundType roundType);
    
    void singlePlayerWon(Player p);
    
    int getPurse();
}
