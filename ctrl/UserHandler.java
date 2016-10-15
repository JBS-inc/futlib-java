/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.util.ArrayList;
import java.util.List;
import model.User;
import model.Achievement;

/**
 *
 * @author Stephan
 */
public class UserHandler extends Handler {
    
    /** handled account */
    User user;
    
    /** current library id */
    int lib_id;
    
    public UserHandler(String url) {
        super(url);
    }
    
    public void loadUser(String name, String password) {
        
        String answer = httpsHandler.contactServer("user/get/", "name=" + name + "&password=" + password, ""); //TODO
        //TODO parse answer
        
        user = new User(0, "Jack", "testpw", 10);
        lib_id = 0;
        
        READY = true;
    }
    
    public void selectLibrary(int library_id) {
        lib_id = library_id;
    }
        
    public boolean loadAchievements() {
        if(user == null)
            return false;

        /** load data */
        //String answer = httpsHandler.contactServer("user/achievements/", "userid=" + user.getId() + "&libid=" + lib_id, "");
        String answer = httpsHandler.contactServer("echo/", "userid=" + user.getId() + "&libid=" + lib_id, "This is from the App");
        
        //System.out.println("Server answer: " + answer);
        
         /** Check connection success */
        if(answer.equals(HTTPSHandler.ERROR_NORESPONSE) || answer.equals(HTTPSHandler.ERROR_INVALID))
            return false;
        
       // List<Achievement> achs = jsonHandler.parseAchievements(answer);

        user.setAchievements(new ArrayList<Achievement>());
        return true;
    }
    
    public boolean handleQRscan(String qrCode) {
        if(user == null)
            return false;
        
        /** load data */
        String answer = httpsHandler.contactServer("user/qr/", "userid=" + user.getId() + "&uuid=" + qrCode, "");
        //System.out.println("Server answer: " + answer);
        
         /** Check connection success */
        if(answer.equals(HTTPSHandler.ERROR_NORESPONSE) || answer.equals(HTTPSHandler.ERROR_INVALID))
            return false;
        
        List<Achievement> achs = jsonHandler.parseAchievements(answer);

        user.setAchievements(achs);
        return true;
    }
    
    public List<Achievement> getAchievements() {
        return user.getAchievements();
    }
    
    public String getName() {
        return user.getName();
    }
   
    public int getPoints() {
        return user.getPoints();
    }
    
}
