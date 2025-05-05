package com.capgemini.theaterservice.dto.request;

import com.capgemini.theaterservice.dto.ScreenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterRequestDto {
    private String name;
    private String location;
    private List<ScreenDto> screens;
}
