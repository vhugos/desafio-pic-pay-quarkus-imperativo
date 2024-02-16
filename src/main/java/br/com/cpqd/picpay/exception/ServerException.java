package br.com.cpqd.picpay.exception;

import jakarta.validation.ConstraintViolation;

import java.util.Locale;
import java.util.Set;

public class ServerException extends GenericException {

	private static final long serialVersionUID = 1L;

	public ServerException(String code) {
		super(code);
	}
	public ServerException(String code, int status) {
		super(code, status);
	}

	public ServerException(Set<? extends ConstraintViolation<?>>violations){
		super(violations);
	}

	public ServerException(String code, Object[] messageParameters, int status) {
		super(code, messageParameters, status);
	}

	@Override
	public String getBundleName() {
		return "exceptions_pt_BR";
	}

	@Override
	public Locale getLocale() {
		return Locale.getDefault();
	}


}
