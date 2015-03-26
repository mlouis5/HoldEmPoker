/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.enums.FaceValue;
import com.mac.holdempoker.app.enums.Suit;
import java.io.Serializable;

/**
 *
 * @author Mac
 */
public interface Card extends Printable, Serializable {
    
    public Suit getSuit();

    public FaceValue getValue();

    public boolean isIsBurned();
    
    public void reset();
}
