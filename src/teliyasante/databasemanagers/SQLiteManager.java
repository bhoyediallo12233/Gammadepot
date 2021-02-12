/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teliyasante.databasemanagers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import teliyasante.models.User;
import teliyasante.outils.Global;


public class SQLiteManager {
    
    private static Connection conn;
    private static boolean hasData = false;
    public static String dbName;
  
     private static void getConnection() throws ClassNotFoundException, SQLException
    {
        if(conn != null) conn.close();
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbName);
        initialise();
    } 
    
    private static  void initialise() throws SQLException
    {
        if(!hasData)
        {
            hasData = true; 
            
            Statement stm = conn.createStatement();
            ResultSet rts = stm.executeQuery("select name from sqlite_master where type = 'table' and name = 'user'");
            if(!rts.next())
            {
                System.err.println("Doing migration");
                Statement stm1 = conn.createStatement();
                
                stm1.execute("create table user(id integer primary key, originId varchar(50), "
                        + "offcode varchar(50), userApp varchar(100), mcpwd boolean, "
                        + "email varchar(100), password varchar(50), status varchar(50), "
                        + "employeeOriginId varchar(50), name varchar(500), "
                        + "sync boolean);");
                
                stm1.execute("create table prestation(id integer primary key, onlid interger, "
                        + "offcode varchar(50), partnerId varchar(20), partnerName varchar(50), type varchar(50), "
                        + "insuredNumber varchar(50), insuredName varchar(100), "
                        + "staffId integer, "
                        + "total integer, insurerCost integer, "
                        + "coverage interger, restPlafond integer, "
                        + "insuranceName varchar(50), prestationId varchar(20), "
                        + "acts varchar(500), affections varchar(500), exams varchar(500), products varchar(500), "
                        + "status varchar(50), sync boolean);"
                );
                
//                stm1.execute("create table phToSave(id integer, infobase varchar(500), products varchar(500), status varchar(50), sync boolean, primary key(id));");
//                
//                stm1.execute("create table lbToSave(id integer, infobase varchar(500), exams varchar(500), status varchar(50), sync boolean, primary key(id));");

                stm1.execute("create table specialite(id integer primary key, offcode varchar(50), "
                        + "name varchar(100), sync boolean);");

                stm1.execute("create table staff(id integer primary key, offcode varchar(50), originId varchar(50), userId Integer, "
                        + "title varchar(50), firstname varchar(100), lastname varchar(100), type varchar(50), phone varchar(50), "
                        + "specialite varchar(100),  partnerId Integer, partnerName varchar(100), "
                        + "insuranceId Integer, insuranceName varchar(100), "
                        + "status varchar(50), sync boolean);");
                
                stm1.execute("create table act(id integer primary key, onlId varchar(50), offcode varchar(50), "
                        + "name varchar(100), type varchar(50), category varchar(100), subcategory varchar(100), "
                        + "status varchar(50), sync boolean);");
                
                stm1.execute("create table category(id integer primary key, offcode varchar(50), "
                        + "name varchar(100), type varchar(50), "
                        + "status varchar(50), sync boolean);");
                
                stm1.execute("create table affection(id integer primary key, offcode varchar(50), "
                        + "name varchar(500), "
                        + "status varchar(50), sync boolean);");
                
//                stm1.execute("create table exam(id integer primary key, offcode varchar(50), "
//                        + "name varchar(100), categoryId varchar(50), "
//                        + "status varchar(50), sync boolean);");
                
                stm1.execute("create table product(id integer primary key, offcode varchar(50), "
                        + "name varchar(100), "
                        + "status varchar(50), sync boolean);");
                
                stm1.execute("create table partneract(id integer primary key, actId Integer, offcode varchar(50), "
                        + "cost integer, status varchar(50), sync boolean);");
                
                stm1.execute("create table fingertemplate(id integer primary key, offcode varchar(50), "
                        + "insuredNumber varchar(50), fingerIndex integer, fingerprint varchar(1200), "
                        + "sync boolean);");
                
                stm1.execute("create table medicalshortdata(id integer primary key, offcode varchar(50), "
                        + "insuredNumber varchar(50), info varchar(1000), "
                        + "sync boolean);");
            }
        }
    }
    
    
    
    public void insertUserdata() throws SQLException, ClassNotFoundException{
        Global global= new Global();
        getConnection();
        try {
            JsonObject json= new JsonObject();
            URL url= new URL("http://localhost:8000/api/liste");
            json = global.getList(url);
            JsonParser parser = new JsonParser();
            JsonObject data_obj = (JsonObject) parser.parse(json.toString());
            JsonArray arr = (JsonArray) data_obj.get("data");
            for (int i = 0; i <arr.size(); i++) {
                
                JsonObject new_obj = (JsonObject) arr.get(i);
                //User user= new User();
                // user.setName(new_obj.get("name").getAsString());
                 int id= Integer.parseInt (new_obj.get("id").toString ());
                 String nm= new_obj.get("name").getAsString();
                 String em= new_obj.get("email").getAsString ();
                 String pw= new_obj.get("passwords").getAsString();
                 String uapp= new_obj.get("user_app").getAsString();
                 PreparedStatement pstm = conn.prepareStatement("insert into user(id,userapp,email,password,name) " 
                         +"values(?,?,?,?,?);"); 
                 pstm.setInt(1,id);
                 pstm.setString(2,uapp);
                 pstm.setString(3,em);
                 pstm.setString(4,pw);
                 pstm.setString(5,nm);
                 pstm.execute();
                 
                 }  
            
            
        } catch (Exception ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public void insertStaffdata() throws SQLException, ClassNotFoundException{
    Global global= new Global();
    getConnection();
    try {
        JsonObject json= new JsonObject();
        URL url= new URL("http://localhost:8000/api/employeelist");
        json = global.getList(url);
        JsonParser parser = new JsonParser();
        JsonObject data_obj = (JsonObject) parser.parse(json.toString());
        JsonArray arr = (JsonArray) data_obj.get("data");
        for (int i = 0; i <arr.size(); i++) {

            JsonObject new_obj = (JsonObject) arr.get(i);
            //User user= new User();
            // user.setName(new_obj.get("name").getAsString());
             int id= Integer.parseInt (new_obj.get("id").toString ());
             int uid=Integer.parseInt (new_obj.get("access_id").toString ());
             String fm= new_obj.get("firstname").getAsString();
             String lm= new_obj.get("lastname").getAsString ();
             String ph= new_obj.get("phone").getAsString();
             int pid= new_obj.get("partner_id").getAsInt();
             PreparedStatement pstm = conn.prepareStatement("insert into staff(id,userId,firstname,lastname,phone,partnerId) " 
                     +"values(?,?,?,?,?,?);"); 
             pstm.setInt(1,id);
             pstm.setInt(2,uid);
             pstm.setString(3,fm);
             pstm.setString(4,lm);
             pstm.setString(5,ph);
             pstm.setInt(6,pid);
             pstm.execute();

             }  


    } catch (Exception ex) {
        Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
    }

}
    
    public boolean longinUser(String email,String pass) throws ClassNotFoundException, SQLException{
        getConnection();
        PreparedStatement pstm=null;
        ResultSet res=null;
        String query="select * from user where email=? and password=?";
        try{
            pstm=conn.prepareStatement(query);
            pstm.setString(1,email);
            pstm.setString(2,pass);
            res=pstm.executeQuery();
            if(res.next())
               return true;
            else 
                return false;
           }catch(Exception e){
            return false;
        }
            
        
    }
    
    public void OffLineDataIsLoaded(){
        
    }
     
    public  boolean dbaseCreated(){
        File f=new File(dbName);
        return f.exists();
    }
    public  ResultSet getStaff() throws ClassNotFoundException, SQLException
    {
        getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("select * from staff");
        return rs;
    }
    
    public void addStaffMember(String fname, String lname, String phone) throws SQLException, ClassNotFoundException{
        getConnection();
        PreparedStatement pstm = conn.prepareStatement("insert into staff(firstname,lastname,phone) " 
        +"values(?,?,?);"); 
         
         pstm.setString(1,fname);
         pstm.setString(2,lname);
         pstm.setString(3,phone);
         
         pstm.execute(); 
    }
    
    
}
