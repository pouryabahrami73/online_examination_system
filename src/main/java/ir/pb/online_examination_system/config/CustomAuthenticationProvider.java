package ir.pb.online_examination_system.config;

import ir.pb.online_examination_system.services.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// Costume AuthenticationProvider Class.
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    // userDetailService field which is a been.
    @Autowired
    private MyUserDetailsService userDetailsService;
    // passwordEncoder field which is a been.
    @Autowired
    private PasswordEncoder passwordEncoder;
    // authenticate method overridden to customize BadCredentialException's massage and authenticate user.
    // if the user is authenticated his or her authorities and username and password to the costume LoginSuccessHandler
    // class.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    authentication.getPrincipal(),
                    password,
                    userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("رمز عبور اشتباه است!");
        }
    }
    // supports method overridden to represent whether the class can be authenticated or not.
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
