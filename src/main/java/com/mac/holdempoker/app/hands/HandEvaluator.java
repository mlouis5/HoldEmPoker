/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Player;
import java.lang.reflect.Field;
import java.util.Objects;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Mac
 */
public class HandEvaluator {

    private final RoyalFlush royalFlush;
    private final StraightFlush straightFlush;
    private final Quad quad;
    private final Boat boat;
    private final Flush flush;
    private final Straight straight;
    private final Set set;
    private final TwoPair twoPair;
    private final Pair pair;
    private final High high;

    public HandEvaluator() {
        royalFlush = new RoyalFlush();
        straightFlush = new StraightFlush();
        quad = new Quad();
        boat = new Boat();
        flush = new Flush();
        straight = new Straight();
        set = new Set();
        twoPair = new TwoPair();
        pair = new Pair();
        high = new High();
    }
    
    public AbstractHand evaluateHand(Player p, Board b) 
            throws IllegalArgumentException, IllegalAccessException, Exception{
        Card[] cards = ArrayUtils.addAll(p.getHoleCards(), b.getBoard());
        
        Field[] fields = getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            AbstractHand ah = (AbstractHand) field.get(this);
            ah.haveCards(cards);
            Card[] hand = ah.getHand();
            if(Objects.nonNull(hand)){
                return ah;
            }
            field.setAccessible(false);
        }
        throw new Exception("Invalid hand");
    }
    
    public AbstractHand evaluateHand() 
            throws IllegalArgumentException, IllegalAccessException, Exception{
        
        Field[] fields = getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            AbstractHand ah = (AbstractHand) field.get(this);
            Card[] hand = ah.getHand();
            if(Objects.nonNull(hand)){
                return ah;
            }
            field.setAccessible(false);
        }
        throw new Exception("Invalid hand");
    }
    
    public AbstractHand evaluateHand(Card... cards) 
            throws IllegalArgumentException, IllegalAccessException, Exception{
//        Card[] cards = ArrayUtils.addAll(p.getHoleCards(), b.getBoard());
        
        Field[] fields = getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            AbstractHand ah = (AbstractHand) field.get(this);
            ah.haveCards(cards);
            Card[] hand = ah.getHand();
            if(Objects.nonNull(hand)){
                return ah;
            }
            field.setAccessible(false);
        }
        throw new Exception("Invalid hand");
    }
    
    public void haveCard(Card card)
            throws IllegalArgumentException, IllegalAccessException, Exception{        
        Field[] fields = getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            AbstractHand ah = (AbstractHand) field.get(this);
            ah.haveCard(card);            
            field.setAccessible(false);
        }
    }
    
    public void haveCards(Card... card)
            throws IllegalArgumentException, IllegalAccessException, Exception{        
        Field[] fields = getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            AbstractHand ah = (AbstractHand) field.get(this);
            ah.haveCards(card);            
            field.setAccessible(false);
        }
    }
    
    public void clear(){
        royalFlush.clear();
        straightFlush.clear();
        quad.clear();
        boat.clear();
        flush.clear();
        straight.clear();
        set.clear();
        twoPair.clear();
        pair.clear();
        high.clear();
    }
}
