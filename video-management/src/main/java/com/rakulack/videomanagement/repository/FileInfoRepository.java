package com.rakulack.videomanagement.repository;

import com.rakulack.videomanagement.entity.FileInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    
}
