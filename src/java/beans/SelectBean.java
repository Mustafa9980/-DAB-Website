/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.LinkedList;

/**
 *
 * @author mut22
 */
@ManagedBean
@RequestScoped
public class SelectBean {

    LinkedList<clinc> clinData;

    public LinkedList<clinc> getClinData() {
        return clinData;
    }

    public void setClinData(LinkedList<clinc> clinData) {
        this.clinData = clinData;
    }

    public SelectBean() throws Exception {
        clinData = new LinkedList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_INFO.DB_URL);
            String sql = "SELECT * FROM Patients";
            preparedStatement = conn.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                clinc e = new clinc();
                e.setID(rs.getInt("ID"));
                e.setName(rs.getString("Name"));
                e.setMobilephone(rs.getInt("MobliePhone"));
                e.setDate(rs.getString("Date"));
                e.setTime(rs.getString("Time"));
                e.setSpecialization(rs.getString("Specialization"));
                e.setDoctor(rs.getString("Doctor"));
                clinData.add(e);
            }
        } catch (Exception ex) {
            String message = ex.getMessage();
            FacesMessage facesMessage = new FacesMessage(message);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

}
