package org.example.projectsystem.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Data
    public class Tracker {
    @NotNull
    @Length(min = 3,message = "the id should be more than tow digits")
        private String id;
    @NotNull
    @Length(min = 9, message = "the title should be more than 8 char")
        private String title;
    @NotNull
    @Length(min = 16 ,message = "the description should be more than 15 char")
        private String description;
        @NotNull
        @Pattern (regexp = "^(not started|in progress|completed)$", message = "Status should be one of them ( not started OR in progress OR completed)")
        private String status;
        @NotNull
        @Length(min = 7,message = "the company name should be more than 6 letters ")
        private String companyName;
    }

