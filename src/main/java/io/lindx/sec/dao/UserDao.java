package io.lindx.sec.dao;

import java.util.List;

import io.lindx.sec.models.User;

public interface UserDao {
  User getUserById(Long id);

  User getUserByMail(String mail);

  User getUserByName(String name);

  Boolean setUser(User user);

  void setPassword(Long id, String password);

  List<User> getAllUser();
}
