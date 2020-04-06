package com.revature.service;

import java.util.List;
import com.revature.model.Amount;
import com.revature.repository.AmountDao;

public class AmountService {
  
  private AmountDao amdao;
  
  {
    amdao = new AmountDao();
    
  }
  
  public List<Amount> getAllAmounts(){
    return amdao.findAll();
  }
  
  public Amount getbyId(int id) {
    return amdao.findbyId(id);
  }
  
  public Amount insert(int accountId, int balance) {
    return amdao.insert(new Amount(accountId,balance));
  }
  
  public void update(int accountId, int balance) {
    List<Amount> list = amdao.findAll();
    for(int i = 0; i< list.size();i++) {
    Amount a = list.get(i);
    if(a.getAccountid()==(accountId)) {
      a.setBalance(balance);
      amdao.update(a);
      } 
    }
  }
}
