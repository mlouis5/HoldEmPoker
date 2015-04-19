/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Deck;
import com.mac.holdempoker.app.Hand;
import com.mac.holdempoker.app.HandRank;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import com.mac.holdempoker.app.hands.AbstractHand;
import com.mac.holdempoker.app.hands.HandEvaluator;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 *
 * @author Mac
 */
public class SimpleHandTest {

    private Hand hand;

    public SimpleHandTest() {
    }

    @Before
    public void setUp() throws IllegalAccessException, Exception {
        Player p = new SimplePlayer();
        p.haveHoleCard(new SimpleCard(Suit.HEART, Rank.KING));
        p.haveHoleCard(new SimpleCard(Suit.CLUB, Rank.JACK));

        Board b = new SimpleBoard();
        b.dealToBoard(new SimpleCard(Suit.DIAMOND, Rank.NINE),
                new SimpleCard(Suit.HEART, Rank.SEVEN),
                new SimpleCard(Suit.SPADE, Rank.FIVE),
                new SimpleCard(Suit.CLUB, Rank.THREE),
                new SimpleCard(Suit.SPADE, Rank.ACE));

        hand = new SimpleHand(p, b);
    }

    @After
    public void tearDown() {
        hand = null;
    }

//    @Test
//    public void testHand() throws IllegalAccessException, Exception {
//        AbstractHand ah = handEvl.evaluateHand();
//
//        Card[] result = ah.getHand();
//        System.out.println(Arrays.toString(result));
//        Assert.isTrue(ah.getHandType() == HandType.FOUR_OF_A_KIND);
//    }
    @Test
    public void testHandGeneration() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        String started = sdf.format(System.currentTimeMillis());
        Deck deck = new SimpleDeck();
        int iterations = 10000;
        int numPlayers = 3;
        SimpleHandAggregator sha = new SimpleHandAggregator();
        Card[] dealtCards = new Card[7];

        for (int i = 0; i < iterations; i++) {
            Player p = new SimplePlayer();
            Board b = new SimpleBoard();
            int dealtIndex = 0;
            deck.buildDeck();
            deck.shuffleDeck();

            Card sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;
            p.haveHoleCard(sCard);

            deck.drawNumCards(numPlayers);
            sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;
            p.haveHoleCard(sCard);

            deck.burnCard();
            sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;
            b.dealToBoard(sCard);
            sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;            
            b.dealToBoard(sCard);
            
            sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;
            b.dealToBoard(sCard);
            
            deck.burnCard();
            sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;
            b.dealToBoard(sCard);
            
            deck.burnCard();
            sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;
            b.dealToBoard(sCard);

//            System.out.println("cards dealt: " + Arrays.toString(dealtCards));
            Hand h = new SimpleHand(p, b);
            AbstractHand ah = h.getHand();

            Card[] result = ah.getHand();
            System.out.println(ah.getHandType() + "\t" + Arrays.toString(result));
            System.out.println(sha.scoreHand(h));
        }
        System.out.println("Started: " + started + "   Ended: " + sdf.format(System.currentTimeMillis()));
    }

    /**
     * Test of getHand method, of class SimpleHand.
     *
     * @throws java.lang.Exception
     */
//    @Test
    public void testGetHand() throws Exception {
//        System.out.println(getClass().getSimpleName() + ": getHand");
//
//        HandRank hr = hand.getHandRank();
//        Card[] result = hr.getHand();
//        System.out.println(hr.getHandType());
//        System.out.println("hand:" + Arrays.toString(result));
//        Assert.isTrue(hr.getHandType() == HandType.HIGH);
//
//        SimpleHandAggregator sha = new SimpleHandAggregator();
//        System.out.println(sha.scoreHand(hand));
    }

}
