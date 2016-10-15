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
public class Library {
    
    /** id */
    int id;
    /** name */
    String name;
    /** password */
    String password;
    
    
    /** List of achievements */
    List<Achievement> achievements;
    /** List of created achievements */
    List<Achievement> created_achievements;
    
    /** Points per hour */
    int pointsperhour;
    
    
    public Library(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        
        achievements = new ArrayList<>();
        created_achievements = new ArrayList<>();
    }
    
    

    public int getPointsPerHour() {
        return pointsperhour;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public void setPointsperhour(int pointsperhour) {
        this.pointsperhour = pointsperhour;
    }
    
    public List<Achievement> getAchievements() {
        return achievements;
    }
    
    public int getId() {
        return id;
    }
    
    public boolean createAchievement(String name, String desc, long expires, boolean secret, int points) {
        Achievement newach = Achievement.createNewForLib(name, desc, expires, secret, points);
        created_achievements.add(newach);
        achievements.add(newach);
        return true;
    }
    
    public void clearCreatedAchievements() {
        created_achievements.clear();
    }
    
    public boolean deleteAchievement(int id) {
        return false;
    }
    
    public boolean resetAchievementUuid(int id) {
        return false;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<Achievement> getCreated_achievements() {
        return created_achievements;
    }


    
    
  
}
