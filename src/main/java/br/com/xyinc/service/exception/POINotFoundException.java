package br.com.xyinc.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author pedro
 */
@ResponseStatus( value = HttpStatus.NO_CONTENT )
public class POINotFoundException extends RuntimeException {
    
    public POINotFoundException( String message ) {
        
        super( message );
    }
    
}
