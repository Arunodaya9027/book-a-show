package com.capgemini.theaterservice.dto.response;

import java.util.List;

import com.capgemini.theaterservice.dto.TheaterDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTheaterListDto {
	private String message;
	private int status;
	private List<TheaterDetailsDto> theaterDetailsDto;
}
