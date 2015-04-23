/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.socket.utilities.handlers;

import com.mac.holdempoker.socket.pojos.SignIn;
import com.mac.holdempoker.socket.utilities.Message;
import com.mac.holdempoker.socket.utilities.TypeHandler;

/**
 *
 * @author Mac
 */
public class MessageHandler implements TypeHandler<Message, SignIn>{

    @Override
    public SignIn handle(Message t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
