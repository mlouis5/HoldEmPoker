/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import java.io.Serializable;

/**
 *
 * @author Mac
 */
public interface Deck extends Serializable{
    
    boolean burnCard();
    
    Card drawNextCard();
    
    void shuffleDeck();
    
    void validateDeck();
}
