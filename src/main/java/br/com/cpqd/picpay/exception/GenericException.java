package br.com.cpqd.picpay.exception;

import jakarta.validation.ConstraintViolation;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code = null;

	private String message = null;

	private int status ;

	public GenericException(String code) {
		super();
		this.code = code;
		this.initialize(null);
	}

	public GenericException(String code, int status) {
		super();
		this.code = code;
		this.status= status;
		this.initialize(null);
	}
	public GenericException(String code, Throwable cause) {
		super(cause);
		this.code = code;
		this.initialize(null);
	}
	public GenericException(String code,int status, Throwable cause) {
		super(cause);
		this.code = code;
		this.status = status;
		this.initialize(null);
	}


	public GenericException(String code, Object[] messageParameters) {
		this(code);
		this.initialize(messageParameters.clone());
	}
	public GenericException(String code, Object[] messageParameters, int status) {
		this(code);
		this.status = status;
		this.initialize(messageParameters.clone());
	}
	public GenericException(String code, Throwable cause, Object[] messageParameters) {
		this(code, cause);
		this.initialize(messageParameters.clone());
	}

	public GenericException(Set<? extends ConstraintViolation<?>> violations){
		this.status = 400;
		this.message = violations.stream()
				.map(cv -> getMessageFromBundle(cv.getMessage()))
				.collect(Collectors.joining(" "));
	}
	public GenericException(){
		super();
	}

	private void initialize(Object[] messageParameters) {
		String messageFromBundle = getMessageFromBundle();
		String messageWithParams = formatMessageWithParams(messageFromBundle, messageParameters);
		this.message = prependCodeToMessage(messageWithParams);

	}

	private String formatMessageWithParams(String message, Object[] messageParameters) {
		String formatedMessage = message;
		if (messageParameters != null) {
			formatedMessage = MessageFormat.format(message, messageParameters);
		}
		return formatedMessage;
	}

	private String getMessageFromBundle() {
		ResourceBundle bundle = ResourceBundle.getBundle(getBundleName(), getCustomLocale());
		return bundle.getString(this.getCode());
	}

	private String getMessageFromBundle(String code) {
		ResourceBundle bundle = ResourceBundle.getBundle(getBundleName(), getCustomLocale());
		return bundle.getString(code);
	}


	private String prependCodeToMessage(String message) {
		return message.toString();
	}

	public final String getCode() {
		return code;
	}

	public final String getMessage() {
		return message;
	}
	public final int getStatus(){ return status;}

	public abstract String getBundleName();

	private Locale getCustomLocale() {
		Locale locale = getLocale();
		if (locale == null) {
			locale = Locale.getDefault();
		}
		return locale;
	}

	public abstract Locale getLocale();

}
