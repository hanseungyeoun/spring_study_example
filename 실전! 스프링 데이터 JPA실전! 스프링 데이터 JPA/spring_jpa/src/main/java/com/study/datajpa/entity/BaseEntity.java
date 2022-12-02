package com.study.datajpa.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.sound.sampled.spi.AudioFileReader;
import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;
    @LastModifiedBy
    private LocalDateTime modifiedDate;
}
