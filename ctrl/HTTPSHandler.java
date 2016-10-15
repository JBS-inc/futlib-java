/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;


/**
 *
 * @author Stephan
 */
public class HTTPSHandler {

    private final String USER_AGENT = "Mozilla/5.0";
    
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

            System.out.println("used URL: " + obj.toString());
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
            
            // Read in data
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                answer += inputLine + "\n";
            }
            in.close();

            
            System.out.println("-" + answer);
        } catch (Exception e) {
           // System.err.println(e.getMessage());
           // System.err.println(e.getCause());
           // System.err.println(e.getStackTrace());
            
        }

        return answer;
    }

}
