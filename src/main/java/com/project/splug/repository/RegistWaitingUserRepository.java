package com.project.splug.repository;

import com.project.splug.domain.RegistWaitingUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistWaitingUserRepository extends JpaRepository<RegistWaitingUser, Long> {
    Page<RegistWaitingUser> findAll(Pageable pageable);
}
