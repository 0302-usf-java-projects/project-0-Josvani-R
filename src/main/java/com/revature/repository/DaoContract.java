package com.revature.repository;

import java.util.List;


public interface DaoContract <T,I> {
  
  List<T> findAll();
  List<T> findAllId(); 
  T findbyId (I id);
  T findbyUserName(String s);
  T insert (T t);
  int updatePassword (T t);
  int update(T t);

}
