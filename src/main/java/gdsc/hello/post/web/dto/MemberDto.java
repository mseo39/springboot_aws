package gdsc.hello.post.web.dto;

import gdsc.hello.post.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String userName;
    private String originName;
    private String password;
    private Integer age;
    private String gender;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .userName(userName)
                .originName(originName)
                .password(password)
                .age(age)
                .gender(gender)
                .build();
    }

    @Builder
    public MemberDto(Long id, String userName, String originName,Integer age, String gender, String password){
        this.id=id;
        this.userName=userName;
        this.password=password;
        this.originName=originName;
        this.age=age;
        this.gender=gender;
    }
}