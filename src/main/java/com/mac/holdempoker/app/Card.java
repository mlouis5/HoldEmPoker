/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import java.io.Serializable;

/**
 *
 * @author Mac
 */
public interface Card extends Printable, Serializable, Comparable<Card> {
    
    public Suit getSuit();

    public Rank getRank();

    public boolean isIsBurned();
    
    public void reset();
    
    public boolean isSameSuit(Card card);
    
    public boolean isSameFaceValue(Card card);
}
