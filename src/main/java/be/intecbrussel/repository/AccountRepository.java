package be.intecbrussel.repository;

import be.intecbrussel.config.MySQLConfiguration;
import be.intecbrussel.model.Account;
import be.intecbrussel.model.User;

import java.sql.*;
import java.util.Optional;

public class AccountRepository {
    public boolean createAccount(Account account) {

        Connection connection = MySQLConfiguration.getConnection();

        try{

            Statement st = connection.createStatement();
            String query = String.format("INSERT INTO Account VALUES ('%s','%s');", account.getEmail(), account.getPassw());
            st.executeUpdate(query);
            connection.close();

        } catch(SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return true;
    }

    public Optional<Account> getAccount(String email){

//        String query = String.format("SELECT * FROM Account WHERE email like '%s'", email);
        boolean foundRecords = false;

        try (Connection connection = MySQLConfiguration.getConnection()){
            PreparedStatement pst = connection.prepareStatement("select * from account where email like ?");
            pst.setString(1,email);

            System.out.println(pst);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                return Optional.of(new Account(rs.getString(1), rs.getString(2)));
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }

        return Optional.empty();
    }
}
