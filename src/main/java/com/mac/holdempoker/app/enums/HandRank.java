/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.enums;

/**
 *
 * @author MacDerson
 */
public enum HandRank {
    ROYAL_FLUSH(HandValue.INDETERMINATE), STRAIGHT_FLUSH(HandValue.DETERMINATE),
    FOUR_OF_A_KIND(HandValue.DETERMINATE), FULL_HOUSE(HandValue.DETERMINATE),
    FLUSH(HandValue.DETERMINATE), STRAIGHT(HandValue.DETERMINATE),
    THREE_OF_A_KIND(HandValue.DETERMINATE), TWO_PAIR(HandValue.DETERMINATE), 
    PAIR(HandValue.DETERMINATE), HIGH(HandValue.DETERMINATE);
    
    private HandValue handValue;
    HandRank(HandValue handValue){
        this.handValue = handValue;
    }
    
    public HandValue handValue(){
        return this.handValue;
    }
    
    public static enum HandValue{
        DETERMINATE, INDETERMINATE;
    }
}
