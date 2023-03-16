package gdsc.hello.post.service.posts;

import gdsc.hello.post.domain.posts.Posts;
import gdsc.hello.post.domain.posts.PostsRepository;
import gdsc.hello.post.web.dto.PostsListResponseDto;
import gdsc.hello.post.web.dto.PostsResponseDto;
import gdsc.hello.post.web.dto.PostsSaveRequestDto;
import gdsc.hello.post.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/*
* 흔히 하는 오해! service에서 비지니스 로직을 처리해야한다 -> x
* 서비스는 트랜잭션, 도메인 간 순서 보장의 역할만 함
*
* web layer : 뷰 템플릿 영역, 외부 요청과 응답에 대한 전반적인 영영을 이야기함
* service layer: 서비스 영역, 일반적으로 컨트롤러와 디티오의 중간 영역에서 사용됨 @transactional 이 사용되어야 하는 영역
* repository layer : 데이터베이스와 같이 데이터 저장소에 접근하는 영역
* dtos : dto는 계층 간에 데이터 교환을 위한 객체를 이야기하며 dtos는 이들의 영역을 얘기합니다
* 예를 들면 뷰 템플릿 엔진에서 사용될 객체나 레파지토리 레이어에서 결과로 넘겨준 객체 등이 이들을 이야기합니다
* domain Model: 도메인이라 불리는 개발 대상을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화시킨것을 도메인 모델이라고 합니다
* 택시 앱이라고 하면 배차, 탑승, 요금 등이 모두 도메인이 될 수 있습니다
* @entity를 사용해보신 분들은 @entity가 사용된 영역 역시 도메인 모델이라고 이해해주시면 됩니다
* 다만 무조건 데이터베이스의 테이블과 관계가 있어야만 하는 것은 아니도 VO처럼 값 객제들도 이 영역에 해당하기 때문입니다
* */
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //update 기능에서 데이터베이스에 쿼리를 날리는 부분이 없습니다.
    //JPA의 영속성 컨텍스트 때문입니다
    //영속성 컨텍스트란? 엔티티를 영구 저장하는 환경입니다.
    //일종의 논리적 개념이라고 보시면 되며 JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈립니다
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts= postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)//왼쪽 코드는 실제로 .map(posts -> new PostsListResponseDto)와 같다. 포스트레파지토리로 넘어온 포스트의 stream을 map을 통해 postsListResponseDto 변환 -> List로 반환하는 메소드
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }


}