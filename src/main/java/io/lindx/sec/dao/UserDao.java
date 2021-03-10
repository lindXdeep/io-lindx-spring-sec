package io.lindx.sec.dao;

import java.util.List;

import io.lindx.sec.models.User;

interface UserDao {
  User getUser(Integer id);
  User getByMail(String mail);
  User getByName(String name);
  void setUser(User user);
  void setPassword(Integer id, String password);
  List<User> getAll();
}
