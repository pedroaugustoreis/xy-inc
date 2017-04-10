package br.com.xyinc.repository;


import br.com.xyinc.model.POI;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 *
 * @author pedro
 */
public interface POIRepository extends JpaRepository< POI, Long > {

    POI findByPositionXAndPositionY( Long positionX, Long positionY );
    
    
    @Query( "SELECT p FROM POI p WHERE ((p.positionX >= :positionX - :distance AND p.positionX <= :positionX + :distance) AND "
            + "(p.positionY >= :positionY - :distance AND p.positionY <= :positionY + :distance))" )
    List< POI > findByReferenceAndDistance( @Param( "positionX" ) Long positionX,
            @Param( "positionY" ) Long positionY,
            @Param( "distance" ) Long distance );

}
