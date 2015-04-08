/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.util.BoardObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mac
 */
public class SimpleBoard implements Board {

    private List<Card> board;
    
    public SimpleBoard(){
        board = new ArrayList();
    }
    
    @Override
    public Card[] getFlop() {
        if(board.size() < 3){
            return null;
        }
        return board.subList(0, 3).toArray(new Card[3]);
    }

    @Override
    public Card getTurn() {
        if(board.size() < 4){
            return null;
        }
        return board.get(3);
    }

    @Override
    public Card getRiver() {
        if(board.size() < 5){
            return null;
        }
        return board.get(4);
    }

    @Override
    public void dealToBoard(Card... cards) {
        if(board.size() < 5){
            board.addAll(Arrays.asList(cards));
        }
    }

    @Override
    public Card[] getBoard() {
        return board.toArray(new Card[5]);
    }

    @Override
    public void add(BoardObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resetBoard() {
        board.clear();
    }
    
}
