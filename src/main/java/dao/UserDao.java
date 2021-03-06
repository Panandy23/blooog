package dao;

import entity.*;
import io.vavr.collection.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

public class UserDao implements Dao<User, NewUser> {

    private final EntityManager em;

    public UserDao(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<User> getAll() {
        em.getTransaction().begin();
        Query q = em.createQuery("From UserEntity");
        List<User> result = List.ofAll(q.getResultStream()
                .map(e -> new User((UserEntity) e)));
        em.getTransaction().commit();
        return result;
    }

    @Override
    public Optional<User> get(long id) {
        return getEntity(id).map(user -> new User(user));
    }

    @Override
    public void save(NewUser obj) {

        em.getTransaction().begin();
        em.persist(new UserEntity(obj));
        em.getTransaction().commit();

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(User obj) {

    }

    private Optional<UserEntity> getEntity(long id){
        try {
            return Optional.of(em.createQuery("select a from UserEntity a where id = :id", UserEntity.class)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
