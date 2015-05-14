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
public enum Header {
    SIGN_IN, ACTION, HOLE_CARD, GAME_STATE;
    
    public static Header getHeader(String header){
        Header[] headers = Header.class.getEnumConstants();
        for(Header h : headers){
            if(h.name().equalsIgnoreCase(header)){
                return h;
            }
        }
        return null;
    }
}
