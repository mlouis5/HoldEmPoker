/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.socket.utilities;

import com.mac.holdempoker.socket.pojos.SignIn;
import java.io.IOException;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mac
 */
@Component
public class MessageConverter {
    
    private Class<?> type;
    private Object convertedPayload;

    public TypeHandler convertMessage(String message) throws IOException {
        Message msg = (Message) JsonConverter.fromJsonString(message, Message.class);
        String header = msg.getHeader();
        
        switch (header) {
            case "sign in": {
                Object pLoad = convertPayload(JsonConverter.toJsonString(msg.getPayload()), SignIn.class);
                return TypeHandlerFactory.getHandler(pLoad, SignIn.class);
            }
            default: return null;
        }
    }

    private <T> T convertPayload(String payload, Class<T> payloadType)
            throws IOException {
        return (T) JsonConverter.fromJsonString(payload, payloadType);
    }
}
