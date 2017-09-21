package com.yiibai.primera.testng.util;

public class ResultUtil {
	public boolean excepted = true;
	public boolean actual;
	public String message;

	public boolean getExcepted() {
		return excepted;
	}
	public void setExcepted(boolean excepted) {
		this.excepted = excepted;
	}
	public boolean getActual() {
		return actual;
	}
	public void setActual(boolean actual) {
		this.actual = actual;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
