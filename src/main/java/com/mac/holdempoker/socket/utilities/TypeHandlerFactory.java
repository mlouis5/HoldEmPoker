/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.socket.utilities;

import com.mac.abstractrepository.entities.holdem.Contestant;
import com.mac.holdempoker.socket.pojos.SignIn;
import com.mac.holdempoker.socket.utilities.handlers.SignInHandler;

/**
 *
 * @author Mac
 */
public class TypeHandlerFactory {
    
    public static <T> TypeHandler getHandler(Object o, Class<T> t){
        
//        if(t == SignIn.class){
//            TypeHandler<SignIn, Contestant> signInHandler = new SignInHandler();
//            signInHandler.have((SignIn) t.cast(o));
//            return signInHandler;
//        }
        return null;
    }
}
