package com.project.splug.service;

import com.project.splug.domain.User;
import com.project.splug.domain.UserAttributes;
import com.project.splug.repository.UserRepository;
import com.project.splug.security.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLoginService implements UserDetailsService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(user.getRoleType().getKey()));

        UserAttributes originUser = new UserAttributes();
        originUser.setUsername(user.getName());
        originUser.setPassword(user.getPassword());
        originUser.setAuthorities(authorities);
        originUser.setEnabled(true);
        originUser.setAccountNonExpired(true);
        originUser.setAccountNonLocked(true);
        originUser.setCredentialsNonExpired(true);

        httpSession.setAttribute("user", new SessionUser(user)); // 세션 등록

        return originUser;
    }
}
