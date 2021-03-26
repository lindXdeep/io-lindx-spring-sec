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

  @Override
  public User getUser(final Long id) {
    return userDao.getUserById(id);
  }

  @Override
  public User getByMail(final String mail) {
    return userDao.getUserByMail(mail);
  }

  @Override
  public void setUser(final User user) {

    userDao.setUser(user);
  }

  @Override
  public List<User> getAll() {
    return userDao.getAllUser();
  }

  @Override
  public User getByName(final String name) {
    return userDao.getUserByName(name);
  }

  @Override
  public void setPassword(final Long id, final String password) {
    userDao.setPassword(id, password);
  }

  /*
   * «Пользователь» – это просто Object. В большинстве случаев он может быть
   * приведен к классу UserDetails. Для создания UserDetails используется
   * интерфейс UserDetailsService, с единственным методом:
   */
  @Override
  public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

    return userDao.getUserByMail(mail);
  }

}
