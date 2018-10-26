package com.devglan.userportal;

public class ResponseStatus {
	private int responseCode;
	private String responseMessage;

	public ResponseStatus() {
		// TODO Auto-generated constructor stub
	}

	public ResponseStatus(int responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public ResponseStatus(int responseCode) {
		super();
		this.responseCode = responseCode;
	}

	public ResponseStatus(String responseMessage) {
		super();
		this.responseMessage = responseMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
