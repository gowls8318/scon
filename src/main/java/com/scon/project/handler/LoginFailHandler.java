package com.scon.project.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		/* 로그인 실패 시 수행할 코드 작성 */
		
		String errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요";
		
		if(exception instanceof InternalAuthenticationServiceException) {
			errorMessage = "아이디가 일치하지 않습니다.";
		}else if(exception instanceof BadCredentialsException) {
			errorMessage = "아이디와 비밀번호가 일치하지 않습니다.";
		}
		
		request.getRequestDispatcher("/member/login?errorMessage=" + errorMessage).forward(request, response);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
