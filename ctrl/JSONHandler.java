/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Achievement;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author Stephan
 */
public class JSONHandler {
    
    /** Takes a json string of achievements and converts them into an ArrayList
     * of Achievements.
     * @param achs String in JSON format
     * @return 
     */
    public List<Achievement> parseAchievements(String achs) {
        List<Achievement> result = new ArrayList<>();
        
        JSONArray dataarray = new JSONArray(achs);
        for (Object object : dataarray) {
            JSONObject jsnobj = (JSONObject)object;
            
            /* set up achievment params */
            int id = 0;
            String uuid = "none";
            String name = "none";
            String desc = "none";
            long expires = 0;                                
            long created = 0;
            boolean secret = false;
            int points = 0;
            boolean solved = false;
            /* read them from json */
            if(jsnobj.has("a_id"))
                id = jsnobj.getInt("a_id");
            if(jsnobj.has("a_uuid"))
                uuid = jsnobj.get("a_uuid").toString();
            if(jsnobj.has("a_name"))
                name = jsnobj.getString("a_name");
            if(jsnobj.has("a_description"))
                desc = jsnobj.getString("a_description");
            if(jsnobj.has("a_expiry_time"))
                expires = jsnobj.getLong("a_expiry_time");
            if(jsnobj.has("a_creation_time"))
                created = jsnobj.getLong("a_creation_time");
            if(jsnobj.has("a_hidden"))
                secret = jsnobj.getBoolean("a_hidden");
            if(jsnobj.has("a_points"))
                points = jsnobj.getInt("a_points");
            if(jsnobj.has("a_solved"))
                solved = jsnobj.getBoolean("a_solved");
            /* might be ag_ instead of a_ */
            if(jsnobj.has("ag_id"))
                id = jsnobj.getInt("ag_id");
            if(jsnobj.has("ag_uuid"))
                uuid = jsnobj.get("ag_uuid").toString();
            if(jsnobj.has("ag_name"))
                name = jsnobj.getString("ag_name");
            if(jsnobj.has("ag_description"))
                desc = jsnobj.getString("ag_description");
            if(jsnobj.has("ag_expiry_time"))
                expires = jsnobj.getLong("ag_expiry_time");
            if(jsnobj.has("ag_creation_time"))
                created = jsnobj.getLong("ag_creation_time");
            if(jsnobj.has("ag_hidden"))
                secret = jsnobj.getBoolean("ag_hidden");
            if(jsnobj.has("ag_points"))
                points = jsnobj.getInt("ag_points");
            if(jsnobj.has("ag_solved"))
                solved = jsnobj.getBoolean("ag_solved");

            /* just use createForApp, as this will cover all potential field */
            result.add(Achievement.createNewForApp(id, uuid, name, desc, expires, created, secret, points, solved));
        }

        return result;
    }
    
    /**
     * Takes a list of Achievements and returns a string in JSON 
     * representing this list.
     * @param achs
     * @return 
     */
    public String parseAchievements(List<Achievement> achs) {
        String result = "";
        
       
        JSONArray dataarray = new JSONArray();
        for (Achievement ach : achs) {
            JSONObject dataobj = new JSONObject();
            
            //if(ach.getId() >= 0)    // an unset id = -1
            //    dataobj.put("na_id", new Integer(ach.getId()));
            dataobj.put("na_name", "" + ach.getName());
            dataobj.put("na_description", "" + ach.getDesc());
            dataobj.put("na_expiry_time", new Long(ach.getExpires()));
            dataobj.put("na_creation_time", new Long(ach.getCreated()));
            dataobj.put("na_hidden", ach.isSecret());
            dataobj.put("na_points", new Integer(ach.getPoints()));
            
            dataarray.put(dataobj);
        }
        
        result = dataarray.toString();
        return result;
    }
    
    
    
     public int[] stringToArray(String arr) {
        String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        int[] results = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
             results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {
             //NOTE: write something here if you need to recover from formatting errors
            };
        }
        return results;
    }
 
      public String parseUUID(int[] uuidarray) {
        
      //UUID uid = UUID.fromAuuidarray);
       
       return "";
    }
}
