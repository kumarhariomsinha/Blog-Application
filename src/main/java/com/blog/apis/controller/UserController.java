package com.blog.apis.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.apis.payloads.ApiResponse;
import com.blog.apis.payloads.UserDto;
import com.blog.apis.services.UserService;

@RestController

@RequestMapping("/api/users")
public class UserController {
@Autowired
private UserService userService;

@PostMapping("/")
public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto){
	UserDto createdUserDto =this.userService.createUser(userDto);
	return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
}
//Update the userData
@PostMapping("/{userId}")
public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
	UserDto updatedUser=this.userService.updateUser(userDto, uid);
	return ResponseEntity.ok(updatedUser);
}
//Delete-delete user
@DeleteMapping("/{userId}")
public ResponseEntity<ApiResponse>deleteUser(@PathVariable("userId") Integer uid){
this.userService.deleteUser(uid);
return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Succesfully",true),HttpStatus.OK);
}
//Get- user get
@GetMapping("/{userId}")
public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
	return ResponseEntity.ok(this.userService.getUserById(userId));
	
}
@GetMapping("/")
public ResponseEntity<List<UserDto>> getUsers(){
	return ResponseEntity.ok(this.userService.getAllUsers());
	
}
} 