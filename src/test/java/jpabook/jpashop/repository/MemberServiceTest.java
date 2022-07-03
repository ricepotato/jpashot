package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 테스트에서 사용될 때 완료시 rollback
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepositoryOld memberRepository;

    @Test
    @Rollback(false)
    public void testJoin() throws Exception {

        Member member = new Member();
        member.setName("kim");

        /* Rollback annotation 이 없는 경우에는 아래 join 을 실행해도
        * 실제 database 에 insert query 가 실행되지 않는다.
        * spring 은 기본적으로 테스트 후 database 를 rollback 하기 때문에
        * insert query 가 실행되지 않는다. */
        Long saveId = memberService.join(member);


        // em.flush(); // EntityManager 를 가져와서 flush 를 실행하면 실제 database 에 query 를 보낸다.
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void duplicateMemberError() throws Exception {
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        memberService.join(member2);

        fail("예외가 발생해야 한다.");
    }
}