/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.util;

/**
 *
 * @author Mac
 */
public interface Observable {
    
    void add(CommunityObserver observer);
    
    void notifyObservers();
}
