package jp.co.sss.book.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class LoginCheck extends HttpFilter{
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)throws IOException, ServletException{
        String URL = request.getRequestURI();

        //exclude common files from login check filter
        if(URL.indexOf("/html/") != -1 || 
        URL.indexOf("/css/") != -1 ||
        URL.indexOf("/images/") != -1 ||
        URL.indexOf("/js/") != -1){
            chain.doFilter(request, response);
        }

         //login url check
        if(URL.endsWith("/login")){
            chain.doFilter(request, response);
        } else {
            //get session information
            HttpSession session = request.getSession();
            Integer userId = (Integer)session.getAttribute("userId");
            System.out.println(userId);
            //if session doesnt have info redirect to the login page
            if(userId==null){
                response.sendRedirect("/book_list/login");
                return;
            } else {
                if(URL.endsWith("/")){
                    response.sendRedirect("/book_list/list");
                    return;
                }else {
                    chain.doFilter(request, response);
                }
            }
        }
    }
}
