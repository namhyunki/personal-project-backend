package com.example.demo.domain.account.repository;

import com.example.demo.domain.account.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    Optional<Account> findByNickname(String nickname);

    @Modifying
    @Transactional
    void deleteByEmail(String email);

    @Query("SELECT DISTINCT a FROM Account a LEFT JOIN FETCH a.playlist WHERE a.email = :email")
    Account findWithPlaylistByEmail(String email);
}
