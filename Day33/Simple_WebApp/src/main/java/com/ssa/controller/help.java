package com.ssa.controller;

public class help {

	private final long id;
	private final String desc;
	
	public help(long id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	public long getId() {
		return id;
	}
	
	public String getDesc() {
		return desc;
	}
}
