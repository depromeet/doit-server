package com.server.doit.controller.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class GoalDto {
    private String name;
    private String category;
    private Long startDate;
    private Long endDate;
    private Integer penalty;
    private Integer progressType;
    private Integer progressCount;
    private Boolean timerCheck;
    private Integer memberCount;
    private String color;

    public GoalDto(String name) {
        this.name = name;
    }

    public boolean isValid() {
        return name != null && category != null && startDate != null && endDate != null && penalty != null
                && progressType != null && progressCount != null && timerCheck != null && memberCount != null && color != null;
    }
}