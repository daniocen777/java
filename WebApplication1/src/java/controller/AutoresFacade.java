/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Autores;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Autores_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Frases;
import java.util.Collection;

/**
 *
 * @author DANIEL
 */
@Stateless
public class AutoresFacade extends AbstractFacade<Autores> {

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutoresFacade() {
        super(Autores.class);
    }

    public boolean isFrasesCollectionEmpty(Autores entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Autores> autores = cq.from(Autores.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(autores, entity), cb.isNotEmpty(autores.get(Autores_.frasesCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Frases> findFrasesCollection(Autores entity) {
        Autores mergedEntity = this.getMergedEntity(entity);
        Collection<Frases> frasesCollection = mergedEntity.getFrasesCollection();
        frasesCollection.size();
        return frasesCollection;
    }
    
}
