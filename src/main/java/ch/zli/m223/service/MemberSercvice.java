package ch.zli.m223.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.google.common.hash.Hashing;

import ch.zli.m223.model.Member;
import ch.zli.m223.model.RoleEnum;

@ApplicationScoped
public class MemberSercvice {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Member createMember(Member member) {
        if (findAll() == null) {
            member.setRole(RoleEnum.ADMIN);
        }
        member.setPassword(Hashing.sha512().hashString(member.getPassword(), StandardCharsets.UTF_8).toString()); 
        return entityManager.merge(member);
    }

    @Transactional
    public void deleteMember(Long id) {
        var entity = entityManager.find(Member.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public Member updateMember(Long id, Member member) {
        member.setId(id);
        return entityManager.merge(member);
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
