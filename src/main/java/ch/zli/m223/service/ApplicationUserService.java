package ch.zli.m223.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Member;

@ApplicationScoped
public class ApplicationUserService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Member createUser(Member user) {
        return entityManager.merge(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        var entity = entityManager.find(Member.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public Member updateUser(Long id, Member user) {
        user.setId(id);
        return entityManager.merge(user);
    }

    public List<Member> findAll() {
        var query = entityManager.createQuery("FROM ApplicationUser", Member.class);
        return query.getResultList();
    }

    public Optional<Member> findByEmail(String email) {
        return entityManager
                .createNamedQuery("ApplicationUser.findByEmail", Member.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }
}
