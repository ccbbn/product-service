package hello.jpa_tutorial;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String name;

//    @Column(name = "TEAM_ID")
//   private Long teamId;

    @ManyToOne  // 멤버기준 다대일이라서
    @JoinColumn(name = "TEAM_ID")  //외래키
    private Team team;


}

/* 팀(Team) 엔티티 */
