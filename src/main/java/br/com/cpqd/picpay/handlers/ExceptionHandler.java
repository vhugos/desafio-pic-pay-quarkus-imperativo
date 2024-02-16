package br.com.cpqd.picpay.handlers;

import br.com.cpqd.picpay.dto.response.ResponseErrorDTO;
import br.com.cpqd.picpay.exception.ServerException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.apache.http.HttpStatus;

import java.time.Instant;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(Exception e) {
        ResponseErrorDTO error = new ResponseErrorDTO();
        error.setMessage(e.getMessage());
        error.setTimestamp(Instant.now());
        error.setPath(uriInfo.getPath());
        if (e instanceof ServerException) {
            error.setError(((ServerException) e).getCode());
            error.setStatus(((ServerException) e).getStatus());
        } else if (e instanceof ConstraintViolationException) {
            error.setStatus(HttpStatus.SC_BAD_REQUEST);
        } else {
            error.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        return Response.status(error.getStatus()).entity(error).build();
    }
}
