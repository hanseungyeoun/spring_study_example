package com.study.datajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MemberDto {
    Long id;
    String usename;
    String teamName;
}
