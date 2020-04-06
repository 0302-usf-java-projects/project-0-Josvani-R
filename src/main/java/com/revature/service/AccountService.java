package com.revature.service;

import java.util.List;
import com.revature.exception.DuplicateUsernameException;
import com.revature.exception.PasswordNotValidException;
import com.revature.model.Account;
import com.revature.repository.AccountDao;


public class AccountService {
  
  private static final int REQUIRED_PASSWORD_LENGTH = 8;
  private AccountDao adao;
  
  {
    adao = new AccountDao();
    
  }
  
  public List<Account> getAllAccounts(){
    return adao.findAll();
    
  }
  public Account getbyId(int id) {
    return adao.findbyId(id);
  }
  
  public Account insert(String firstName, String lastName, String username,String password) {
    return adao.insert(new Account(0,firstName, lastName, username,password));
  }
  
  public Account getByUsername(String s) {
    return adao.findbyUserName(s);
  }
  
  public void updatePassword(String password) {
   List<Account> list = adao.findAll();
   Account a = list.get(list.size() -1);
   a.setPassword(password);
   adao.updatePassword(a);
  }
  
  public void update(String username) {
    List<Account> list = adao.findAll();
    Account a = list.get(list.size() -1);
    a.setUsername(username);
    adao.update(a);
  }
  
  public boolean checkPassword(String s) throws PasswordNotValidException {
    if(s.length()<REQUIRED_PASSWORD_LENGTH) {
      throw new PasswordNotValidException();
    }else {
      return true;
    }
  }
  
  public boolean checkUsername(String username) throws DuplicateUsernameException  {
    List<Account> list = adao.findAll();
    for(int i = 0; i< list.size();i++) {
      Account a = list.get(i);
      if (a.getUsername().equals(username)) {
        throw new DuplicateUsernameException();
      }
    }
    return true;
  }
  
  

}
