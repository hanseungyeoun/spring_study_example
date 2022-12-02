package com.study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "user_id", "age"})
@NamedQuery(name = "Member.findByUsername",
    query = "select m from Member m where m.username = :username"
)

@NamedEntityGraph(name = "Member.all", attributeNodes = @NamedAttributeNode("team"))

public class Member extends JpaBaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    Team team;

   public Member(String username) {
        this.username = username;
    }

    public Member(String username, int i, Team teamA) {
       this.username = username;
       this.age = age;
        if (teamA != null) {
            changeTeam(teamA);
        }
    }

    public Member(String username, int age) {
       this.username = username;
       this.age = age;
    }


    public void changeTeam(Team team){
        this.team = team;
        this.team.getMembers().add(this);
    }
}
