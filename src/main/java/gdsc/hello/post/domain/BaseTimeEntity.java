package gdsc.hello.post.domain;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //mappedSuperclass
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity클래스에 Auditing 기능을 포함시킵니다
public class BaseTimeEntity {
    @CreatedDate //Entity가 생성되어 저장할 때 시간이 자동 저장됩니다
    private LocalDateTime createDate;

    @LastModifiedDate //조회한 Entity의 값을 변경할 때 시간이 자동 저장됩니다
    private LocalDateTime modifiedDate;
}