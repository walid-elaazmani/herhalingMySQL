package be.intecbrussel.repository;

import be.intecbrussel.config.MySQLConfiguration;
import be.intecbrussel.model.Account;
import be.intecbrussel.model.User;

import java.sql.*;
import java.util.Optional;

public class UserRepository {
    public boolean createUser(User user) {


        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountapp", "root", "V_521baf")){

            Statement st = connection.createStatement();
            String query = String.format("INSERT INTO User VALUES ('%d','%s','%s','%s')", user.getId(),user.getFname(), user.getLname(), user.getAccount().getEmail());
            st.executeUpdate(query);

            return true;

        } catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Optional<User> getUser(String email, String passw){

        try(Connection connection = MySQLConfiguration.getConnection()){

            PreparedStatement st = connection.prepareStatement("SELECT * FROM User WHERE accEmail like ?");
            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            while (rs.next()){
                return Optional.of(new User (rs.getLong(1), rs.getString(2),
                        rs.getString(3), new Account(email, passw)));
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
}
