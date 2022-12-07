package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Workplace;


@ApplicationScoped
public class WorkspaceService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Workplace createWorkspace(Workplace workspace) {
        return entityManager.merge(workspace);
    }

    @Transactional
    public void deleteWorkspace(Integer id) {
        var entity = entityManager.find(WorkspaceService.class, id);
        entityManager.remove(entity);
    }

    public List<Workplace> findAll() {
        var query = entityManager.createQuery("FROM Category", Workplace.class);
        return query.getResultList();
    }
}