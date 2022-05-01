package com.blog.apis.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.apis.entities.Category;
import com.blog.apis.entities.Post;
import com.blog.apis.entities.User;
import com.blog.apis.exceptions.ResourceNotFoundException;
import com.blog.apis.payloads.PostDto;
import com.blog.apis.payloads.PostResponse;
import com.blog.apis.repositories.CategoryRepo;
import com.blog.apis.repositories.PostRepo;
import com.blog.apis.repositories.UserRepo;
import com.blog.apis.services.PostService;
@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postrepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public PostDto createPost(PostDto postdto,Integer userId, Integer categoryId ) {
		// TODO Auto-generated method stub
		User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId",userId));

		Category category= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId",categoryId));
		
		Post post=this.modelMapper.map(postdto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(null);
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postrepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postdto, Integer postId) {
		// TODO Auto-generated method stub
		 Post post= this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
post.setTitle(postdto.getTitle());
post.setContent(postdto.getContent());
post.setImageName(postdto.getImageName());
Post updatedPost=this.postrepo.save(post);
return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
	 Post post= this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
		this.postrepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(int pageNumber,int pageSize, String sortBy) {
		// TODO Auto-generated method stub
		
	Pageable p=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).ascending());
		
	    Page<Post>pagePosts=this.postrepo.findAll(p);
	    List<Post>posts=pagePosts.getContent();
		List<PostDto>postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
		
		return this.modelMapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getPostsBycategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));
		List<Post>posts=this.postrepo.findByCategory(cat);
		List<PostDto> postDto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		User users=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User ID",userId));
		List<Post>posts=this.postrepo.findByUser(users);
		List<PostDto>postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		List<Post>posts=this.postrepo.findByTitleContaining(keyword);
		List<PostDto>postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

}
