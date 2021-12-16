package com.rakulack.videomanagement.service.impl;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rakulack.videomanagement.entity.User;
import com.rakulack.videomanagement.repository.UserRepository;
import com.rakulack.videomanagement.service.AccountService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void register(String email, String password, String[] roles) {
      if (userRepository.findByEmail(email).isPresent()) {
        throw new RuntimeException("invalid name or email");
      }
      String encodedPassword = passwordEncode(password);
      String joinedRoles = joinRoles(roles);
      User user = new User(null, email, encodedPassword, joinedRoles, Boolean.TRUE);
      userRepository.save(user);
    }
  
    /**
     *
     * @param rawPassword 平文のパスワード
     * @return 暗号化されたパスワード
     */
    private String passwordEncode(String rawPassword) {
      return passwordEncoder.encode(rawPassword);
    }
  
    /**
     *
     * @param roles ロール文字列の配列
     * @return カンマ区切りのロール文字列
     */
    private String joinRoles(String[] roles) {
      if (roles == null || roles.length == 0) {
        return "";
      }
      return Stream.of(roles)
          .map(String::trim)
          .map(String::toUpperCase)
          .collect(Collectors.joining(","));
    }    
}
