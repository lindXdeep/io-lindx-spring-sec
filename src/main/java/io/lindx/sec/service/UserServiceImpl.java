package io.lindx.sec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.lindx.sec.dao.UserDaoImpl;
import io.lindx.sec.models.User;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

  @Autowired
  private UserDaoImpl userDao;

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

  /*
   * «Пользователь» – это просто Object. В большинстве случаев он может быть
   * приведен к классу UserDetails. Для создания UserDetails используется
   * интерфейс UserDetailsService, с единственным методом:
   */
  @Override
  public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

    return userDao.getByMail(mail);
  }
}
