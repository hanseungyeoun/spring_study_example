package com.study.datajpa.repository;

import com.study.datajpa.dto.MemberDto;
import com.study.datajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom
{
    List<Member> findByUsernameAndAgeGreaterThan(String userMember, int age);

    List<Member> findTop3HelloBy();

    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findByUser(@Param("username") String username, @Param("age") int age);

    @Query("select new com.study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :username ")
    List<MemberDto> findByNames(@Param("username") Collection<String> names);

    List<Member> findListByUsername(String username);

    Member findMemberByUsername(String username);

    Optional<Member> findOptionalByUsername(String username);

    @Query(value = "select m from Member m join m.team t",
            countQuery = "select count(m) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying
    @Query("update Member m set m.age = m.age+1 where m.age >= :age")
    int bulkagePlue(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberByFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @Query("select m from Member m left join fetch m.team")
    @EntityGraph(attributePaths = {"team"})
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})
    List<Member> findEntityGraphByUsername(@Param("username") String username);

}
