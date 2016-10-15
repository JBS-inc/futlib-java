/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.util.ArrayList;
import java.util.List;
import model.Achievement;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author Stephan
 */
public class JSONHandler {
    
    public List<Achievement> parseAchievements(String achs) {
        List<Achievement> result = new ArrayList<>();
        
        JSONArray dataarray = new JSONArray(achs);
        for (Object object : dataarray) {
            JSONObject jsnobj = (JSONObject)object;
                
            int id = 0;
            if(jsnobj.has("a_id"))
                id = jsnobj.getInt("a_id");
            String name = "";
            if(jsnobj.has("a_name"))
                name = jsnobj.getString("a_name");
            String desc = "";
            if(jsnobj.has("a_description"))
                desc = jsnobj.getString("a_description");
            long expires = 0;
            if(jsnobj.has("a_expiry_time"))
                expires = jsnobj.getLong("a_expiry_time");
            long created = 0;
            if(jsnobj.has("a_creation_time"))
                created = jsnobj.getLong("a_creation_time");
            boolean secret = false;
            if(jsnobj.has("a_hidden"))
                secret = jsnobj.getBoolean("a_hidden");
            int points = 0;
            if(jsnobj.has("a_points"))
                points = jsnobj.getInt("a_points");
            boolean solved = false;
            if(jsnobj.has("a_solved"))
                solved = jsnobj.getBoolean("a_solved");
            
            
            /* just use createForApp, as this will cover all potential field */
            result.add(Achievement.createNewForApp(id, name, desc, expires, created, secret, points, solved));
        }

        return result;
    }
    
    public String parseAchievements(List<Achievement> achs) {
        String result = "";
        
       
        JSONArray dataarray = new JSONArray();
        for (Achievement ach : achs) {
            JSONObject dataobj = new JSONObject();
            
            dataobj.put("a_id", new Integer(ach.getId()));
            dataobj.put("a_name", "" + ach.getName());
            dataobj.put("a_desc", "" + ach.getDesc());
            dataobj.put("a_expires", new Long(ach.getExpires()));
            dataobj.put("a_created", new Long(ach.getCreated()));
            dataobj.put("a_secret", ach.isSecret());
            dataobj.put("a_points", new Integer(ach.getPoints()));
            
            dataarray.put(dataobj);
        }
        
        result = dataarray.toString();
        return result;
    }
     
}
