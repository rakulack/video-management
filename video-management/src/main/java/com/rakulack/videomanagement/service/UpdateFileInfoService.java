package com.rakulack.videomanagement.service;

import com.rakulack.videomanagement.auth.SimpleLoginUser;

/**
 * 今んとこメモだけアップデートできる予定
 * 
 * @author rakulack
 */
public interface UpdateFileInfoService {
    void update(String fileName, SimpleLoginUser user, String memo);

}
