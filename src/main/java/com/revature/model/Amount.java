package com.revature.model;

public class Amount {
  
  private int accountid;
  private int balance;
  
  
  public int getAccountid() {
    return accountid;
  }
  

  public Amount(int accountid, int balance) {
    this.accountid = accountid;
    this.balance = balance;
  }
  
  
  @Override
  public String toString() {
    return "Amount [accountid=" + accountid + ", balance=" + balance + "]\n";
  }

  public void setAccountid(int accountid) {
    this.accountid = accountid;
  }
  
  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }
  
  

}
