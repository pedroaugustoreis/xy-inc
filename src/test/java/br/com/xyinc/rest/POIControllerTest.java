package br.com.xyinc.rest;

import br.com.xyinc.model.POI;
import br.com.xyinc.rest.AbstractRestTest;
import br.com.xyinc.rest.POIController;
import br.com.xyinc.service.POIService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author pedro
 */
public class POIControllerTest extends AbstractRestTest {
    
    @Autowired
    private POIService poiService;
    
    private MockMvc restBusMockMvc;
    
    
    @Before
    public void setup() {

        POIController busResource = new POIController();
        ReflectionTestUtils.setField( busResource, "service", poiService );
        this.restBusMockMvc = MockMvcBuilders.standaloneSetup( busResource ).build();
    }
    
    
    @Test
    public void shouldReturnAllPOIs_WithoutProblem()
        throws Exception {

        poiService.save( createPoi() );

        restBusMockMvc.perform( get( "/api/poi" )
            .accept( APPLICATION_JSON_UTF8_VALUE ) )
            .andDo( print() )
            .andExpect( status().isOk() )
            .andExpect( content().contentType( APPLICATION_JSON_UTF8_VALUE ) );
    }
    
    
    @Test
    public void shouldCreatePOI_WithoutProblem() throws Exception {
        
        POI poi = createPoi();

        restBusMockMvc.perform( post( "/api/poi" )
            .contentType( contentType )
            .content( this.convertObjectToJsonBytes( poi ) )
            .accept( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
            .andExpect( status().isOk() )
            .andExpect( content().contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
            .andDo( print() )
            .andExpect( jsonPath( "$.id" ).exists() )
            .andExpect( jsonPath( "$.name" ).value( poi.getName() ) )
            .andExpect( jsonPath( "$.positionX" ).value( poi.getPositionX() ) )
            .andExpect( jsonPath( "$.positionY" ).value( poi.getPositionY() ) );
    }
    
    
    @Test
    public void shouldReturnConflict_WhenSavingPOIWithExistentPosition() throws Exception {
        
        poiService.save( createPoi() );

        restBusMockMvc.perform( post( "/api/poi" )
            .contentType( contentType )
            .content( this.convertObjectToJsonBytes( createPoi() ) )
            .accept( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
            .andExpect( status().isConflict() )
            .andDo( print() );
    }
    
}
