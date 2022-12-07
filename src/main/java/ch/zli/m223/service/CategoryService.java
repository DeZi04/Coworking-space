package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Workspace;

@ApplicationScoped
public class CategoryService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Workspace createCategory(Workspace category) {
        return entityManager.merge(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        var entity = entityManager.find(Workspace.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public Workspace updateCategory(Long id, Workspace category) {
        category.setId(id);
        return entityManager.merge(category);
    }

    public List<Workspace> findAll() {
        var query = entityManager.createQuery("FROM Category", Workspace.class);
        return query.getResultList();
    }
}