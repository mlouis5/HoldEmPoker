/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

/**
 *
 * @author MacDerson
 */
public interface HandAggregator {
    
    long scoreHand(Hand hand) throws Exception;
}
