package io.lindx.sec.dao;

import java.util.List;

import io.lindx.sec.models.User;

interface UserDao {
  User getUserById(Long id);

  User getUserByMail(String mail);

  User getUserByName(String name);

  void setUser(User user);

  void setPassword(Long id, String password);

  List<User> getAllUser();
}
