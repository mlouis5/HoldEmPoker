/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.exceptions;

import com.mac.holdempoker.app.Board;

/**
 *
 * @author MacDerson
 */
public class InvalidBoardException extends Exception{
    
    public InvalidBoardException(Board board){
        super("Invalid board: " + board);
    }
}
