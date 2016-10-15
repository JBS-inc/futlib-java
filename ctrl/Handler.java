/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 *
 * @author Stephan
 */
public abstract class Handler {
    
    //192.168.5.89
    
    HTTPSHandler httpsHandler;
    
    JSONHandler jsonHandler;
    
    /** flag to determine if necessary data has been loaded */
    boolean READY;
    
    public Handler(String url) {
        httpsHandler = new HTTPSHandler(url);
        jsonHandler = new JSONHandler();
    }
    
    protected long getCurrentTime() {
        Date date = new Date();
        
        return date.getTime();
    }
    
    public boolean isReady() {
        return READY;
    }

   
    
    
   
}
