package signup.app.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import signup.app.model.Session;
import signup.app.repository.SessionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private SessionRepository sessionRepository;
    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        
        if ( request.getRequestURI().startsWith("/css/") || request.getRequestURI().startsWith("/js/") || request.getRequestURI().startsWith("/auth/")  || request.getRequestURI().equals("/") || request.getRequestURI().equals("/index.html")) {
            filterChain.doFilter(request, response);
            return;
        }



        boolean isAuthenticated = checkIfUserIsAuthenticated(request);
        //UNAUTHENTICATED USERS ARE SENT TO THE LOGIN PAGE
        if (!isAuthenticated) {            
            response.sendRedirect("/");
            return;
        }

        //LOGGED USERS CAN PROCEED MAKING THEIR REQUEST
        filterChain.doFilter(request, response);
    }

    //THIS FUNCTION READS THE COOKIES WITH THE SESSION TOKEN AND EMAIL AND CHECKS IF THEY ARE VALID
    private boolean checkIfUserIsAuthenticated(HttpServletRequest request) {
        String sessiontoken="";
        String email="";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessiontoken".equals(cookie.getName())) sessiontoken=cookie.getValue();
                if ("user".equals(cookie.getName())) email=cookie.getValue();                
            }
        }        
        Optional<Session> opt_session=sessionRepository.findByUserAndToken(email,sessiontoken);
        if(!opt_session.isPresent()) return false;
        Session session= opt_session.get();
        if(session.getExpiration().isBefore(LocalDateTime.now())) {
            sessionRepository.delete(session);
            return false;
        }
        return true;
    }
}
