/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.game.impl.util;

/**
 *
 * @author MacDerson
 */
public interface RoundObservable {
    
    void addRoundObserver(RoundObserver ro);
    
    void clearObservers();
    
    void removeObserver(RoundObserver ro);
    
    void notifyObserver();
}
