/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Frases;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Frases_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Autores;

/**
 *
 * @author DANIEL
 */
@Stateless
public class FrasesFacade extends AbstractFacade<Frases> {

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrasesFacade() {
        super(Frases.class);
    }

    public boolean isIdautorEmpty(Frases entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Frases> frases = cq.from(Frases.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(frases, entity), cb.isNotNull(frases.get(Frases_.idautor)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Autores findIdautor(Frases entity) {
        return this.getMergedEntity(entity).getIdautor();
    }
    
}
