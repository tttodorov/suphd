package com;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.user.AuthBean;

@WebFilter(filterName ="CharacterEncodingFilter", urlPatterns = {"/*"}) 
public class CharacterEncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
    	try {
            request.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);    		
    	} catch (Exception e) {
			System.out.println("CharacterEncodingFilter: " + e.getMessage());
			AuthBean authBean = new AuthBean();
			authBean.logout();
		}
    }

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}