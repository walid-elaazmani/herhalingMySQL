package be.intecbrussel.repository;

import be.intecbrussel.config.MySQLConfiguration;
import be.intecbrussel.model.Account;
import be.intecbrussel.model.User;

import java.sql.*;
import java.util.Optional;

public class UserRepository {
    public boolean createUser(User user) {

        try(Connection connection = MySQLConfiguration.getConnection()){

            PreparedStatement st = connection.prepareStatement( "INSERT INTO User VALUES (?,?,?,?);");

            st.setLong(1, user.getId());
            st.setString(2, user.getFname());
            st.setString(3, user.getLname());
            st.setString(4, user.getAccount().getEmail());

            st.executeUpdate();

        } catch(SQLException e){
            System.out.println(e.getMessage());
            return false;

        }
        return true;
    }

    public Optional<User> getUser(Account account){

        try(Connection connection = MySQLConfiguration.getConnection()){

            PreparedStatement st = connection.prepareStatement("SELECT * FROM User WHERE accEmail like ?");
            st.setString(1, account.getEmail());

            ResultSet rs = st.executeQuery();

            if (rs.next()){

                return Optional.of(new User (rs.getLong(1), rs.getString(2),
                        rs.getString(3), account));
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
}
