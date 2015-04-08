/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.enums.ActionName;

/**
 *
 * @author MacDerson
 */
public interface Action {
    
    void setActingPlayer(Player p);
    
    Player getActingPlayer();
    
    ActionName getActionName();
}
