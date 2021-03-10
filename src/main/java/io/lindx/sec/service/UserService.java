package io.lindx.sec.service;

import java.util.List;

import io.lindx.sec.models.User;

public interface UserService {
  User getUser(Integer id);
  User getByMail(String mail);
  void setUser(User user);
  void setPassword(Integer id, String password);
  User getByName(String name);
  List<User> getAll();
}
