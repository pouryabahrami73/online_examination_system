package ir.pb.online_examination_system.config;

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

@Configuration
public class MyLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication, request);
        if(response.isCommitted()){
            return;
        }
        RedirectStrategy strategy = new DefaultRedirectStrategy();
        strategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication, HttpServletRequest request) {
        String url = "/login?error=ture";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority: authorities){
            roles.add(authority.getAuthority());
        }
        if(roles.contains("ROLE_ADMIN")){
            url = "/admin";
        }else if (roles.contains("ROLE_MASTER") /*& request.getCookies("isActive")*/){
            url = "/master";
        }else if (roles.contains("ROLE_STUDENT") /*& request.getCookies("isActive")*/){
            url = "/student";
        }
        return url;
    }
}
