package hello.jpa_tutorial;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/jpa-basic")
@RestController
@Transactional
public class JpaBasic {

    private final EntityManager em;
    @GetMapping
    public String saveTeam() {
        //1.팀을 만들고 맴버 객체에 해당팀을 지정
        Team team = new Team();
        team.setName("KITRI");
        em.persist(team);

        Member member = new Member();
        member.setName("나");

//        member.setTeamId(team.getId());
        member.setTeam(team);
        em.persist(member);



        //2.조회, 멤바가 소속된 팀을 가져오고 싶다
        Member foundMember = em.find(Member.class, member.getId());
        Team foundTeam =  foundMember.getTeam();
//        Team foundTeam = em.find(Team.class, foundMember.getTeamId());



        return foundTeam.getName();

    }



    @GetMapping("/{teamId}/members")
    public int findMembersFromTeam(@PathVariable Long teamId) {
        Team team = em.find(Team.class, teamId);
        return team.getMembers().size();
    }
}
