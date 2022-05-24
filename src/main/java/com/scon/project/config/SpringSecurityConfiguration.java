
package com.scon.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.scon.project.handler.LoginFailHandler;
import com.scon.project.handler.LoginSuccessHandler;
import com.scon.project.member.model.service.MemberService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SpringSecurityConfiguration(MemberService memberService, PasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	.csrf().disable()
				.authorizeHttpRequests()
				.antMatchers("/").authenticated()
				.antMatchers("/student/**").authenticated()					
				.antMatchers("/schedule/**").authenticated()				
				.antMatchers("/admin/**").authenticated()					
				.antMatchers("/admin/**").hasAnyRole("ADMIN","DIRECTOR")	
				.and()
				/* 로그인 설정 */
				.formLogin()
				/* 로그인 페이지 설정 */
				.loginPage("/member/login")
				/* 로그인 성공 시의 핸들러 설정 */
				.successHandler(loginSuccessHandler())
				/* 로그인 실패 시의 핸들러 설정 */
				.failureHandler(loginFailHandler())
				.and()
				/* 로그아웃 설정 */
				.logout()
				/* 로그아웃 주소 */
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				/* JSESSIONID 쿠키 삭제 */
				.deleteCookies("JSESSIONID")
				/* 세션 만료 */
				.invalidateHttpSession(true)
				/* 성공 시 랜딩 페이지 */
				.logoutSuccessUrl("/")
	.and()
		/* 인증/인가 예외 처리 */
		 .exceptionHandling()
		/* 인가 되지 않았을 때 - 권한이 없을 때 이동할 페이지 */
		 .accessDeniedPage("/denied");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* 로그인, 로그아웃은 MemberController에 작성하지 않고 스프링 시큐리티 모듈을 통해 처리 */
		/* 사용자 인증을 위해서 사용할 MemberService 등록, 사용하는 비밀번호 인코딩 방식 설정 */
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder);
	}
	
	 /* 로그인 성공 핸들러 bean 등록*/
	 @Bean
	 public LoginSuccessHandler loginSuccessHandler() {
		 return new LoginSuccessHandler();
	 }

	/* 로그인 실패 핸들러 bean 등록*/
	 @Bean 
	 public LoginFailHandler loginFailHandler() { 
		 return new LoginFailHandler(); 
	 }
	
}

