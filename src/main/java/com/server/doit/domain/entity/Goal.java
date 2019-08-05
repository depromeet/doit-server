package com.server.doit.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

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
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Data
@Builder
@AllArgsConstructor
@ToString
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;
    private String goalName;
    private String category;
    @JsonIgnore
    private LocalDateTime startDate;
    @JsonIgnore
    private LocalDateTime endDate;
    private String themeColor;
    private Integer penalty;
    private Integer progressCheckCount;
    private Boolean timerCheck;
    private Integer progressRate;
    @Builder.Default
    private Integer memberCount=0;

    @OneToOne
    @JoinColumn(name = "pctId")
    private ProgressCheckType progressCheckType;

    public Goal() {}

    @JsonProperty
    private long epochStartDate() {
        return startDate.toEpochSecond(ZoneOffset.UTC);
    }

    @JsonProperty
    private long epochEndDate() {
        return endDate.toEpochSecond(ZoneOffset.UTC);
    }
}
