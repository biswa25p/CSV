package com.csv.biswa;

public enum ErrorCode {

	OK(0), CONNECT_SERVER_DOWN(250), BAD_REQUEST(400), UNAUTHORIZED(401), NO_RECORD_FOUND(404), INVALID_PARAMS(422),
	INTERNAL_ERROR(500);

	private final int code;

	private ErrorCode(int code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

}
