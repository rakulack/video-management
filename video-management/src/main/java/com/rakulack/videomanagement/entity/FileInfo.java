package com.rakulack.videomanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "header")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "id" })
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "file_name", length = 200, nullable = false)
    private String file_name;
    @Column(name = "memo", length = 200, nullable = false)
    private String memo;
    @Column(name = "prc_date")
    private Date prcDate;
}