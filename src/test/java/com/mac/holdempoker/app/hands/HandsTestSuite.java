/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import com.mac.holdempoker.app.impl.SimpleCard;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Mac
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.mac.holdempoker.app.hands.BoatTest.class, 
    com.mac.holdempoker.app.hands.PairTest.class, 
    com.mac.holdempoker.app.hands.StraightFlushTest.class, 
    com.mac.holdempoker.app.hands.FlushTest.class, 
    com.mac.holdempoker.app.hands.QuadsTest.class, 
    com.mac.holdempoker.app.hands.RoyalFlushTest.class, 
    com.mac.holdempoker.app.hands.StraightTest.class, 
    com.mac.holdempoker.app.hands.PairsTest.class, 
    com.mac.holdempoker.app.hands.TripsTest.class, 
    com.mac.holdempoker.app.hands.HighTest.class})
public class HandsTestSuite {
    
    public static final Card[] cards = {new SimpleCard(Suit.CLUB, Rank.ACE), 
        new SimpleCard(Suit.CLUB, Rank.KING), new SimpleCard(Suit.CLUB, Rank.QUEEN),
        new SimpleCard(Suit.CLUB, Rank.JACK), new SimpleCard(Suit.CLUB, Rank.TEN),
        new SimpleCard(Suit.CLUB, Rank.NINE)};

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
