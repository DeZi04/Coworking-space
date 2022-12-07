package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@ApplicationScoped
public class WorkspaceService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public WorkspaceService createWorkspace(WorkspaceService workspace) {
        return entityManager.merge(workspace);
    }

    @Transactional
    public void deleteWrokspace(Long id) {
        var entity = entityManager.find(WorkspaceService.class, id);
        entityManager.remove(entity);
    }

    public List<WorkspaceService> findAll() {
        var query = entityManager.createQuery("FROM Category", WorkspaceService.class);
        return query.getResultList();
    }
}