package com.study.datajpa.repository;

import com.study.datajpa.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.statements.SpringRepeat;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired MemberJpaRepository memberJpaRepository;

    @Test
    @Transactional
    public void testMember() {
        Member member = new Member("memberA");
        Member savedMember = memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findOne(savedMember.getId());
        assertThat(findMember.getId()).isEqualTo(savedMember.getId());
    }

    @Test
    public void basicCRUD(){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        List<Member> all = memberJpaRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        long count =  memberJpaRepository.count();
        assertThat(count).isEqualTo(2);

        memberJpaRepository.delete(findMember1);
        memberJpaRepository.delete(findMember2);

        long deleteCount =  memberJpaRepository.count();
        assertThat(deleteCount).isEqualTo(0);
    }

    @Test
    public void findUsernameAndAgeGreaterThen(){
        Member memberA = new Member("AAA", 10);
        Member memberB = new Member("AAA", 20);
        memberJpaRepository.save(memberA);
        memberJpaRepository.save(memberB);

        List<Member> result = memberJpaRepository.findUsernameAndAgeGreaterThen("AAA", 15);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void testNameQuery(){
        Member memberA = new Member("AAA", 10);
        Member memberB = new Member("AAA", 20);
        memberJpaRepository.save(memberA);
        memberJpaRepository.save(memberB);

        List<Member> result = memberJpaRepository.findByUserName("AAA");

        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(memberA);
    }

    @Test
    public void paging(){
        //given
        memberJpaRepository.save(new Member("member1", 10));
        memberJpaRepository.save(new Member("member2", 10));
        memberJpaRepository.save(new Member("member3", 10));
        memberJpaRepository.save(new Member("member4", 10));
        memberJpaRepository.save(new Member("member5", 10));

        int age = 10;
        int offset = 0;
        int limit = 3;

        List<Member> members = memberJpaRepository.findbyPage(age, offset, limit);
        long totalCount = memberJpaRepository.totalCount(age);

        assertThat(members.size()).isEqualTo(3);
        assertThat(totalCount).isEqualTo(5);

    }

    @Test
    public void bulyUpdate(){
        //given
        memberJpaRepository.save(new Member("member1", 10));
        memberJpaRepository.save(new Member("member2", 19));
        memberJpaRepository.save(new Member("member3", 20));
        memberJpaRepository.save(new Member("member4", 21));
        memberJpaRepository.save(new Member("member5", 40));

        int result = memberJpaRepository.bulkagePlue(20);

        assertThat(result).isEqualTo(3);
    }
}