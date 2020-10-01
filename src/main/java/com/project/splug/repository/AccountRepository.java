package com.project.splug.repository;

import com.project.splug.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByOrderByIdxDesc();

    Account findFirstByOrderByIdxDesc();
}
