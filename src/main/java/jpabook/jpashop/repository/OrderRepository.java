package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOrder(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        if(orderSearch.getMemberName() == null && orderSearch.getOrderStatus() == null){
            return em.createQuery("select o from Order o", Order.class).setMaxResults(1000).getResultList();
        }else{
            return em.createQuery("select o from Order o join o.member m where o.status = :status and m.name like :name", Order.class)
                    .setParameter("status", orderSearch.getOrderStatus())
                    .setParameter("name", orderSearch.getMemberName())
                    .setMaxResults(1000)
                    .getResultList();
        }

    }
}
