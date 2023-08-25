package be.intecbrussel;

import be.intecbrussel.model.User;
import be.intecbrussel.service.LoginService;
import be.intecbrussel.service.UserService;

import java.util.Optional;
import java.util.Scanner;

public class AccountApp {
    public static void main(String[] args) {
        System.out.println("Hello visitor");
        System.out.println("1. register, 2. login");
        Scanner scanner = new Scanner(System.in);
        int userchoice = scanner.nextInt();

        switch (userchoice){
            case 1:
                register();
                break;
            case 2 :
                login();
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
}
