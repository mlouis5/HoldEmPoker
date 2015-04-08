/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.PlayerOrder;
import com.mac.holdempoker.app.Deck;
import com.mac.holdempoker.app.GameDealer;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.Pot;
import com.mac.holdempoker.app.enums.Deal;
import com.mac.holdempoker.app.exceptions.InvalidBoardException;
import com.mac.holdempoker.app.impl.util.PlayerOrderImpl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author MacDerson
 */
public class SimpleGameDealer implements GameDealer {

    private static final int DEAL_ROUNDS = 2;
    private Board board;
    private List<Player> players;
    private PlayerOrder dealOrder;
    private final Deck deck;
    private Deal currentDeal;
    private Map<Pot, List<Player>> pots;
    
    public SimpleGameDealer(){
        this.deck = new SimpleDeck();
        currentDeal = Deal.FLOP;
        pots = new HashMap();
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void setPlayers(Player... players) {
        this.players = Arrays.asList(players);
        this.dealOrder = new PlayerOrderImpl(this.players);
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
    public void dealBoard() throws InvalidBoardException {
        validateBoard();
        deck.burnCard();
        Card[] card = null;
        
        switch(currentDeal){
            case FLOP:{
                card = deck.drawNumCards(3);
                currentDeal = Deal.TURN;
                break;
            }
            case TURN:{
                 card = deck.drawNumCards(1);
                 currentDeal = Deal.RIVER;
                 break;
            }
            case RIVER:{
                card = deck.drawNumCards(1);
                currentDeal = Deal.FLOP;
            }
        }
        board.dealToBoard(card);        
        for(Player p : players){
            if(!p.isEliminated()){
                p.haveSharedCards(card);
            }
        }
    }

    private void validateBoard() throws InvalidBoardException{
        if(Objects.isNull(board)){
            throw new InvalidBoardException(board);
        }
    }

    @Override
    public void actionPerformed(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
