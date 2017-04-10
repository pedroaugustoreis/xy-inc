package br.com.xyinc.config;


import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;


/**
 *
 * @author pedro
 */
@ControllerAdvice
public class CustomControllerAdvice extends DefaultHandlerExceptionResolver {

    @ExceptionHandler( IllegalArgumentException.class )
    void handleIllegalArgumentException( HttpServletResponse response )
        throws IOException {

        response.sendError( HttpStatus.BAD_REQUEST.value() );
    }

}
