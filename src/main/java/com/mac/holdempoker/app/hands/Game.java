/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.abstractrepository.entities.holdem.GameSetting;
import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.game.impl.GameState;
import java.time.temporal.Temporal;

public interface Game {
    
    String getGameId();
    
    Temporal getGameStartTime();
    
    GameSetting getGameSetting();
    
    GameState getGameState();
    
    void start();
    
    void addPlayer(Player p);
    
    void actionPerformed(Action action);
    
}
