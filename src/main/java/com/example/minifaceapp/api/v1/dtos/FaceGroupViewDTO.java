package com.example.minifaceapp.api.v1.dtos;

import java.util.List;

import lombok.Data;

@Data
public class FaceGroupViewDTO {
	
	private Long id;
	private String name;
	private String description;
	private FaceUserDTO owner;
	private List<FaceUserDTO> members;
	private List<FacePostDTO> posts;
}
