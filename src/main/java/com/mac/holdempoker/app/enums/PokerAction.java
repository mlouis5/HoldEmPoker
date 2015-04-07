/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.enums;

import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.Player;

/**
 *
 * @author MacDerson
 */
public enum PokerAction implements Action{

    BET, RAISE, FOLD, CALL;

    private Player player;
    
    @Override
    public void setActingPlayer(Player p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
