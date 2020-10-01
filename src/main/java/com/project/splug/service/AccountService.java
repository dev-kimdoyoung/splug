package com.project.splug.service;

import com.project.splug.domain.Account;
import com.project.splug.domain.dto.AccountSaveRequestDTO;
import com.project.splug.repository.AccountRepository;
import com.project.splug.repository.PostRepository;
import com.project.splug.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> findAllAccount(){
        return accountRepository.findAllByOrderByIdxDesc();
    }

    // 회계 내역 생성
    @Transactional
    public Long save(AccountSaveRequestDTO accountsaveRequestDTO){
        Account account = accountRepository.findFirstByOrderByIdxDesc();
        int remainAmount = Integer.parseInt(account.getRemainAmount()) + Integer.parseInt(accountsaveRequestDTO.getUseAmount());

        account = accountsaveRequestDTO.toEntity();
        account.setRemainAmount(Integer.toString(remainAmount));

        return accountRepository.save(account).getIdx();
    }
}
