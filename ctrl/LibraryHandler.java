/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.util.ArrayList;
import java.util.List;
import model.Achievement;
import model.Library;

/**
 *
 * @author Stephan
 */
public class LibraryHandler extends Handler {
    
    Library library;
    
    public LibraryHandler(String url) {
        super(url);
    }
    
    public void loadLibrary(String name, String password) {
        
        String answer = httpsHandler.contactServer("echo", "", "Requesting library authentication"); //TODO
        //TODO parse answer
        library = new Library(1, "test", "testpw");
        READY = true;
    }
    
    public boolean createAchievement(String name, String desc, boolean secret, int points) {
        return library.createAchievement(name, desc, getCurrentTime(), secret, points);
    }
    
    /**
     * Sends general, ie points/hour data.
     */
    public void sendData() {
        /** contact server */
        String answer = httpsHandler.contactServer("library/setpph/", "pph="+library.getPointsPerHour() , "");
        
        
        System.out.println("Server answer: " + answer);
    }
    
    public void sendCreatedAchievements() {
        /** contact server */
        String answer = httpsHandler.contactServer("achievements/add/", "" , "" + jsonHandler.parseAchievements(library.getCreated_achievements()));
        /** clear created achivements list, as they have been sent to server */
        library.clearCreatedAchievements();
        
        System.out.println("Server answer: " + answer);
    }
    
    public void loadAchievements() {
        // use name and pw to do this
        if(library == null)
            return;

         /** TODO load data */
        String rawdata = httpsHandler.contactServer("achievements/get/", "libid=" + library.getId() + "&expired=true", "");
        System.out.println("Server answer: " + rawdata);
        
        
        
        List<Achievement> achs = jsonHandler.parseAchievements(rawdata);
        //TODO load data

        library.setAchievements(achs);
        
    }
    
    public void loadData() {
        String rawdata = httpsHandler.contactServer("library/getpph/", "libid="+library.getId(), "");
       
        //int value = Integer.parseInt(rawdata); //TODO
        int value = 20;
        library.setPointsperhour(value);
    }
   
    public int getPointsPerHour() {
        return library.getPointsPerHour();
    }
    
    public List<Achievement> getAchievements() {
        return library.getAchievements();
    }
    
}
