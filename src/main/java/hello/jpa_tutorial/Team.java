package hello.jpa_tutorial;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")  //pk
    private Long id;
    private String name;


    //팀 하나에 여려 맴버 연결
    @OneToMany(mappedBy = "team") // fk가 누군지 알려줘야 함 (테이블 컬럼이 아닌 변수명 , 양뱡향 연결을 위해, 팀에서 읽을 때... 읽기 전용... 조회용
    private List<Member> members = new ArrayList<>();

}
