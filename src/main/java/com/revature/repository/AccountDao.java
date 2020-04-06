package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.config.ConnectionUtil;
import com.revature.model.Account;

public class AccountDao implements DaoContract<Account, Integer> {

  @Override
  public List<Account> findAll() {
    try(Connection conn = ConnectionUtil.connect()){
      String sql = "select * from accounts order by id asc";
      PreparedStatement ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      List<Account> list = new ArrayList<>();
      while(rs.next()) {
        list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5)));
       
      }
      return list;
      }catch (SQLException e) {
        e.printStackTrace();
      }
    return null;
  }

  @Override
  public Account findbyId(Integer id) {
    try(Connection conn = ConnectionUtil.connect()){
        String sql = "select * from account where id =?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Account(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5));
          
        }catch(SQLException e) {
          e.printStackTrace();
        }
    return null;
  }

  @Override
  public Account findbyUserName(String s) {
    try (Connection conn = ConnectionUtil.connect()) {
      String sql ="select * from accounts where username = ?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, s);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        return new Account(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5));

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
    
  }

  @Override
  public Account insert(Account t) {
    try (Connection conn = ConnectionUtil.connect()){
      String sql ="insert into accounts (firstname,lastname,username,password) values (?,?,?,?)";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, t.getFirstName());
      ps.setString(2, t.getLastName());
      ps.setString(3, t.getUsername());
      ps.setString(4, t.getPassword());
      ps.execute();
      return findbyUserName(t.getUsername());
    }catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public int updatePassword(Account t) {
    try (Connection conn = ConnectionUtil.connect()){
      String sql = "update accounts set password = ? where id = ?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, t.getPassword());
      ps.setInt(2, t.getAccountId());
      int updated = ps.executeUpdate();
      ps.close();
      return updated;
    }catch(SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  @Override
  public int update(Account t) {
    try (Connection conn = ConnectionUtil.connect()){
      String sql = "update accounts set username = ? where id = ?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, t.getUsername());
      ps.setInt(2, t.getAccountId());
      int updated = ps.executeUpdate();
      ps.close();
      return updated;
    }catch(SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  @Override
  public List<Account> findAllId() {
    // TODO Auto-generated method stub
    return null;
  }


}
