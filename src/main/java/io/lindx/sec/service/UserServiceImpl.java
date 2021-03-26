package io.lindx.sec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import io.lindx.sec.dao.UserDao;
import io.lindx.sec.models.User;

@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

  @Autowired
  private UserDao userDao;

  @Override
  public User getUser(final Long id) {
    return userDao.getUserById(id);
  }

  @Override
  public User getByMail(final String email) {
    return userDao.getUserByMail(email);
  }

  @Override
  @Transactional
  public Boolean setUser(final User user) {

    userDao.setUser(user);

    return true;
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
  @Transactional
  public void setPassword(final Long id, final String password) {
    userDao.setPassword(id, password);
  }

  /*
   * «Пользователь» – это просто Object. В большинстве случаев он может быть
   * приведен к классу UserDetails. Для создания UserDetails используется
   * интерфейс UserDetailsService, с единственным методом:
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    User user = userDao.getUserByMail(email);
    
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    return user;
  }
}
