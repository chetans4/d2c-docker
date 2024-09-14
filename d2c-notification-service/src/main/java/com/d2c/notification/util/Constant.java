package com.d2c.notification.util;

public enum Constant {

	SUCCESS("Success"),
	REQ_SUCCESS("Request processed successfully."),
	REQ_SUCCESS_CODE(200);

	private final Object desc;

	private Constant(Object desc) {
		this.desc = desc;
	}

	public Object getDesc() {
		return desc;
	}

}
