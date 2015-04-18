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
    DIAMOND("D"), CLUB("C"), SPADE("S"), HEART("H");
    
    private String init;
    
    Suit(String init){
        this.init = init;
    }
    
    public String initial(){
        return init;
    }    
}
