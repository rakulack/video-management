package com.rakulack.videomanagement.service;

public interface AccountService {
    void register(String email, String password, String[] roles);
}
