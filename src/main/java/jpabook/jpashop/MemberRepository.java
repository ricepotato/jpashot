package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        // command 와 query 를 분리함
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
