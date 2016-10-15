/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Stephan
 */
public class Achievement {
    
    /** identifier for our own database */
    int id;
    /** universal unique identifier, for the QR code */
    String uuid;
    /** Name and description*/
    String name, desc;
    /** Timestamps for expiry and creation dates */
    long expires, created;
    /** flag whether or not this is a secret achievement */
    boolean secret;
    /** points value of the achievement */
    int points;
    
    /** used for the app */
    boolean solved;
      
    private Achievement(int id, String name, String desc, long expires, long created, boolean secret, int points, boolean solved) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.expires = expires * 1000;
        this.created = created * 1000;
        this.secret = secret;
        this.points = points;
        this.solved = solved;
    }
    
    public static Achievement createNewForLib(String name, String desc, long expires, boolean secret, int points) {
        return new Achievement(0 , name, desc, expires * 1000, 0, secret, points, false);
    }
    
    public static Achievement createNewForLib(int id, String name, String desc, long expires, long created, boolean secret, int points) {
        return new Achievement(id, name, desc, expires * 1000, created * 1000, secret, points, false);
    }
    
    public static Achievement createNewForApp(int id, String name, String desc, long expires, long created, boolean secret, int points, boolean solved) {
        return new Achievement(id, name, desc, expires * 1000, created * 1000, secret, points, solved);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getPoints() {
        return points;
    }
    
    public String getUuid() {
        return uuid;
    }

    public long getExpires() {
        return expires;
    }

    public long getCreated() {
        return created;
    }

    public boolean isSecret() {
        return secret;
    }

    @Override
    public String toString() {
        String s = "";
        s += "" + this.id + ", ";
        s += this.name + ", ";
        s += this.desc + ", ";
        s += this.expires + ", ";
        s += this.created + ", ";
        s += this.secret + ", ";
        s += this.points + ", ";
        s += this.solved;
        return s;
    }

}
