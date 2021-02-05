package com.biswa.csv.bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {

	// private ErrorDetails errors;
	private Object results;
	// private boolean status;

	/**
	 * @return the errors
	 */
//	public ErrorDetails getErrors() {
//		return errors;
//	}
	/**
	 * @param errors the errors to set
	 */

//	public void setErrors(ErrorDetails errors) {
//		this.errors = errors;
//	}
	/**
	 * @return the results
	 */
	public Object getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(Object results) {
		this.results = results;
	}

	/*
	 * public boolean isStatus() { return status; }
	 * 
	 * public void setStatus(boolean status) { this.status = status; }
	 */

	public static ResponseEntity<Response> buildResponse(Object results) {
		return buildResponse(results, HttpStatus.OK);
	}

	public static ResponseEntity<Response> buildResponse(Exception exception, HttpStatus status) {
		ErrorDetails details = new ErrorDetails("Bad request", ErrorCode.BAD_REQUEST.getCode());
		return buildResponse(details, status);
	}

	public static ResponseEntity<Response> buildResponse(Object results, HttpStatus status) {
		Response response = new Response();
		response.setResults(results);
		return new ResponseEntity<Response>(response, status);
	}

//	public static ResponseEntity<Response> buildResponse(ErrorDetails errorDetails, HttpStatus status) {
//		Response response = new Response();
//		response.setErrors(errorDetails);
//		return new ResponseEntity<Response>(response, status);
//	}

	/*
	 * public static ResponseEntity<Response> buildResponse(Object results, boolean
	 * status, HttpStatus statusCode) { Response response = new Response();
	 * response.setResults(results); response.setStatus(status); return new
	 * ResponseEntity<Response>(response, statusCode); }
	 */

}
