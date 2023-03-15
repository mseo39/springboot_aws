package gdsc.hello.post.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// jpaRepository<Entity, PK타입>을 상속하면 기본적인 crud 메소드가 자동으로 생성됨
// !!주의) 엔티티 클래스와 기본 엔티티 레파지토리 는 같은 위치에 있어야 함
public interface PostsRepository extends JpaRepository<Posts,Long> {

}