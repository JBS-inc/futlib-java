/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;


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
    
    public boolean loadLibrary(String name, String password) {
        
        String answer = httpsHandler.contactServer("echo", "", "Requesting library authentication"); //TODO
        //System.out.println("Server answer: " + answer);
         
        /** Check connection success */
        if(answer.equals(HTTPSHandler.ERROR_NORESPONSE) || answer.equals(HTTPSHandler.ERROR_INVALID))
            return false;
        
        //TODO parse answer
        library = new Library(0, "Scottish Library", "testpw");
        READY = true;
        
        return true;
    }
    
    public boolean createAchievement(String name, String desc, boolean secret, int points) {
        return library.createAchievement(name, desc, getCurrentTime(), secret, points);
    }
    
    /**
     * Sends general, ie points/hour data.
     */
    public boolean sendData() {
        /** contact server */
        String answer = httpsHandler.contactServer("library/setpph/", "pph="+library.getPointsPerHour() , "");
        //System.out.println("Server answer: " + answer);
         
        /** Check connection success */
        if(answer.equals(HTTPSHandler.ERROR_NORESPONSE) || answer.equals(HTTPSHandler.ERROR_INVALID))
            return false;
        
        return true;
    }
    
    public boolean sendCreatedAchievements() {
        if(library == null)
            return false;
         
        /** contact server */
        String answer = httpsHandler.contactServer("achievements/add/", "libid=" + library.getId() , "" + jsonHandler.parseAchievements(library.getCreated_achievements()));
        //System.out.println("Server answer: " + answer);
         
        /** Check connection success */
        if(answer.equals(HTTPSHandler.ERROR_NORESPONSE) || answer.equals(HTTPSHandler.ERROR_INVALID))
            return false;
        
        /** clear created achivements list, as they have been sent to server */
        library.clearCreatedAchievements();
        return true;
    }
    
    public boolean resetQRcode(String uuid) {
         if(library == null)
            return false;
         
         /** contact server */
        String answer = httpsHandler.contactServer("achievements/reset/", "uuid=" + uuid, "");
        //System.out.println("Server answer: " + answer);
         
        /** Check connection success */
        if(answer.equals(HTTPSHandler.ERROR_NORESPONSE) || answer.equals(HTTPSHandler.ERROR_INVALID))
            return false;
        
        return true;
    }
    
    public boolean loadAchievements() {
        if(library == null)
            return false;

        /** load data */
        String answer = httpsHandler.contactServer("achievements/get/", "libid=" + library.getId() + "&expired=true", "");
        //System.out.println("Server answer: " + answer);
        
         /** Check connection success */
        if(answer.equals(HTTPSHandler.ERROR_NORESPONSE) || answer.equals(HTTPSHandler.ERROR_INVALID))
            return false;
        
        List<Achievement> achs = jsonHandler.parseAchievements(answer);

        library.setAchievements(achs);
        return true;
    }
    
    public boolean loadData() {
        if(library == null)
            return false;
        
        /** load data */
        String answer = httpsHandler.contactServer("library/getpph/", "libid="+library.getId(), "");
        //System.out.println("Server answer: " + answer);
        
         /** Check connection success */
        if(answer.equals(HTTPSHandler.ERROR_NORESPONSE) || answer.equals(HTTPSHandler.ERROR_INVALID))
            return false;
        
        //int value = Integer.parseInt(rawdata); //TODO
        int value = 20;
        library.setPointsperhour(value);
        return true;
    }
    
    public boolean requestQRcode(String uuid) {
        httpsHandler.downloadQRcode(uuid);
        
        return true;
    }
    
   
    public int getPointsPerHour() {
        return library.getPointsPerHour();
    }
    
    public List<Achievement> getAchievements() {
        return library.getAchievements();
    }
    
    public String getName() {
        return library.getName();
    }
}
