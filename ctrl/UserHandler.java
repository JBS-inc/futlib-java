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
        
        String answer = httpsHandler.contactServer("echo", "", "Requesting user authentication"); //TODO
        //TODO parse answer
        
        user = new User(0, "Perry", "testpw", 10);
        lib_id = 0;
        
        READY = true;
    }
    
    public void selectLibrary(int library_id) {
        lib_id = library_id;
    }
        
    public void loadAchievements() {
        // use name and pw to do this
        if(user == null)
            return;

         /** TODO load data */
        String rawdata = httpsHandler.contactServer("achievements/get", "libid=" + lib_id + "&exp=0", "");
        System.out.println("Server answer: " + rawdata);
        
        List<Achievement> achs = new ArrayList();
        //TODO load data

        user.setAchievements(achs);
    }
    
}
