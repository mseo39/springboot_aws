package gdsc.hello.post.web;
import gdsc.hello.post.service.member.MemberService;
import gdsc.hello.post.service.posts.PostsService;
import gdsc.hello.post.web.dto.MemberDto;
import gdsc.hello.post.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    @GetMapping("/posts/list") //url 매핑
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }
    /*
    * 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 자정됩니다
    * 앞의 경로는 .src/main/resources/templates
    * 여기선 index을 반환하는 src/main/resources/templates/index.mustache로 전환되어 view Resolver가 처리하게 됩니다.
    */

    @GetMapping("/posts/save")
    public String postSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    private MemberService memberService;

    @GetMapping("/")
    public String indexMember(){
        return "home/index_member";
    }

    @GetMapping("/member/signup")
    public String signupForm(Model model){
        model.addAttribute("member", new MemberDto());

        return "member/signupForm";
    }

    @PostMapping("/member/signup")
    public String signup(MemberDto memberDto){
        memberService.signUp(memberDto);

        return "redirect:/";
    }

    @GetMapping("/member/login")
    public String login(){
        return "member/loginForm";
    }

}