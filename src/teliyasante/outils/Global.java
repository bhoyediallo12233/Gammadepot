/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teliyasante.outils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


/**
 *
 * @author bhoyediallo
 */
final public class Global {
   
   public boolean IsConnectionAvailable() {
       try {
         URL url = new URL("http://www.google.com");
         URLConnection connection = url.openConnection();
         connection.connect();
         //System.out.println("Internet is connected");
         return true;
      }  catch (IOException e) {
        // System.out.println("Internet is not connected");
         return false;
      }
     }
    
    public JsonObject getList(URL url) throws Exception
    {
      // URL obj = new URL("http://localhost:8000/api/liste");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setRequestProperty("accept","Application/json");
        StringBuffer response;
       try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
           String inputLine;
           response = new StringBuffer();
           while ((inputLine = in.readLine()) != null) {
               response.append(inputLine);
           }
       }
        JsonParser parser= new JsonParser();
        JsonObject jsonResponse = (JsonObject) parser.parse(response.toString());
        con.disconnect();
        return jsonResponse;

    }
    
    public boolean loginOnLine(String email, String password) throws IOException{
        
        URL obj = new URL("http://localhost:8000/api/log");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        String urlParameters = "email="+email+"&password="+password;
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("accept","Application/json");
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
        }
       
        StringBuffer response;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
           String inputLine;
           response = new StringBuffer();
           while ((inputLine = in.readLine()) != null) {
               response.append(inputLine);
           }
       }
       con.disconnect(); 
       JsonParser parser= new JsonParser();
       JsonObject Resp = (JsonObject) parser.parse(response.toString());
       return Resp.get("isAuth").getAsBoolean();
      
    }
            
}
