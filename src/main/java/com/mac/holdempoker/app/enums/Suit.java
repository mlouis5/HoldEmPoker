/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.enums;

/**
 *
 * @author Mac
 */
public enum Suit {
    DIAMOND("D", 1), CLUB("C", 10), SPADE("S", 100), HEART("H", 1000);
    
    private String init;
    private int suitValue;
    
    Suit(String init, int val){
        this.init = init;
        this.suitValue = val;
    }
    
    public String initial(){
        return init;
    }
    
    public int suitValue(){
        return suitValue;
    }
}
