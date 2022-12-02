package com.study.datajpa.repository;

import com.study.datajpa.entity.Member;

import java.util.List;

interface MemberRepositoryCustom {
    List<Member> findMemberCustom();

}
