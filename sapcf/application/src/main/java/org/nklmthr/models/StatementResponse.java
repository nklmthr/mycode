package org.nklmthr.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class StatementResponse {
    @JsonProperty
    private String institution;

    @JsonProperty
    private Instant startDate;

    @JsonProperty
    private Instant endDate;

    @JsonProperty
    private Double amount;

    public StatementResponse(String institution, Instant startDate, Instant endDate, Double amount) {
        this.institution = institution;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }
}
