package gdsc.hello.post.Config;

import gdsc.hello.post.service.member.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //Config Bean이라는 것을 명시해주는 annotation입니다
@EnableWebSecurity //spring security config를 할 클래스라는 것을 명시해줍니다
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //WebSecurityConfigurerAdapter를 상속받아 필요한 메서드를 구현하여 필요한 설정을 해줍니다
    private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() { //입력받은 비밀번호를 그대로 저장하는 것이 아니라 암호화하여 저장을 해줘야 한다

        return new BCryptPasswordEncoder(); //비밀번호 암호화 방법
    }

    //
    @Override
    public void configure(WebSecurity web) throws Exception{
        //인증을 무시하기 위한 설정
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    //http로 들어오는 요청에 대하여 보안을 구성할 수 있는 클래스로 로그인에 대한 설정을 한다
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()//로그린 설정
                .loginPage("/member/login") //커스텀 로그인 페이지를 사용
                .defaultSuccessUrl("/")// 로그인 성공 시 이동할 페이지
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/") //로그아웃 성공 시 이동할 페이지
                .invalidateHttpSession(true) //세션 초기화
                .and()
                .exceptionHandling();
    }
    
    //유저 정보를 memberservice에서 찾아 담아준다
    //passwordEncoder는 bean으로 등록한 passwordEncode()로 등록한다
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //로그인 처리하기 위한 AuthenticationManagerBuilder 를 설정
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

}
