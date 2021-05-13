package com.game.repository;

import com.game.entity.Parameters;
import com.game.entity.Player;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerExtendedRepositoryImpl implements PlayerExtendedRepository {

    @PersistenceContext
    private EntityManager em;

    /*   inner tools to organize code   */

    private class Criteria<T, X> {
        CriteriaBuilder cb;
        CriteriaQuery<T> cq;
        Root<X> root;

        public Criteria(Class<T> queryClass, Class<X> rootClass) {
            cb = em.getCriteriaBuilder();
            cq = cb.createQuery(queryClass);
            root = cq.from(rootClass);
        }
    }

    private <T> CriteriaQuery<T> qFilterBy (Parameters ps, CriteriaBuilder cb, CriteriaQuery<T> cq, Root<Player> root) {
        List<Predicate> filter = new ArrayList<>();

        if (ps.getName() != null) filter.add(cb.like( cb.lower(root.get("name")), "%" + ps.getName().toLowerCase() + "%" ));
        if (ps.getTitle() != null) filter.add(cb.like( cb.lower(root.get("title")), "%" + ps.getTitle() + "%" ));
        if (ps.getRace() != null) filter.add(cb.equal( root.get("race"), ps.getRace()));
        if (ps.getProfession() != null) filter.add(cb.equal( root.get("profession"), ps.getProfession() ));
        if (ps.getAfter() != null) filter.add(cb.greaterThanOrEqualTo( root.get("birthday"), ps.getAfter() ));
        if (ps.getBefore() != null) filter.add(cb.lessThanOrEqualTo( root.get("birthday"), ps.getBefore() ));
        if (ps.getBanned() != null) filter.add(cb.equal( root.get("banned"), ps.getBanned() ));
        if (ps.getMinExperience() != null) filter.add(cb.greaterThanOrEqualTo( root.get("experience"), ps.getMinExperience() ));
        if (ps.getMaxExperience() != null) filter.add(cb.lessThanOrEqualTo( root.get("experience"), ps.getMaxExperience() ));
        if (ps.getMinLevel() != null) filter.add(cb.greaterThanOrEqualTo( root.get("level"), ps.getMinLevel() ));
        if (ps.getMaxLevel() != null) filter.add(cb.lessThanOrEqualTo( root.get("level"), ps.getMaxLevel() ));

        cq.where(filter.toArray(new Predicate[0]));
        return cq;
    }

    /*   outer methods   */

    @Override
    public List<Player> getPlayersFiltered(Parameters ps, Pageable pageable) {
        Criteria<Player, Player> c = new Criteria<>(Player.class, Player.class);
        CriteriaQuery<Player> query = qFilterBy(ps, c.cb, c.cq, c.root);
        if (pageable != null) {
            query.orderBy(QueryUtils.toOrders(pageable.getSort(), c.root, c.cb));
            return em.createQuery(query).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
        } else return em.createQuery(query).getResultList();
    }

    @Override
    public Integer countPlayersFiltered(Parameters ps) {
        Criteria<Long, Player> c = new Criteria<>(Long.class, Player.class);
        CriteriaQuery<Long> countQ = qFilterBy(ps, c.cb, c.cq, c.root).select(c.cb.count(c.root));
        return em.createQuery(countQ).getSingleResult().intValue();
    }
}