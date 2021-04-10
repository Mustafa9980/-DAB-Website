/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.*;
import java.text.*;
import javax.faces.application.*;
import javax.faces.bean.*;
import javax.faces.context.*;

/**
 *
 * @author mut22
 */
@ManagedBean
@RequestScoped
public class NewBean {

    int ID;
    String name;
    int mobilephone;
    String Date;
    String Time;
    String doctor;
    String Specialization;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(int mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String Specialization) {
        this.Specialization = Specialization;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public NewBean() {
    }

    public void InsertData() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_INFO.DB_URL);
            String sql = "insert into Patients values (?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, ID);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, mobilephone);
            preparedStatement.setString(4, Date);
            preparedStatement.setString(5, Time);
            preparedStatement.setString(6, Specialization);
            preparedStatement.setString(7, doctor);

            preparedStatement.executeUpdate();

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your record has been inserted", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception ex) {
            String message = ex.getMessage();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

}
