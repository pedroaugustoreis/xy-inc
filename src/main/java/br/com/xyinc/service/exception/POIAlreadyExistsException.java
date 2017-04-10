package br.com.xyinc.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 *
 * @author pedro
 */
@ResponseStatus( value = HttpStatus.CONFLICT )
public class POIAlreadyExistsException extends RuntimeException {

    public POIAlreadyExistsException( String message ) {
        super( message );
    }

}
