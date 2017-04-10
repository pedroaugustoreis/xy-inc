package br.com.xyinc;


import br.com.xyinc.model.POI;
import br.com.xyinc.service.POIService;
import br.com.xyinc.service.exception.POIAlreadyExistsException;
import br.com.xyinc.service.exception.POINotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith( SpringRunner.class )
@SpringBootTest
public class PoiApplicationTests {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private POIService poiService;


    private POI createPoi() {

        POI poi = new POI();
        poi.setName( "Local 1" );
        poi.setPositionX( 10L );
        poi.setPositionY( 20L );

        return poi;
    }


    @Test
    public void savePoiThrowsIllegalArgumentExceptionOnPositionX() {

        POI poi = createPoi();
        poi.setPositionX( -30L );
        
        exception.expect( IllegalArgumentException.class );
        
        poiService.save( poi );
    }
    
    
    @Test
    public void savePoiThrowsIllegalArgumentExceptionOnPositionY() {

        POI poi = createPoi();
        poi.setPositionY( -30L );
        
        exception.expect( IllegalArgumentException.class );
        
        poiService.save( poi );
    }
    
    
    @Test
    public void savePoiThrowsPOIAlreadyExistsException() {

        POI poi = createPoi();
        poiService.save( poi );
        
        exception.expect( POIAlreadyExistsException.class );
        
        poi = createPoi();
        poiService.save( poi );
    }
    

}
