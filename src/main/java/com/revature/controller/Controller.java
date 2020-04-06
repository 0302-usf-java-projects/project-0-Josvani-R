package com.revature.controller;

import java.util.Scanner;
import org.apache.log4j.Logger;
import com.revature.exception.DuplicateUsernameException;
import com.revature.exception.PasswordNotValidException;
import com.revature.model.Account;
import com.revature.model.Amount;
import com.revature.service.AccountService;
import com.revature.service.AmountService;


public class Controller {
  static Logger log = Logger.getLogger(Controller.class);
  private static Scanner sc = new Scanner(System.in);
  private static boolean LoggedIn = false;
  private static boolean Adminlog = false;
  private String user= new String();
  
  private static AccountService as;
  private static AmountService bs;
  
  {
    bs = new AmountService();
    as = new AccountService();
  }

  public Controller() {
    super();
  }
 
  

  public void welcomeMenu() {
    while(!LoggedIn) {
      int menuOutput = runWelcomeMenu();
      if(menuOutput == 0) {
        break;
      }
    
    
     while(Adminlog & LoggedIn) {
        int userOutput = adminMenu();
        if(userOutput == 0) {
          break;
        }         
      }
   
     while(LoggedIn) {
       int userOutput = runLoginMenu();
       if(userOutput == 0) {
         break;
       }
     }
    }
  }  
  
  
  
  public int runWelcomeMenu() {
    System.out.println("++++Welcome to Vault-Tec Cap Storage Reserve++++");
    System.out.println("                  +++VCSR+++");
    System.out.println("       Input number to select option" );
    System.out.println("                  1: Login");
    System.out.println("                  2: Register account");
    System.out.println("                  0: exit terminal");
    
    String userOption = sc.nextLine();
    switch(userOption) {
      case "1":
        login();
        return 1;
      case "2":
        register();
        return 1;
      case"0":
        return 0;
      default:
        System.out.println("Failed to recoginze option");
         return 1;
    }

}
  public static void register() {
    System.out.println("VCSR Account Registration");
    
      System.out.println("Please provide a Firstname");
      String firstName = sc.nextLine();
      
      System.out.println("Please provide a Lastname");
      String lastName = sc.nextLine();
     
      System.out.println("Please provide a username");
      String username = sc.nextLine();
      
      
      boolean checkuname = false;
      while(!checkuname) {
        try{
            as.checkUsername(username);
            checkuname = as.checkUsername(username);
        } catch (DuplicateUsernameException e) {
          log.error("Username already in use");
          System.out.println("Create new Username");
          System.out.println("USERNAME:");
          username =sc.nextLine();
          checkuname = false;
        }
        }

      System.out.println("Please provide a Password");
      String password = sc.nextLine();
      
      Account a = as.insert(firstName, lastName, username,password);
      Amount am = bs.insert(a.getAccountId(),0);
      
      
      
      
      
      boolean check = false;
      while(!check) {
      try{
          as.checkPassword(password);
          check = as.checkPassword(password);
          log.info("Registration successful for" + a.getUsername());
      } catch (PasswordNotValidException e) {
        log.error("Password not vaild");
        System.out.println("Create a longer password");
        System.out.println("PASSWORD:");
        password =sc.nextLine();
        as.updatePassword(password);
        check = false;
      }
      } 
      

  }

  
  public boolean login() {
    System.out.println("USERNAME:");
    String username = sc.nextLine();
    
    System.out.println("PASSWORD:");
    String password = sc.nextLine();
    
  
    Account a = as.getByUsername(username);
    if(a==null) {
      log.assertLog(LoggedIn, "login failed");
      System.out.println("Sorry doesnt seem like you're registered with VCSR");
      System.out.println("Register today to keep your CAPS safe");
      System.out.println("We have thousands of access points around the wasteland!");
      return LoggedIn;
    }else if(a.checkLogin(username, password)) {
      log.info("Login successful");
      LoggedIn= true;
      user = username;
      if(user.equals("Admin")) {
        Adminlog = true;
        adminMenu();
      }
      return LoggedIn;
    }else {
      log.assertLog(LoggedIn, "login failed");
      System.out.println("Sorry doesnt seem like you're password is correct");
      System.out.println("Register today to keep your CAPS safe");
      System.out.println("We have thousands of access points around the wasteland!");
    }
    return LoggedIn;
  }
  
