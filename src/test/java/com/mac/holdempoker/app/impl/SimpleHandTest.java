/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Deck;
import com.mac.holdempoker.app.Hand;
import com.mac.holdempoker.app.HandRank;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import com.mac.holdempoker.app.impl.util.AbstractHand;
import com.mac.holdempoker.app.impl.util.HandEvaluator;
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

    private final Hand hand;
    private final HandEvaluator handEvl;

    public SimpleHandTest() {
        hand = new SimpleHand();
        handEvl = new HandEvaluator();
    }

    @Before
    public void setUp() throws IllegalAccessException, Exception {
        hand.addToHand(new SimpleCard(Suit.HEART, Rank.KING));
        hand.addToHand(new SimpleCard(Suit.CLUB, Rank.JACK));
        hand.addToHand(new SimpleCard(Suit.DIAMOND, Rank.NINE));
        hand.addToHand(new SimpleCard(Suit.HEART, Rank.SEVEN));
        hand.addToHand(new SimpleCard(Suit.SPADE, Rank.FIVE));
        hand.addToHand(new SimpleCard(Suit.CLUB, Rank.THREE));
        hand.addToHand(new SimpleCard(Suit.SPADE, Rank.ACE));
        
//        handEvl.haveCard(new SimpleCard(Suit.DIAMOND, Rank.FOUR));
//        handEvl.haveCard(new SimpleCard(Suit.CLUB, Rank.FOUR));
//        handEvl.haveCard(new SimpleCard(Suit.HEART, Rank.FOUR));
//        handEvl.haveCard(new SimpleCard(Suit.SPADE, Rank.THREE));
//        handEvl.haveCard(new SimpleCard(Suit.HEART, Rank.FOUR));
//        handEvl.haveCard(new SimpleCard(Suit.CLUB, Rank.THREE));
//        handEvl.haveCard(new SimpleCard(Suit.SPADE, Rank.ACE));
    }

    @After
    public void tearDown() {
        hand.clearHand();
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
//        System.out.println(sha.scoreHand(hand));
        Card[] dealtCards = new Card[7];

        HandEvaluator he = new HandEvaluator();

        for (int i = 0; i < iterations; i++) {
            int dealtIndex = 0;
            deck.buildDeck();
            deck.shuffleDeck();
//            hand.clearHand();
            he.clear();

            Card sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;
//            hand.addToHand(sCard);
            he.haveCard(sCard);

            deck.drawNumCards(numPlayers);
            sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;
//            hand.addToHand(sCard);
            he.haveCard(sCard);

            deck.burnCard();
            sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;
//            hand.addToHand(sCard);
            he.haveCard(sCard);
            sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;
//            hand.addToHand(sCard);
            he.haveCard(sCard);
            sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;
//            hand.addToHand(sCard);
            he.haveCard(sCard);
            deck.burnCard();
            sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;
//            hand.addToHand(sCard);
            he.haveCard(sCard);
            deck.burnCard();
            sCard = deck.drawNextCard();
            dealtCards[dealtIndex++] = sCard;
//            hand.addToHand(sCard);
            he.haveCard(sCard);

//            System.out.println("cards dealt: " + Arrays.toString(dealtCards));
//            HandRank hr = hand.getHandType();
            AbstractHand ah = he.evaluateHand();
//            Card[] result = hr.getHand();

            Card[] result = ah.getHand();
//            System.out.println(ah.getHandType() + "\t" + Arrays.toString(result));
//            System.out.println(hr.getHandType() + "\t" + Arrays.toString(result));
//            System.out.println();
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
        System.out.println(getClass().getSimpleName() + ": getHand");

        HandRank hr = hand.getHandRank();
        Card[] result = hr.getHand();
        System.out.println(hr.getHandType());
        System.out.println("hand:" + Arrays.toString(result));
        Assert.isTrue(hr.getHandType() == HandType.HIGH);

        SimpleHandAggregator sha = new SimpleHandAggregator();
        System.out.println(sha.scoreHand(hand));
    }

}
