package com.example.views;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by justin on 21.05.15.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    final static Logger LOGGER = LoggerFactory.getLogger(RuntimeExceptionMapper.class);

    ErrorView errorView = null;
    @Override
    public Response toResponse(RuntimeException exception) {

        errorView = new ErrorView(exception, "errors/500.ftl");
        Response response500 = Response
                .serverError()
                .entity(errorView)
                .build();

        // Assuming that any WebApplicationException thrown in application are deliberate and already logged
        if (exception instanceof WebApplicationException) {
            return handleWebApplicationException(exception, response500);
        }

        LOGGER.error("Uncaught exception in application", exception);

        return response500;
    }

    private Response handleWebApplicationException(RuntimeException exception, Response response500) {

        WebApplicationException webAppException = (WebApplicationException) exception;
        int statusCode = webAppException.getResponse().getStatus();

        Response response;
        switch(statusCode) {
            case 400: errorView = new ErrorView(exception, "errors/400.ftl");
                response = Response.status(statusCode).entity(errorView).build();
                return response;
            case 401: errorView = new ErrorView(exception, "errors/401.ftl");
                response = Response.status(statusCode).entity(errorView).build();
                return response;
            case 403: errorView = new ErrorView(exception, "errors/403.ftl");
                response = Response.status(statusCode).entity(errorView).build();
                return response;
            case 404: errorView = new ErrorView(exception, "errors/404.ftl");
                response = Response.status(statusCode).entity(errorView).build();
                return response;
            case 405: errorView = new ErrorView(exception, "errors/405.ftl");
                response = Response.status(statusCode).entity(errorView).build();
                return response;
        }

        LOGGER.info("No template for WebApplicationException status " + statusCode);
        LOGGER.info("errorView  " + errorView.getTemplateName());
        return response500;
    }
}