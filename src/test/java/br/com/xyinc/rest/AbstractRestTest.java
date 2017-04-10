package br.com.xyinc.rest;


import br.com.xyinc.AbstractBaseTest;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.MediaType;


/**
 *
 * @author pedro
 */
public abstract class AbstractRestTest extends AbstractBaseTest {

    protected MediaType contentType = new MediaType( MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName( "utf8" ) );

    public static final DateTimeFormatter ISO_FIXED_FORMAT = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" ).withZone( ZoneId.of( "Z" ) );


    /**
     * Convert an object to JSON byte array.
     *
     * @param object
     *            the object to convert
     * @return the JSON byte array
     * @throws IOException
     */
    public static byte[] convertObjectToJsonBytes( Object object )
        throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable( MapperFeature.USE_ANNOTATIONS );

        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer( ZonedDateTime.class, new ZonedDateTimeSerializer( ISO_FIXED_FORMAT ) );
        mapper.registerModule( module );

        return mapper.writeValueAsBytes( object );
    }

}
