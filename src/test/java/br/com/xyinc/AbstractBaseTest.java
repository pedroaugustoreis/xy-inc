package br.com.xyinc;


import br.com.xyinc.model.POI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author pedro
 */
@RunWith( SpringRunner.class )
@SpringBootTest
@Transactional
public class AbstractBaseTest {

    public POI createPoi() {

        POI poi = new POI();
        poi.setName( "Local Teste" );
        poi.setPositionX( 30L );
        poi.setPositionY( 60L );

        return poi;
    }


    public List< POI > generatePOIList() {

        // List< POI > poiList = new ArrayList<>();
        List< POI > poiList = Arrays.asList( new POI[] {
            new POI( "Local 1", 300L, 300L ),
            new POI( "Local 2", 301L, 301L ),
            new POI( "Local 3", 310L, 310L ),
            new POI( "Local 4", 315L, 315L ),
            new POI( "Local 5", 320L, 320L ),} );

        return poiList;
    }

}
