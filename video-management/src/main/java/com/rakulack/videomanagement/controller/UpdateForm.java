package com.rakulack.videomanagement.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateForm {

    @NonNull
    private String fileName;
    @NonNull
    private String memo;
}
