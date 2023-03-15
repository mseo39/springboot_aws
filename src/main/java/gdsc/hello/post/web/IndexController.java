package gdsc.hello.post.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexController {
    @GetMapping("/") //url 매핑
    public String index(){
        return "index";
    }
    /*
    * 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 자정됩니다
    * 앞의 경로는 .src/main/resources/templates
    * 여기선 index을 반환하는 src/main/resources/templates/index.mustache로 전환되어 view Resolver가 처리하게 됩니다.
    */
}