package com.study.datajpa.repository;

import com.study.datajpa.dto.MemberDto;
import com.study.datajpa.entity.Member;
import com.study.datajpa.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void basicCRUD(){

        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        long count =  memberRepository.count();
        assertThat(count).isEqualTo(2);

        memberRepository.delete(findMember1);
        memberRepository.delete(findMember2);

        long deleteCount =  memberRepository.count();
        assertThat(deleteCount).isEqualTo(0);
    }

    @Test
    public void findUsernameAndAgeGreaterThen(){
        Member memberA = new Member("AAA", 10);
        Member memberB = new Member("AAA", 20);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void testNameQuery(){
        Member memberA = new Member("AAA", 10);
        Member memberB = new Member("AAA", 20);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        List<Member> result = memberRepository.findByUsername("AAA");

        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(memberA);
    }

    @Test
    public void findUsernameList(){
        Member memberA = new Member("AAA", 10);
        Member memberB = new Member("AAA", 20);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        List<Member> result = memberRepository.findByUser("AAA", 10);

        for (Member member : result) {
            System.out.println("member = " + member);
        }
    }

    @Test
    public void findMemberDto(){
        Team team1 = new Team("team1");
        Team team2 = new Team("team1");
        teamRepository.save(team1);
        teamRepository.save(team2);
        
        Member memberA = new Member("AAA", 10, team1);
        Member memberB = new Member("AAA", 20, team2);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
            
        }

    }

    @Test
    public void findReturnType(){
        Member memberA = new Member("AAA", 10);
        Member memberB = new Member("AAA", 20);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        List<Member> aaa = memberRepository.findListByUsername("AAA");
        Member aaa1 = memberRepository.findMemberByUsername("AAA");
        Optional<Member> aaa2 = memberRepository.findOptionalByUsername("AAA");
   }

    @Test
    public void paging(){
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(2, 3, Sort.by(Sort.Direction.DESC, "username"));

        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));

        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();
        for (Member member : content) {
            System.out.println("member = " + member);
        }

        System.out.println("totalElements = " + totalElements);

        assertThat(content.size()).isEqualTo(3);
        //assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);
        //assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();


       // assertThat(members.size()).isEqualTo(3);
      //  assertThat(totalCount).isEqualTo(5);

    }

    @Test
    public void bulkUpdate(){
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));

        int result = memberRepository.bulkagePlue(20);

        em.flush();
        em.clear();

        List<Member> members = memberRepository.findListByUsername("member5");
        Member member = members.get(0);
        System.out.println("member = " + member);


        assertThat(result).isEqualTo(3);
    }

    @Test
    public void findMemberLazy(){
        //given
        Team teamA = new Team("teamA");
        Team teamB = new Team("TeamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamA);

        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println("member.teamClass = " + member.getTeam().getClass());
            System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
        }
    }






}
