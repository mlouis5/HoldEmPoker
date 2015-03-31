/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Deck;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mac
 */
public class SimpleDeck implements Deck {

    private static final int CARDS_IN_DECK = 52;
    private final List<Card> deck;
    
    public SimpleDeck(){
        deck = new ArrayList(CARDS_IN_DECK);
        validateDeck();
        shuffleDeck();
    }    
    
    @Override
    public boolean burnCard() {
        if(deck.isEmpty()){
            return false;
        }
        return deck.remove(deck.get(deck.size() - 1));
    }

    @Override
    public Card drawNextCard() {
        if(deck.isEmpty()){
            return null;
        }
        return deck.get(deck.size() - 1);
    }

    @Override
    public final void shuffleDeck() {
        if(deck.isEmpty()){
            return;
        }
        deck.stream().forEach((card) -> {
            card.reset();
        });
        Collections.shuffle(deck);
        shuffle();
    }
    
    private void shuffle(){
        int bottom = (deck.size() / 2) - 1;
        int top = deck.size() / 2;
        
        for(int i = 0; i < (deck.size() / 2); i++){
            swap(top++, bottom--);
        }
    }
    
    private void swap(int top, int bottom){
        Card temp = deck.get(bottom);
        deck.set(bottom, deck.get(top));
        deck.set(top, temp);
    }

    @Override
    public final void validateDeck() {
        Suit[] suits = Suit.values();
        Rank[] values = Rank.values();
        
        deck.clear();
        for(Suit suit : suits){
            for(Rank fv : values){
                Card card = new SimpleCard(suit, fv);
                deck.add(card);
            }
        }
    }
}
