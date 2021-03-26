package io.lindx.sec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.lindx.sec.models.User;

@Repository
public class UserDaoImpl implements UserDao {

  @PersistenceContext(unitName = "entityManagerFactory")
	private EntityManager entityManager;


  @Override
  public User getUserById(Long id) {

    TypedQuery<User> query = entityManager.createQuery(

        "select u from User u where u.id =: id", User.class

    );

    return query.setParameter("id", id).getResultList().stream().findAny().orElse(null);
  }

  @Override
  public User getUserByMail(String email) {

    TypedQuery<User> query = entityManager.createQuery(

      "select u from User u where email =: email", User.class

    );
    
    return query.setParameter("email", email).getResultList().stream().findAny().orElse(null);
  }

  @Override
  public User getUserByName(String name) {
    
    TypedQuery<User> query = entityManager.createQuery(

      "select u from User u where name =: name", User.class

    );
    
    return query.setParameter("name", name).getResultList().stream().findAny().orElse(null);
  }

  @Override
  public Boolean setUser(User user) {

    entityManager.persist(user);

    return true;
  }

  @Override
  public void setPassword(Long id, String password) {

    User user = getUserById(id);
    user.setPassword(password);
    entityManager.merge(user);
  }

  @Override
  public List<User> getAllUser() {

    return entityManager.createQuery(

        "select u from User u", User.class

    ).getResultList();
  }
}
