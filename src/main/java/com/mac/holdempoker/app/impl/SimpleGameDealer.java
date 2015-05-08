/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.abstractrepository.entities.holdem.GameSetting;
import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Deck;
import com.mac.holdempoker.app.GameDealer;
import com.mac.holdempoker.app.MoneyAction;
import com.mac.holdempoker.app.PlayOrder;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.enums.ActionName;
import com.mac.holdempoker.app.enums.Deal;
import com.mac.holdempoker.app.enums.RoundType;
import com.mac.holdempoker.app.exceptions.InvalidBoardException;
import com.mac.holdempoker.game.impl.GameState;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SimpleGameDealer implements GameDealer {

    private static final int DEAL_ROUNDS = 2;
    private Board board;
    private List<Player> players;
    private PlayOrder playOrder;
    private final Deck deck;
    private Deal currentDeal;
    private GameSetting settings;
    private SimplePot pot;
    
    public SimpleGameDealer(GameSetting gs){
        this.deck = new SimpleDeck();
        currentDeal = Deal.FLOP;
        this.settings = gs;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void dealAround() {
        pot = new SimplePot();
        deck.buildDeck();
        deck.shuffleDeck();
        
        for (int i = 0; i < (this.players.size() * DEAL_ROUNDS); i++) {
            playOrder.dealToNext(deck.drawNextCard());
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
                pot.newRound(RoundType.PRE_TURN);
                break;
            }
            case TURN:{
                 card = deck.drawNumCards(1);
                 currentDeal = Deal.RIVER;
                 pot.newRound(RoundType.PRE_RIVER);
                 break;
            }
            case RIVER:{
                card = deck.drawNumCards(1);
                currentDeal = Deal.FLOP;
                pot.newRound(RoundType.POST_RIVER);
            }
        }
        board.dealToBoard(card); 
    }

    private void validateBoard() throws InvalidBoardException{
        if(Objects.isNull(board)){
            throw new InvalidBoardException(board);
        }
    }

    @Override
    public void actionPerformed(Action action) {
        if(Objects.isNull(action)){
            return;
        }
        if(action instanceof MoneyAction){
            pot.increasePot((MoneyAction) action);
            System.out.println("ADD IMPLEMENTATION FOR BETTING/RAISING");
        }else{
            if(action.getActionName() == ActionName.FOLD){
                System.out.println("ADD IMPLEMENTATION FOR FOLDING");
            }else if(action.getActionName() == ActionName.CHECK){
                System.out.println("ADD IMPLEMENTATION FOR CHECKING");
            }
        }
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void setPlayers(List<Player> players) {
        this.players = players;
        this.playOrder = new SimplePlayOrder(this.settings.getAnte(), this.players);
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public PlayOrder getPlayOrder() {
        return playOrder;
    }

    @Override
    public GameState getGameState() {
        GameState gs = new GameState();
        gs.setBoard(board);
        gs.setPlayers(players);
        gs.setPot(pot);
        return gs;
    }
}
