/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.socket.utilities.decoders;

import com.mac.holdempoker.socket.utilities.Message;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Mac
 */
public class MessageDecoder implements Decoder.Text<Message>{

    /**
     * Transform the input string into a Message
     * @param string
     * @return 
     * @throws javax.websocket.DecodeException
     */
    @Override
    public Message decode(String string) throws DecodeException {
        JsonObject json = Json.createReader(new StringReader(string)).readObject();
        return new Message(json);
    }
 
    /**
     * Checks whether the input can be turned into a valid Message object
     * in this case, if we can read it as a Json object, we can.
     * @param string
     * @return 
     */
    @Override
    public boolean willDecode(String string) {
        try{
            Json.createReader(new StringReader(string)).read();
            return true;
        }catch (JsonException ex){
            ex.printStackTrace();
            return false;
        }
    }
 
    /**
     * The following two methods are placeholders as we don't need to do anything
     * special for init or destroy.
     * @param config
     */
    @Override
    public void init(EndpointConfig config) {
        System.out.println("init");
    }
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
    
}
