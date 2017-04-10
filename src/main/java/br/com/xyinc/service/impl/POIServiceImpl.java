package br.com.xyinc.service.impl;


import br.com.xyinc.model.POI;
import br.com.xyinc.repository.POIRepository;
import br.com.xyinc.service.POIService;
import br.com.xyinc.service.exception.POIAlreadyExistsException;
import br.com.xyinc.service.exception.POINotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


/**
 *
 * @author pedro
 */
@Service
public class POIServiceImpl implements POIService {

    private final Logger LOG = LoggerFactory.getLogger( this.getClass() );

    @Autowired
    private POIRepository repository;


    @Override
    @Transactional( readOnly = false )
    public POI save( POI poi ) {

        validatePOIisNotNull( poi );
        validatePOIPositionsIntegrity( poi );
        
        if ( poi.getId() != null ) {
            throw new IllegalArgumentException( "new POI can not have an id" );
        }

        POI poiOnBase = repository.findByPositionXAndPositionY( poi.getPositionX(), poi.getPositionY() );

        if ( poiOnBase != null ) {
            LOG.info( String.format( "POI already exists (%s, %s, %s)", poiOnBase.getName(), poiOnBase.getPositionX(), poiOnBase.getPositionY() ) );
            throw new POIAlreadyExistsException(
                String.format( "POI already exists with the coordinates (%s, %s).", poiOnBase.getPositionX(), poiOnBase.getPositionY() ) );
        }

        poi = repository.save( poi );
        return poi;
    }


    private void validatePOIisNotNull( POI poi ) {

        Assert.notNull( poi, "The POI can not be null." );
        Assert.notNull( poi.getName(), "The POI's name can not be null." );
        Assert.notNull( poi.getPositionX(), "The POI's positionX can not be null." );
        Assert.notNull( poi.getPositionY(), "The POI's positionY can not be null." );
    }


    private void validatePOIPositionsIntegrity( POI poi ) {

        validateValidPosition( poi.getPositionX() );
        validateValidPosition( poi.getPositionY() );
    }


    private void validateValidPosition( Long coordinate ) {

        if ( coordinate < 0 ) {
            throw new IllegalArgumentException( "Coordinate can not be negative." );
        }
    }


    private void validateValidDistance( Long distance ) {

        if ( distance < 0 ) {
            throw new IllegalArgumentException( "Distance can not be negative." );
        }
    }


    private void validateSearchForReferenceParams( Long positionX, Long positionY, Long distance ) {

        validateValidPosition( positionX );
        validateValidPosition( positionY );
        validateValidDistance( distance );
    }

    
    @Override
    @Transactional( readOnly = true )
    public List< POI > findByReferenceAndDistance( Long positionX, Long positionY, Long distance ) {

        Assert.notNull( positionX, "positionX can not be null." );
        Assert.notNull( positionY, "positionY can not be null." );
        Assert.notNull( distance, "distance can not be null." );

        validateSearchForReferenceParams( positionX, positionY, distance );

        List< POI > poiList = repository.findByReferenceAndDistance( positionX, positionY, distance );

        if ( poiList == null || poiList.isEmpty() ) {
            throw new POINotFoundException(
                String.format( "No POI was found with the reference location (%s, %s) and distance = %s.", positionX, positionY, distance ) );
        }
        
        return poiList;
    }


    @Override
    @Transactional( readOnly = true )
    public List< POI > findAll() {

        List< POI > poiList = repository.findAll();

        if ( poiList == null || poiList.isEmpty() ) {
            throw new POINotFoundException( "No POI was found in the base." );
        }

        return poiList;
    }

}
