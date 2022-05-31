package com.nikolin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

//Costum exception
public class ResourceNotFoundException extends RuntimeException {

}
