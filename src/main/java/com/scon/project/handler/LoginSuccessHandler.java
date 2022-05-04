package com.scon.project.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// Authentication 객체를 이용해서 사용자가 가진 모든 권한을 체크
			
				List<String> roleNames = new ArrayList<>();
				authentication.getAuthorities().forEach(authority->{
					roleNames.add(authority.getAuthority());
				});
				
				if(roleNames.contains("ROLE_ADMIN")) {
					response.sendRedirect("/admin");
					return;
				}
				if(roleNames.contains("ROLE_STUDENT")) {
					response.sendRedirect("/student");
					return;
				}
				response.sendRedirect("/");
				
		
	}

}
