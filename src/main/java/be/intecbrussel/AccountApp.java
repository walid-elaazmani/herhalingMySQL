package be.intecbrussel;

import be.intecbrussel.model.Account;
import be.intecbrussel.model.User;
import be.intecbrussel.service.LoginService;
import be.intecbrussel.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AccountApp {
    public static void main(String[] args) {
        System.out.println("Hello visitor");
        System.out.println("1. register, 2. login, 3. add multiple users");
        Scanner scanner = new Scanner(System.in);
        int userchoice = scanner.nextInt();

        switch (userchoice){
            case 1:
                register();
                break;
            case 2 :
                login();
                break;
            case 3 :
                batchInsert();
                break;
            default:
                System.out.println("Wrong Input");
        }
    }

    private static void register(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("fname");
        String fname = scanner.nextLine();
        System.out.println("lname");
        String lname =scanner.nextLine();
        System.out.println("email");
        String email =scanner.nextLine();
        System.out.println("password");
        String passw = scanner.nextLine();
        LoginService loginService = new LoginService();
        boolean success = loginService.register(fname,lname, email, passw);
        if (success){
            System.out.printf("welcome %s %s", fname, lname);
        } else System.out.println("boom");
    }

    private static void login(){
        Scanner scanner = new Scanner(System.in);
        LoginService loginService = new LoginService();


       try{
           System.out.println("email");
           String email =scanner.nextLine();
           System.out.println("password");
           String passw = scanner.nextLine();

           Optional<User> user = loginService.logIn(email, passw);
           if(user.isPresent())
               System.out.print("welcome " + user.get().getFname() + " " + user.get().getLname());
           else System.out.println("user not found");
       } catch (RuntimeException e){
           System.out.println("Database error, check connection");
       }
    }

    private static void batchInsert(){

        List<User> usersList = new ArrayList<>();


        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {

            System.out.println("fname");
            String fname = scanner.nextLine();
            System.out.println("lname");
            String lname =scanner.nextLine();
            System.out.println("email");
            String email = scanner.nextLine();
            System.out.println("password");
            String passw = scanner.nextLine();

            User user = new User(fname,lname, new Account(email, passw));
            usersList.add(user);


        }

        LoginService loginService = new LoginService();

        loginService.registerManyUsers(usersList);

    }


}
