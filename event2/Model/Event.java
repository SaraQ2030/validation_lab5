package org.example.event2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {
    @NotEmpty
    @Length(min = 3 ,message = "the id should be more than 2 digit")
        private String id;
    @NotEmpty
    @Length(min = 15 ,message = "the description should be more than 15 char")
    private String description;
    @NotNull
    @Min(value = 26 ,message = "Capacity should be more than 25 person")
   @Pattern (regexp = "^[0-9]+$", message = "Capacity should contain only numbers")
    private  String capacity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
