/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.game.impl.util;

import com.mac.holdempoker.app.Player;

/**
 *
 * @author MacDerson
 */
public interface RoundObserver {
        
    void winningPlayers(Player...plyrs);
    
    void playerChecked(Player p);
    
    void raise(Player p, int amount);
    
    void call(Player p, int amount);
    
    void fold(Player p);
    
    void allIn(Player p);
}
