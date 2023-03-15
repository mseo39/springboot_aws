package gdsc.hello.post.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
//해당 클래스의 빌더 패턴 클래스를 생성
//생성자 상단에 선언 시 생성자에 포합된 필드만 빌더에 포함
// !! setter메소드를 만들지 않음_ 클래스의 인스턴스 값들이 언제 어디서 변하는지 코드상으로 명확하게 구분할 수 없어 차후 기능 변경 시 정말 복잡해집니다
//대신 해당 필드의 닶 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야 합니다
// !! 그러면 어떻게 삽입?
//생성자를 통해 최종값을 채운 후 db에 삽입 or 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경
@NoArgsConstructor
//기본 생성자 자동 추가
//public Posts() {}와 같은 효과
@Entity
//테이블과 링크될 클래스임을 나타냅니다
//기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 이름을 매칭합니다
//ex) SalesManager.java -> sales_managertable
public class Posts {
    @Id //해당테이블의 pk필드를 나타냅니다
    //pk의 생성규칙을 나타냅니다
    //스프링부트 2.0에서는 generationType.IDENTITY옵션을 추가해야만 auto_increment가 됩니다
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long id;

    //테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드를 모두 칼럼이 됩니다
    //사용하지 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용합니다
    //문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나, 타입을 TEXT로 변경하고 싶거나 등의 경우에 사용됩니다
    @Column(length = 10, nullable = false)
    private String title;

    @Column(length =100, columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //생성자는 어떤 필드에 값을 채우는지 알 수 없음 하지만 빌더는 어느 필드에 어떤 값을 채워야 할지 명확하게 알 수 있음
    public Posts(String title, String content, String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }
}