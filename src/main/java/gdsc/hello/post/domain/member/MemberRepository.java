package gdsc.hello.post.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import gdsc.hello.post.domain.member.Member;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByuserName(String username);
}