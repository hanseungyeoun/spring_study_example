package com.study.datajpa.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public class JpaBaseEntity
{
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();

        createDate = now;
        updateDate = now;
    }

    @PreUpdate
    public void preUpdate(){
        LocalDateTime now = LocalDateTime.now();
        updateDate = now;
    }
}
