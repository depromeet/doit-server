package com.server.doit.domain.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gid;
    private String goalName;
    private String category;
    private LocalDate startDate;
    private LocalDate endDate;
    private String themeColor;
    private Integer penalty;
    private Integer progressCheckCount;
    private Boolean timerCheck;

    @OneToOne
    @JoinColumn(name = "pctId")
    private ProgressCheckType progressCheckType;

    public Goal() {}
}
