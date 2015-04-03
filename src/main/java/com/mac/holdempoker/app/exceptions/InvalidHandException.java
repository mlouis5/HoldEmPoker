/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.exceptions;

/**
 *
 * @author MacDerson
 */
public class InvalidHandException extends Exception{
    
    public InvalidHandException(){
        super("Hand was invalid, reason unknown");
    }
    
    public InvalidHandException(String msg){
        super(msg);
    }
}
