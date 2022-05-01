package com.blog.apis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.apis.payloads.ApiResponse;
import com.blog.apis.payloads.CategoryDto;
import com.blog.apis.services.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//createCategory
	@PostMapping("/")
	public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto categDto){
	CategoryDto createdCategory=this.categoryService.createCategory(categDto);
		return new ResponseEntity<CategoryDto>(createdCategory,HttpStatus.CREATED);
	}

	//updateCategory
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
	CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
	//deleteCategory
		@DeleteMapping("/{catId}")
		public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully",true),HttpStatus.OK);
		}
	

		//getCategoryBy Id
			@GetMapping("/{catId}")
			public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
			CategoryDto categoryDto=this.categoryService.getCategory(catId);
			return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
			}

			

			//getAllCategories
			@GetMapping("/")
			public List<CategoryDto> getCategories(){
		  
			return (this.categoryService.getCategories());
			}
							
}
