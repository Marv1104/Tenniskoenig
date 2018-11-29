package com.tco.model;

public class InputData {
	public InputData() {
		super();
	}

	public InputData(String name) {
		super();
		this.name = name;
	}

	private String name;
	private String moreData;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMoreData() {
		return moreData;
	}

	public void setMoreData(String moreData) {
		this.moreData = moreData;
	}

}