  public int runLoginMenu() {
    System.out.println("      +++++++++ Welcome " + user + " ++++++++++");
    Account a = as.getByUsername(user);
    System.out.println("Account # " +a.getAccountId());
    System.out.println("                  +++VCSR+++");
    System.out.println("           Input number to select option" );
    System.out.println("                  1: View Total CAPS");
    System.out.println("                  2: Withdraw CAPS");
    System.out.println("                  3: Deposit CAPS");
    System.out.println("                  4: Transfer CAPS");
    System.out.println("                  0: Logout");
    
   
    String userOption = sc.nextLine();
    switch(userOption) {
      case "1":
        viewCaps();
        return 1;
      case "2":
        withdraw();
        return 1;
      case "3":
        desposit();
        return 1;
      case "4":
        transfer();
        return 1;
      case"0":
        LoggedIn = false;
        return 0;
      default:
        System.out.println("Failed to recoginze option");
         return 1;
    }
    
   
  }
  
  public int adminMenu() {
    System.out.println("      +++++++++ Welcome Big Boi ++++++++++");
    Account a = as.getByUsername(user);
    Amount am = bs.getbyId(a.getAccountId());
    System.out.println("Account: "+ user);
    System.out.println("                  +++VCSR+++");
    System.out.println("       Input number to select option" );
    System.out.println("                  1: View all accounts");
    System.out.println("                  2: View account amounts");
//    System.out.println("                  3: view all transactions ");
//    System.out.println("                  4: Edit accounts");
    System.out.println("                  0: Logout");
    
    String userOption = sc.nextLine();
    switch(userOption) {
      case "1":
       System.out.println(as.getAllAccounts());
        return 1;
      case "2":
        System.out.println(bs.getAllAmounts());
        return 1;
      case"0":
        LoggedIn= false;
        Adminlog = false;
        return 0;
      default:
        System.out.println("Failed to recoginze option");
         return 1;
   
    }
  }
  
  public void viewCaps() {
    Account a = as.getByUsername(user);
    Amount am = bs.getbyId(a.getAccountId());
    System.out.println("Total CAPS: " + am.getBalance());
  }
  
  public void withdraw() {
    Account a = as.getByUsername(user);
    Amount am = bs.getbyId(a.getAccountId());
    
    int total = am.getBalance();
    System.out.println("Total CAPS: " + total);
    System.out.println("How much would like to take? ");
    String input = sc.nextLine();
    int amount = Integer.parseInt(input);
    if(amount > total) {
      System.out.println("Who do you think you are?");
      System.out.println("you dont have much");
      System.out.println("Returning to login menu");
    }else {
      total = total-amount;
      bs.update(a.getAccountId(), total);
    }
      
  }
  
  public void desposit() {
    Account a = as.getByUsername(user);
    Amount am = bs.getbyId(a.getAccountId());
    
    int total = am.getBalance();
    System.out.println("Total CAPS: " + total);
    System.out.println("How much would like to deposit?");
    String input = sc.nextLine();
    int amount = Integer.parseInt(input);
    total = total + amount;
    bs.update(a.getAccountId(), total);

  }
  
  public void transfer() {
    Account a = as.getByUsername(user);
    Amount am = bs.getbyId(a.getAccountId());
    int total = am.getBalance();
    System.out.println("How much would like to transfer?");
    String input = sc.nextLine();
    int amount = Integer.parseInt(input);
    if(amount > total) {
      System.out.println("Who do you think you are?");
      System.out.println("you dont have much");
      System.out.println("Returning to login menu");
    }else {
      total = total-amount;
      bs.update(a.getAccountId(), total);
      System.out.println("To which account username?");
      String username= sc.nextLine();
      
      Account b = as.getByUsername(username);
      Amount amto = bs.getbyId(b.getAccountId());
      
      int total2 = amto.getBalance();
      int newtotal = total2 + amount;
      bs.update(b.getAccountId(), newtotal);
    }
      
  }



  
}
