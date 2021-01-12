package ir.pb.online_examination_system.config;

import ir.pb.online_examination_system.domains.User;
import ir.pb.online_examination_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
// MyLoginSuccessHandler is a customised LoginSuccessHandler. it's duty is determine the role of the user and
// guide the user to related page.
@Configuration
public class MyLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    // UserService field is autowired to get the user from it.
    @Autowired
    private UserService service;
    // handle method is overridden to define redirections and related stuff.
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);
        if(response.isCommitted()){
            return;
        }
        RedirectStrategy strategy = new DefaultRedirectStrategy();
        strategy.sendRedirect(request, response, targetUrl);
    }
    // determineTargetUrl method send each user to the related page upon his or her role if the user registration
    // process was committed by the admin user.
    protected String determineTargetUrl(Authentication authentication) {
        String url = "/login?error=ture";
        User user = service.findByUserName(authentication.getName());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority: authorities){
            roles.add(authority.getAuthority());
        }
        if(roles.contains("ROLE_ADMIN")){
            url = "/admin";
        }else if (roles.contains("ROLE_MASTER") & user.isActive()){
            url = "/master";
        }else if (roles.contains("ROLE_STUDENT") & user.isActive()){
            url = "/student";
        }else {
            url = "/user/not-registered";
        }
        return url;
    }
}
