package com.root.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class UserLoginDTO {
	
	@NotBlank(message = "email can't be null or blank!")
	private String email;
	
	@NotBlank(message="password can't be null or blank!")
	private String password;
}
