package com.revature.model;





public class Account {
  
  private int accountId;
  private String firstName;
  private String lastName;
  private String username;
  private String password;
 
  
  
  public Account(int accountId, String firstName, String lastName, String username,String password) {
    this.setAccountId(accountId);
    this.setPassword(password);
    this.setUsername(username);
    this.setFirstName(firstName);
    this.setLastName(lastName);
  }

  public Account() {
    super();
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

 

  @Override
  public String toString() {
    return "Account [accountId=" + accountId + ", firstName=" + firstName + ", lastName=" + lastName
        + ", username=" + username + ", password=" + password + "]\n";
  }

  public int getAccountId() {
    return accountId;
  }


  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password)  {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username){
    
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public boolean checkLogin(String username, String password) {
    return this.username.equals(username) && this.password.equals(password);
  }
  
//  public boolean checkUsername(String username) throws DuplicateUsernameException  {
//    if (this.username.equals(username)) {
//      throw new DuplicateUsernameException();
//    }
//    return this.username.equals(username);
//  }
  
  

}
