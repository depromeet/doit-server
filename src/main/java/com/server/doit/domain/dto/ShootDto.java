package com.server.doit.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShootDto {
    private Long gid;
    private Long time;
    private String text;
}