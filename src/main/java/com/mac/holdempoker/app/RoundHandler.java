/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.exceptions.InvalidActionException;
import java.util.List;

/**
 *
 * @author MacDerson
 */
public interface RoundHandler {
    
    void reset();
    
    void newRound();
    
    void setAction(Action action) throws InvalidActionException;
    
    void assessCurrentRound();
}
