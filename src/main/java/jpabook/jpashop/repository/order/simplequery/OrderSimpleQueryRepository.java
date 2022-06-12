package jpabook.jpashop.repository.order.simplequery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {
    /* api spec 에 맞춘 query 인 경우 package 를 따로 작성한다.
    * repository 는 순수 entity 를 조회하는 용도로 사용한다. */
    private final EntityManager em;
    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery("select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) from Order o join o.member m join o.delivery d", OrderSimpleQueryDto.class).getResultList();
    }
}
