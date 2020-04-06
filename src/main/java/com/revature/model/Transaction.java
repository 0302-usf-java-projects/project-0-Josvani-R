package com.revature.model;

public class Transaction {
  
  private int accountid;
  private int amount;
  private String descript;
  
  
  public Transaction(int accountid, int amount, String descript) {
    this.accountid = accountid;
    this.amount = amount;
    this.descript = descript;
  }
  
  public Transaction(int accountid, int amount) {
    this.accountid = accountid;
    this.amount = amount;
  }
  
  public Transaction() {
    super();
  }
  
  public int getAccountid() {
    return accountid;
  }
  
  public void setAccountid(int accountid) {
    this.accountid = accountid;
  }
  
  public int getAmount() {
    return amount;
  }
  
  public void setAmount(int amount) {
    this.amount = amount;
  }
  
  public String getDescript() {
    return descript;
  }
  
  public void setDescript(String descript) {
    this.descript = descript;
  }
  
  

}
