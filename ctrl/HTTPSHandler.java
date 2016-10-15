/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


/**
 *
 * @author Stephan
 */
public class HTTPSHandler {

    public static final String ERROR_NORESPONSE = "Error No response";
    public static final String ERROR_INVALID = "Error other";
    
    String url;
    
    public HTTPSHandler(String url) {
        this.url = url;
    }

    public String contactServer(String target, String getparams, String postparams) {
        String answer = "";

        try {
            /** Prepare url and add get params */
            URL obj = new URL(url + "/" +  target + "?" + getparams);
           // URL obj = new URL(url + "/" +  target);

            //System.out.println("used URL: " + obj.toString());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");

            /** Add post params */
            String urlParameters = "" + postparams;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(urlParameters.getBytes("UTF8"));
            wr.flush();
            wr.close();
            
            // get response code
            int responseCode = con.getResponseCode();
            // return error codes on fail
            if(responseCode != 200) {
                if(responseCode == 204)
                    return ERROR_INVALID;
                else
                    return ERROR_NORESPONSE;
            }
                
            // Read in data
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                answer += inputLine + "\n";
            }
            
            con.disconnect();
            in.close();
            
        } catch (Exception e) {
           System.err.println(e.getMessage());
        } finally {
           
        }

        return answer;
    }
    
  
    
    public void downloadQRcode(String uuid) {
        try {
            URL website = new URL(url + "/qrcode/" + uuid);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("res/imageQR" + uuid + ".png");
            
            //System.out.println("res/imageQR" + uuid + ".png");    
            
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
             fos.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            //System.out.println("ctrl.HTTPSHandler.downloadQRcode() DONE");
           
        }
        
    }
    

}
