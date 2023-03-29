package gdsc.hello.post.web.dto;

import gdsc.hello.post.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private Integer age;
    private String gender;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, Integer age, String gender) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.age=age;
        this.gender=gender;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .age(age)
                .gender(gender)
                .build();
    }
}
