package br.com.xyinc.service;


import br.com.xyinc.AbstractBaseTest;
import br.com.xyinc.model.POI;
import br.com.xyinc.service.POIService;
import br.com.xyinc.service.exception.POIAlreadyExistsException;
import br.com.xyinc.service.exception.POINotFoundException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;


public class PoiApplicationTests extends AbstractBaseTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private POIService poiService;


    @Test
    public void shouldThrowIllegalArgumentException_WhenPositionXIsNegative() {

        POI poi = createPoi();
        poi.setPositionX( -30L );

        exception.expect( IllegalArgumentException.class );

        poiService.save( poi );
    }


    @Test
    public void shouldThrowIllegalArgumentException_WhenPositionYIsNegative() {

        POI poi = createPoi();
        poi.setPositionY( -30L );

        exception.expect( IllegalArgumentException.class );

        poiService.save( poi );
    }


    @Test
    public void shouldThrowPOIAlreadyExistsException_WhenPOIAlreadyExists() {

        POI poi = createPoi();
        poiService.save( poi );

        exception.expect( POIAlreadyExistsException.class );

        poi = createPoi();
        poiService.save( poi );
    }


    @Test
    public void shouldSavePOI_WithoutProblem() {

        List< POI > poiList = poiService.findAll();
        Integer countBeforeSave = poiList.size();

        POI poi = createPoi();
        poiService.save( poi );

        assertEquals( countBeforeSave + 1, poiService.findAll().size() );
    }


    @Test
    public void shouldReturnAllPOIsByReferenceAndDistance_WithoutProblem() {

        generatePOIList().forEach( p -> {
            poiService.save( p );
        } );

        List< POI > poiList = poiService.findByReferenceAndDistance( 305L, 305L, 5L );
        assertEquals( 3, poiList.size() );

    }
    
    
    @Test
    public void shouldThrowsPOINotFoundException_WhenThereIsNoPOI() {

        generatePOIList().forEach( p -> {
            poiService.save( p );
        } );

        exception.expect( POINotFoundException.class );
        
        poiService.findByReferenceAndDistance( 505L, 505L, 5L );
    }

}
