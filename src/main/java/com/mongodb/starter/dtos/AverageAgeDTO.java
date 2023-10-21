package com.mongodb.starter.dtos;

public record AverageAgeDTO(double averageAge) {

    public AverageAgeDTO(double averageAge) {
        this.averageAge = averageAge;
    }
}
