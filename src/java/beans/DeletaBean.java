
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
public class DeletaBean {

    int ID;
    String name;
    int mobilephone;
    String doctor;

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

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
    

    public DeletaBean() {
    }
       public void DeletaData(){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_INFO.DB_URL);
            String sql = "delete from Patients where ID= ?;";
//            String sql = "update into products values (?, ?, ?);";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,ID);
            preparedStatement.executeUpdate();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your record has been Deleat", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception ex)
        {
            String message = ex.getMessage();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
}
