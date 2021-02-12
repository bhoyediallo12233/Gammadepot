/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teliyasante.models;

/**
 *
 * @author bhoyediallo
 */
public class User {
    private int id;
    private String originId;
    private String offcode;
    private String userApp;
    private boolean mcpwd ;
    private String email;
    private String password;
    private String status;
    private String employeeOriginId;
    private String name;
    private boolean sync;

    public User() {
    }

    public User(int id, String originId, String offcode, String userApp, boolean mcpwd, String email, String password, String status, String employeeOriginId, String name, boolean sync) {
        this.id = id;
        this.originId = originId;
        this.offcode = offcode;
        this.userApp = userApp;
        this.mcpwd = mcpwd;
        this.email = email;
        this.password = password;
        this.status = status;
        this.employeeOriginId = employeeOriginId;
        this.name = name;
        this.sync = sync;
    }
    

    public int getId() {
        return id;
    }

    public String getOriginId() {
        return originId;
    }

    public String getOffcode() {
        return offcode;
    }

    public String getUserApp() {
        return userApp;
    }

    public boolean isMcpwd() {
        return mcpwd;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getEmployeeOriginId() {
        return employeeOriginId;
    }

    public String getName() {
        return name;
    }

    public boolean isSync() {
        return sync;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public void setOffcode(String offcode) {
        this.offcode = offcode;
    }

    public void setUserApp(String userApp) {
        this.userApp = userApp;
    }

    public void setMcpwd(boolean mcpwd) {
        this.mcpwd = mcpwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployeeOriginId(String employeeOriginId) {
        this.employeeOriginId = employeeOriginId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
    
    
    
}
