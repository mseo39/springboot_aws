package gdsc.hello.post.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {
    
    //아이디, 비밀번호 , 이름, 나이, 성별 필요
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String originName;
    private String password;
    private Integer age;
    private String gender;

    @Builder
    public Member(Long id, String userName, String password, Integer age, String gender, String originName){
        this.id=id;
        this.userName=userName;
        this.password=password;
        this.age=age;
        this.gender=gender;
        this.originName=originName;
    }

}
