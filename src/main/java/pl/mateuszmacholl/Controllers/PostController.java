package pl.mateuszmacholl.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.mateuszmacholl.DTO.Post.PostDto;
import pl.mateuszmacholl.DTO.Post.PostListDto;
import pl.mateuszmacholl.Models.Post.Post;
import pl.mateuszmacholl.Services.Post.PostService;
import pl.mateuszmacholl.Services.User.UserService;
import pl.mateuszmacholl.Services.Validation.ValidationErrorBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by ggere on 01.04.2018.
 */
@RestController
@RequestMapping(value = "/post")
public class PostController {
	private final PostService postService;
	private final UserService userService;

	public PostController(PostService postService, UserService userService) {
		this.postService = postService;
		this.userService = userService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity get(){
		List<PostDto> postsDto = postService.findAll().stream()
				.map(postService::convertToDto)
				.collect(Collectors.toList());

		return new ResponseEntity<>(postsDto, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity get(@PathVariable("id") int id){
		Optional<Post> post = postService.findById(id);
		if(post.isPresent()){
			PostDto postDto = postService.convertToDto(post.get());
			return new ResponseEntity<>(postDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("not found user with id = " + id, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity add(@RequestBody @Valid PostDto postDto, Errors errors){
		if (errors.hasErrors()) {
			return new ResponseEntity<>(ValidationErrorBuilder.fromBindingErrors(errors), HttpStatus.BAD_REQUEST);
		} else if(userService.findByUsername(postDto.getUsername()) == null) {
			return new ResponseEntity<>("There is no user with username: " + postDto.getUsername(), HttpStatus.BAD_REQUEST);
		}
		else {
			Post newPost = postService.add(postService.convertToEntity(postDto));
			PostDto newPostDto = postService.convertToDto(newPost);
			return new ResponseEntity<>(newPostDto,HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/list-view", method = RequestMethod.GET)
	public ResponseEntity listView(){
		List<PostListDto> postsListDto = postService.findAll().stream()
				.map(postService::convertToListDto)
				.collect(Collectors.toList());

		return new ResponseEntity<>(postsListDto, HttpStatus.OK);
	}
}
