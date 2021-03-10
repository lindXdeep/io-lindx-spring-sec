package io.lindx.sec.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import io.lindx.sec.models.User;

@Repository
public class UserDaoImpl implements UserDao {

  private static Integer count = 0;
  private List<User> users;

  {
    users = new ArrayList<>();

    users.add(new User(++count, "Tom", "email1@email.ru", "pass1"));
    users.add(new User(++count, "Bill", "email2@email.ru", "pass2"));
    users.add(new User(++count, "John", "email3@email.ru", "pass3"));
    users.add(new User(++count, "Lue", "email4@email.ru", "pass4"));
  }

  public UserDaoImpl() {
  }

  /**
   * get user by id.
   */
  @Override
  public User getUser(final Integer id) {

    return users.stream().filter(u -> u.getId() == id).findAny().orElse(null);
  }

  /**
   * get user by enail.
   */
  @Override
  public User getByMail(final String mail) {
    return users.stream().filter(u -> u.getMail().equals(mail)).findAny().orElse(null);
  }

  /**
   * add user.
   */
  @Override
  public void setUser(final User user) {
    user.setId(++count);
    users.add(user);
  }

  /**
   * get all users.
   */
  @Override
  public List<User> getAll() {
    return users;
  }

  /**
   * Update password for user.
   */
  @Override
  public void setPassword(final Integer id, final String password) {
    User user = getUser(id);
    user.setPassword(password);
  }

  /**
   * get user by name.
   */
  @Override
  public User getByName(final String name) {
    return users.stream().filter(u -> u.getName().equals(name)).findAny().orElse(null);
  }
}
