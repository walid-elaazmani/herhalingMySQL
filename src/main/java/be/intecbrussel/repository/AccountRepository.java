package be.intecbrussel.repository;

import be.intecbrussel.model.Account;
import be.intecbrussel.model.User;

import java.sql.*;
import java.util.Optional;

public class AccountRepository {
    public boolean createAccount(Account account) {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountapp", "root", "V_521baf")){

            Statement st = connection.createStatement();
            String query = String.format("INSERT INTO Account VALUES ('%s' , '%s');", account.getEmail(), account.getPassw());
            st.executeUpdate(query);

            return true;

        } catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean getAccount(String email, String passw){

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountapp", "root", "V_521baf")){

            Statement st = connection.createStatement();
            String query = String.format("SELECT * FROM Account WHERE email = '%s' AND passw = '%s' ", email, passw);
            ResultSet rs = st.executeQuery(query);

            return rs.next(); // if found records = true else false

        } catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
