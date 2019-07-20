package com.server.doit.domain.entity;

import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Data
@Builder
@AllArgsConstructor
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;
    private String goalName;
    private String category;
    @JsonIgnore
    private LocalDate startDate;
    @JsonIgnore
    private LocalDate endDate;
    private String themeColor;
    private Integer penalty;
    private Integer progressCheckCount;
    private Boolean timerCheck;
    private Integer progressRate;

    @OneToOne
    @JoinColumn(name = "pctId")
    private ProgressCheckType progressCheckType;

    public Goal() {}

    @JsonProperty
    private long epochStartDate() {
        return startDate.toEpochDay();
    }

    @JsonProperty
    private long epochEndDate() {
        return endDate.toEpochDay();
    }
}
