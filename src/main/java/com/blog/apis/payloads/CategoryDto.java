package com.blog.apis.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	@NotBlank
	@Size(min=4,max=10)
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10,max=50)
	private String categoryDescription;
}
