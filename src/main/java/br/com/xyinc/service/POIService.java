package br.com.xyinc.service;


import br.com.xyinc.model.POI;
import java.util.List;


/**
 *
 * @author pedro
 */
public interface POIService {

    POI save( POI poi );


    List< POI > findAll();


    List< POI > findByReferenceAndDistance( Long positionX, Long positionY, Long distance );
}
