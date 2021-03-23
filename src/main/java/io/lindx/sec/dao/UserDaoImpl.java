package io.lindx.sec.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import io.lindx.sec.models.Role;
import io.lindx.sec.models.User;

@Repository
public class UserDaoImpl implements UserDao {

  private static Integer count = 0;

  private final Map<String, User> users;

  {
    users = new HashMap<>();
    
    users.put("user@u", new User(++count, "Tom", "user@u", "user", Collections.singleton(new Role(count,"ROLE_ADMIN"))));
    users.put("user1@u", new User(++count, "Bill", "user1@u", "u1", Collections.singleton(new Role(count,"ROLE_USER"))));
    users.put("user2@u", new User(++count, "John", "user2@u", "u2", Collections.singleton(new Role(count,"ROLE_USER"))));
    users.put("user3@u", new User(++count, "Lue", "user3@u", "u3", Collections.singleton(new Role(count,"ROLE_USER"))));
  }

  /**
   * get user by id.
   */
  @Override
  public User getUser(final Integer id) {

    return getAll().stream().filter(u -> u.getId() == id).findAny().orElse(null);
  }

  /**
   * get user by enail.
   */
  @Override
  public User getByMail(final String mail) {

    if (!users.containsKey(mail)) {
      return null;
    }

    return users.get(mail);
  }

  /**
   * add user.
   */
  @Override
  public void setUser(final User user) {

    user.setId(++count);
    users.put(user.getMail(), user);
  }

  /**
   * get all users.
   */
  @Override
  public List<User> getAll() {

    return new ArrayList<User>(users.values());
  }

  /**
   * Update password for user.
   */
  @Override
  public void setPassword(final Integer id, final String password) {

    User user = getUser(id);
    user.setPassword(password);

    users.put(user.getMail(), user);
  }

  /**
   * get user by name.
   */
  @Override
  public User getByName(final String name) {

    return getAll().stream().filter(u -> u.getName().equals(name)).findAny().orElse(null);
  }
}
