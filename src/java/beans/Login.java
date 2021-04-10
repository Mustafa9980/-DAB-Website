package beans;

import java.sql.*;
import javax.faces.application.*;
import javax.faces.bean.*;
import javax.faces.context.*;

@ManagedBean
@ApplicationScoped
public class Login {

    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login() {
    }

    public String checkLogin() throws SQLException, ClassNotFoundException, Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_INFO.DB_URL);
            String Q = "select * from User where Username = ? and Password= ?";
            preparedStatement = conn.prepareStatement(Q);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your record has been inserted", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return "Home.xhtml";
        } catch (Exception ex) {
            String message = "Not Alloud";
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        return null;
    }
}
