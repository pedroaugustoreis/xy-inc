package br.com.xyinc.rest;


import br.com.xyinc.model.POI;
import br.com.xyinc.service.POIService;
import br.com.xyinc.view.POIView;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


/**
 *
 * @author pedro
 */
@RestController
@RequestMapping( value = "/api/poi", produces = APPLICATION_JSON_UTF8_VALUE )
public class POIController {

    private final Logger LOG = LoggerFactory.getLogger( this.getClass() );

    @Autowired
    private POIService service;


    @RequestMapping( value = "", method = RequestMethod.POST )
    public POI save( @RequestBody POI poi ) {

        LOG.info( String.format( "saving POI with name = %s and positions (%s, %s)", poi.getName(), poi.getPositionX(), poi.getPositionY() ) );
        return service.save( poi );
    }


    @RequestMapping( value = "", method = RequestMethod.GET )
    public List< POIView > findAll() {

        LOG.info( "finding all POIs" );
        List< POI > poiList = service.findAll();

        List< POIView > poiViewList = new ArrayList<>();

        poiList.forEach( poi -> {
            poiViewList.add( new POIView( poi ) );
        } );

        return poiViewList;
    }
    
    
    @RequestMapping( value = "/findByReferenceAndDistance", method = RequestMethod.GET )
    public List< POIView > findByReferenceAndDistance( Long positionX, Long positionY, Long distance ) {

        LOG.info( "finding all POIs" );
        List< POI > poiList = service.findByReferenceAndDistance( positionX, positionY, distance );

        List< POIView > poiViewList = new ArrayList<>();

        poiList.forEach( poi -> {
            poiViewList.add( new POIView( poi ) );
        } );

        return poiViewList;
    }

}
