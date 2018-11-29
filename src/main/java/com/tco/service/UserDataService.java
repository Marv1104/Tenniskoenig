package com.tco.service;

import org.springframework.stereotype.Service;

import com.tco.model.InputData;

@Service
public class UserDataService {

	public InputData findData(String name) {
		InputData inputData = new InputData(name);
		switch (name) {
		case "mary":
			inputData.setMoreData("Zheng");
			break;
		case "tom":
			inputData.setMoreData("Johnson");
			break;
		default:
			inputData.setMoreData("Cool dude!");
		}

		return inputData;

	}

}
