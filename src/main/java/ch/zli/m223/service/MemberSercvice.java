package ch.zli.m223.service;

import java.io.Console;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;

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
        } else {
            member.setRole(RoleEnum.MEMBER);
        }
        if (!Pattern.matches("^[a-zA-Z0-9]{6,}$", member.getPassword())  ) {
            throw new BadRequestException("Please use a stronger password.");
        }
        member.setPassword(Hashing.sha512().hashString(member.getPassword(), StandardCharsets.UTF_8).toString()); 
        return entityManager.merge(member);
    }

    @Transactional
    public void deleteMember(Long id) {
        var entity = entityManager.find(Member.class, id);
        entity.getEntries().forEach(e -> entityManager.remove(e));
        entityManager.remove(entity);
    }

    @Transactional
    public Member updateMember(Long id, Member member) {
        member.setId(id);
        return entityManager.merge(member);
    }

    public List<Member> findAll() {
        var query = entityManager.createQuery("FROM Member", Member.class);
        return query.getResultList();
    }

    public Optional<Member> findByEmail(String email) {
        return entityManager
                .createNamedQuery("Member.findByEmail", Member.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }
}
