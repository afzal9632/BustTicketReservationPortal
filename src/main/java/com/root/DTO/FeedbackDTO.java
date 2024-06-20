package com.root.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {

    private Integer driverRating;


    private Integer serviceRating;

    @Min(value=1, message="Rating must be in range of 1-5")
    @Max(value=5, message="Rating must be in range of 1-5")
    private Integer overallRating;

    private String comments;


}
