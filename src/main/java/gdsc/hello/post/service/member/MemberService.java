package gdsc.hello.post.service.member;

import gdsc.hello.post.domain.member.Member;
import gdsc.hello.post.domain.member.MemberRepository;
import gdsc.hello.post.web.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;
    //회원가입
    @Transactional
    public Long signUp(MemberDto memberDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        
        //password를 암호화 한 뒤 dp에 저장

        return memberRepository.save(memberDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        //로그인을 하기 위해 가입된 user 정보를 조회하는 메서드
        Optional<Member> memberWrapper = memberRepository.findByuserName(username);
        Member member = memberWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        
        //여기서는 간단하게 username이 admin이면 admin 권한 부여
        if("admin".equals(username)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        
        //아이디, 비밀번호, 권한 리스트 매개변수로 User를 만들어 반환
        return new User(member.getUserName(), member.getPassword(), authorities);
    }
}
