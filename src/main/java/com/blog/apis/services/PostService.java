package com.blog.apis.services;

import java.util.List;

import com.blog.apis.entities.Post;
import com.blog.apis.payloads.PostDto;
import com.blog.apis.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postdto ,Integer userId,Integer categoryId);
	
	//update post
	PostDto updatePost(PostDto postdto,Integer postId);
	//delete
	void deletePost(Integer postId);
	//get All posts
	PostResponse getAllPost(int pageNumber,int pageSize,String sortBy);
	
	//get post by Id
	PostDto getPostById(Integer postId);
	
	//get Post by category
	List<PostDto> getPostsBycategory(Integer categoryId);
	List<PostDto> getPostsByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);
	
	
}
