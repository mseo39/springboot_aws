package gdsc.hello.post.web.dto;

import gdsc.hello.post.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    private Integer age;
    private String gender;

    public PostsResponseDto(Posts entity){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.content=entity.getContent();
        this.author= entity.getAuthor();
        this.age=entity.getAge();
        this.gender=entity.getGender();
    }
}