/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.socket.utilities;

/**
 *
 * @author Mac
 */
public interface TypeHandler<T, R> {
    
    void have(T t);
    
    R handle();
}
