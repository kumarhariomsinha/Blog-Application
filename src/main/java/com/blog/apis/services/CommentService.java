package com.blog.apis.services;

import com.blog.apis.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto,int postId);

	//CommentDto getComment(int commentId);
	
	void deleteComment(Integer commentId);
}
