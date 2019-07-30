package com.server.doit.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class GoalDto {
    private Long mid;
    private String name;
    private String category;
    private Long startDate;
    private Long endDate;
    private Integer penalty;
    private Integer progressType;
    private Integer progressCount;
    private Boolean timerCheck;
    @Builder.Default
    private Integer memberCount=0;
    private String color;

    public GoalDto(String name) {
        this.name = name;
    }

    @JsonIgnore
    public boolean isValid() {
        return name != null && category != null && startDate != null && endDate != null && penalty != null
                && progressType != null && progressCount != null && timerCheck != null && memberCount != null && color != null;
    }
}
