package com.blog.apis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.apis.entities.Category;
import com.blog.apis.entities.Post;
import com.blog.apis.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
	
}
