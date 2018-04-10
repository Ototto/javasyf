package pl.mateuszmacholl.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.mateuszmacholl.DTO.User.User.UserDto;
import pl.mateuszmacholl.Models.User.User;
import pl.mateuszmacholl.Services.User.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity get(){
		List<UserDto> usersDto = userService.findAll().stream()
				.map(userService::convertToUserDto)
				.collect(Collectors.toList());

		return new ResponseEntity<>(usersDto, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity get(@PathVariable("id") int id){
		Optional<User> user = userService.findById(id);
		if(user.isPresent()){
			UserDto userDto = userService.convertToUserDto(user.get());
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("not found user with id = " + id, HttpStatus.OK);
		}
	}
}
