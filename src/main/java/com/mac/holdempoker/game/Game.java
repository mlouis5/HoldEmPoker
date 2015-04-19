/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.game;

import com.mac.abstractrepository.entities.holdem.GameSetting;
import com.mac.holdempoker.game.impl.GameState;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Map;
import org.java_websocket.WebSocket;

/**
 *
 * @author Mac
 */
public interface Game {
    
    Map<String, WebSocket> getActiveConnections();
    
    void addActiveConnection(String pId, WebSocket conn);
    
    String getGameId();
    
    void setGameId(String gameId);
    
    void setGameStartTime(LocalDateTime dateTime);
    
    Temporal getGameStartTime();
    
    void removeConnection(WebSocket conn);
    
    void sendGameState(GameState gameState) throws IOException;
    
    GameSetting getGameSetting();
}
