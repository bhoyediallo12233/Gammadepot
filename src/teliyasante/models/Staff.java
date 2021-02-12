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
public class Staff {
    private Integer id;
    private String offcode;
    private String originId;
    private int userId;
    private String title;
    private String firstname;
    private String lastname;
    private String type;
    private String phone;
    private String specialiste;
    private int partnerId;
    private String partnerName;
    private int insuranceId;
    private String insuranceName;
    private String status;
    private boolean sync;

    public Staff() {
    }

    public Staff(Integer id, String offcode, String originId, int userId, String title, String firstname, String lastname, String type, String phone, String specialiste, int partnerId, String partnerName, int insuranceId, String insuranceName, String status, boolean sync) {
        this.id = id;
        this.offcode = offcode;
        this.originId = originId;
        this.userId = userId;
        this.title = title;
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
        this.phone = phone;
        this.specialiste = specialiste;
        this.partnerId = partnerId;
        this.partnerName = partnerName;
        this.insuranceId = insuranceId;
        this.insuranceName = insuranceName;
        this.status = status;
        this.sync = sync;
    }

    public int getId() {
        return id;
    }

    public String getOffcode() {
        return offcode;
    }

    public String getOriginId() {
        return originId;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getSpecialiste() {
        return specialiste;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public String getStatus() {
        return status;
    }

    public boolean isSync() {
        return sync;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOffcode(String offcode) {
        this.offcode = offcode;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSpecialiste(String specialiste) {
        this.specialiste = specialiste;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
    
    
}
