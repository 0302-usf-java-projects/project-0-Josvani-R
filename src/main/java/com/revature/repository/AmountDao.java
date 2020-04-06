package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.config.ConnectionUtil;

import com.revature.model.Amount;

public class AmountDao implements DaoContract<Amount,Integer> {

  @Override
  public List<Amount> findAll() {
    try(Connection conn = ConnectionUtil.connect()){
      String sql = "select * from amount";
      PreparedStatement ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      List<Amount> list = new ArrayList<>();
      while(rs.next()) {
        list.add(new Amount(rs.getInt(1), rs.getInt(2)));
       
      }
      return list;
      }catch (SQLException e) {
        e.printStackTrace();
      }
    return null;
  }

  @Override
  public Amount findbyId(Integer id) {
    try(Connection conn = ConnectionUtil.connect()){
      String sql = "select * from amount where account_id =?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      rs.next();
      return new Amount(rs.getInt(1), rs.getInt(2));
        
      }catch(SQLException e) {
        e.printStackTrace();
      }
    return null;
  }

  @Override
  public Amount findbyUserName(String s) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Amount insert(Amount t) {
    try (Connection conn = ConnectionUtil.connect()){
      String sql ="insert into amount values (?,?)";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, t.getAccountid());
      ps.setInt(2, t.getBalance());
      ps.execute();
      return findbyId(t.getAccountid());
    }catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public int updatePassword(Amount t) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int update(Amount t) {
    try (Connection conn = ConnectionUtil.connect()){
      String sql = "update amount set total = ? where account_id = ?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, t.getBalance());
      ps.setInt(2, t.getAccountid());
      int updated = ps.executeUpdate();
      ps.close();
      return updated;
    }catch(SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  @Override
  public List<Amount> findAllId() {
    // TODO Auto-generated method stub
    return null;
  }

//  @Override
//  public List<Amount> findAllId() {
//    try(Connection conn = ConnectionUtil.connect()){
//      String sql = "select account_id from amount";
//      PreparedStatement ps = conn.prepareStatement(sql);
//      ResultSet rs = ps.executeQuery();
//      List<Amount> list = new ArrayList<>();
//      while(rs.next()) {
//        list.add(new Amount(rs.getInt(1)));
//       
//      }
//      return list;
//      }catch (SQLException e) {
//        e.printStackTrace();
//      }
//    return null;
//  }
 


}

 
