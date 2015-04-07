/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.DealOrder;
import com.mac.holdempoker.app.Deck;
import com.mac.holdempoker.app.GameDealer;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.exceptions.InvalidBoardException;
import com.mac.holdempoker.app.impl.util.DealOrderImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author MacDerson
 */
public class SimpleGameDealer implements GameDealer {

    private static final int DEAL_ROUNDS = 2;
    private Board board;
    private List<Player> players;
    private DealOrder dealOrder;
    private Deck deck;
    
    
    
    public SimpleGameDealer(){
        this.deck = new SimpleDeck();
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void setPlayers(Player... players) {
        this.players = Arrays.asList(players);
        this.dealOrder = new DealOrderImpl(this.players);
    }

    @Override
    public void dealAround() {
        deck.validateDeck();
        deck.shuffleDeck();
        List<Player> allPlayers = dealOrder.getDealOrder();

        for (int i = 0; i < DEAL_ROUNDS; i++) {
            allPlayers.stream().filter((p) -> (Objects.nonNull(p) 
                    && !p.isEliminated())).forEach((p) -> {
                p.haveHoleCard(deck.drawNextCard());
            });
        }
    }

    @Override
    public void dealFlop() throws InvalidBoardException {
        validateBoard();
        deck.burnCard();
        Card[] flop = deck.drawNumCards(3);
        board.setFlop(flop);
        
        for(Player p : players){
            if(!p.isEliminated()){
                p.haveSharedCards(flop);
            }
        }
    }

    @Override
    public void dealTurn() throws InvalidBoardException {
        validateBoard();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dealRiver() throws InvalidBoardException {
        validateBoard();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void validateBoard() throws InvalidBoardException{
        if(Objects.isNull(board)){
            throw new InvalidBoardException(board);
        }
    }
}
