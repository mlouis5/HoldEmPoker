/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.game.impl;

import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.Pot;
import java.util.List;

/**
 *
 * @author Mac
 */
public class GameState {
    
    private Player actingPlayer;
    
    private List<Player> players;
    
    private List<Pot> pots;
    
    private Board board;
}
