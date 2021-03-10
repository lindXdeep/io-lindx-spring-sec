package io.lindx.sec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.lindx.sec.dao.UserDaoImpl;
import io.lindx.sec.models.User;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDaoImpl userDao;

  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * service method.
   */
  @Override
  public User getUser(final Integer id) {
    return userDao.getUser(id);
  }

  /**
   * service method.
   */
  @Override
  public User getByMail(final String mail) {
    return userDao.getByMail(mail);
  }

  /**
   * service method.
   */
  @Override
  public void setUser(final User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userDao.setUser(user);
  }

  /**
   * service method.
   */
  @Override
  public List<User> getAll() {
    return userDao.getAll();
  }

  /**
   * service method.
   */
  @Override
  public User getByName(final String name) {
    return userDao.getByName(name);
  }

  /**
   * service method.
   */
  @Override
  public void setPassword(final Integer id, final String password) {
    userDao.setPassword(id, password);
  }
}
