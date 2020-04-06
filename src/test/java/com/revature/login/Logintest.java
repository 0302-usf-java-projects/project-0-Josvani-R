package com.revature.login;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.revature.controller.Controller;

public class Logintest {
  private static Controller controller;
  
  @Before
  public void setup() {
    controller =  new Controller();
    
  }
  
  @After
  public void tearDown() {
    controller = null;
    
  }
  
  @Test
  public void loginsuccessful() {
    boolean result = controller.login();
    assertTrue(result == true);
  }
  

  @Test
  public void logoutsuccessful() {
    int result = controller.runLoginMenu();
    assertTrue(result == 0);
  }
}
