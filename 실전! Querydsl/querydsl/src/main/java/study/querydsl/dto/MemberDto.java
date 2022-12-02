package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class MemberDto {
    private String username;
    private int age;

    @QueryProjection
    public MemberDto(String username, int aga) {
        this.username = username;
        this.age = aga;
    }
}
