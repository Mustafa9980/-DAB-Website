package beans;

import java.sql.*;
import javax.faces.application.*;
import javax.faces.bean.*;
import javax.faces.context.*;

@ManagedBean
@RequestScoped
public class Signup {

    String user;
    String pass;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Signup() {
    }

    public void SignupInsert() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_INFO.DB_URL);
            String Q = "insert into User values (?, ?);";
            preparedStatement = conn.prepareStatement(Q);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
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
