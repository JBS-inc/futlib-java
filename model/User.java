/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stephan
 */
public class User {
    
    /** identifier for our DB */
    int id;
    /** name and password */
    String name, password;
    /** points */
    int points;
    /** List of ACHIEVED achievements */
    List<Achievement> achievements;
    
    public User(int id, String name, String password, int points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getPoints() {
        return points;
    }

    public void scanCode(String qrCode) {
        
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }
    
    

    
    
}



