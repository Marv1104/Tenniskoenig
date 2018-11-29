package com.tco.ui.vaadin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tco.model.InputData;
import com.tco.service.UserDataService;

@Component
public class InputController {

	@Autowired
	private UserDataService userService ;

	public InputData setUserInput(String value) {
		return userService.findData(value);
	}

}
