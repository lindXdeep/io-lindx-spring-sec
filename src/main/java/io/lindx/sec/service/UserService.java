package io.lindx.sec.service;

import java.util.List;

import io.lindx.sec.models.User;

public interface UserService {
  User getUser(Long id);

  User getByMail(String mail);

  Boolean setUser(User user);

  void setPassword(Long id, String password);

  User getByName(String name);

  List<User> getAll();
}
