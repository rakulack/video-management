package com.rakulack.videomanagement.repository;

import java.util.List;

import com.rakulack.videomanagement.entity.FileInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findbyUserIdAndFileName(Long userId, String fileName);

    List<FileInfo> findByUserIdOrderByPrcDateDesc(Long userId);
}
