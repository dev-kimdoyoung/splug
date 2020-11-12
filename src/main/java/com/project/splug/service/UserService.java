package com.project.splug.service;

import com.project.splug.domain.Comment;
import com.project.splug.domain.RegistWaitingUser;
import com.project.splug.domain.User;
import com.project.splug.domain.UserAttributes;
import com.project.splug.domain.dto.UserRegistDTO;
import com.project.splug.domain.enums.RoleType;
import com.project.splug.repository.CommentRepository;
import com.project.splug.repository.PostRepository;
import com.project.splug.repository.RegistWaitingUserRepository;
import com.project.splug.repository.UserRepository;
import com.project.splug.security.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RegistWaitingUserRepository registWaitingUserRepository;
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

    @Transactional
    public Long requestRegist(UserRegistDTO userRegistDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userRegistDTO.setPassword(encoder.encode(userRegistDTO.getPassword()));

        return registWaitingUserRepository.save(RegistWaitingUser.builder()
                .id(userRegistDTO.getId())
                .password(userRegistDTO.getPassword())
                .name(userRegistDTO.getName())
                .studentId(userRegistDTO.getStudentId())
                .dateOfBirth(userRegistDTO.getDateOfBirth())
                .department(userRegistDTO.getDepartment())
                .phoneNumber(userRegistDTO.getPhoneNumber())
                .build()).getIdx();
    }

    @Transactional
    public Page<RegistWaitingUser> getRegistWaitingUserList(Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by("idx").descending());

        return registWaitingUserRepository.findAll(pageable);
    }

    @Transactional
    public Long authorizeUser(Long idx){
        RegistWaitingUser guest = registWaitingUserRepository.findById(idx).orElseThrow();
        registWaitingUserRepository.deleteById(idx);

        return userRepository.save(User.builder()
                .id(guest.getId())
                .studentId(guest.getStudentId())
                .department(guest.getDepartment())
                .dateOfBirth(guest.getDateOfBirth())
                .password(guest.getPassword())
                .name(guest.getName())
                .phoneNumber(guest.getPhoneNumber())
                .roleType(RoleType.GUEST)
                .build()
        ).getIdx();
    }

    @Transactional
    public void unauthorizeUser(Long idx){
        registWaitingUserRepository.deleteById(idx);
    }

    @Transactional
    public Page<User> getUserList(Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by("idx").descending());

        return userRepository.findAll(pageable);
    }

    @Transactional
    public Long roleChange(Long idx, RoleType roleType){
        User user = userRepository.findById(idx).orElseThrow();
        user.updateRole(roleType);
        return idx;
    }

    @Transactional
    public Long signout(Long idx){
        User user = userRepository.findById(idx).orElseThrow();
        commentRepository.deleteAllByUser(user);
        userRepository.deleteById(idx);
        return idx;
    }
}
