package com.server.doit.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {
    private long mid;
    private String imageUrl;
    private String name;
    private int progressRate;
}
