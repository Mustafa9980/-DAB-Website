package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class UpdateBean {

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

    public UpdateBean() {
    }

    public void UpdateData() {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.sqlite.JDBC");//مهمه
            conn = DriverManager.getConnection(DB_INFO.DB_URL);
            String sql = "update Patients set  Name = ?, MobliePhone= ?, Date = ?,  Time = ?, Specialization= ? ,Doctor= ? where ID = ?";
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, mobilephone);
            preparedStatement.setString(3, Date);
            preparedStatement.setString(4, Time);
            preparedStatement.setString(5, Specialization);
            preparedStatement.setString(6, doctor);
            preparedStatement.setInt(7, ID);
            
            preparedStatement.executeUpdate();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your record has been updated", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception ex) {
            String message = ex.getMessage();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

    }
}
