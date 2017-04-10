package br.com.xyinc.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;


/**
 *
 * @author pedro
 */
@Entity
@Table( name = "POI" )
public class POI implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID", unique = true, nullable = false )
    private Long id;

    @Column( name = "NAME", length = 200, nullable = false )
    private String name;

    @Column( name = "POSITION_X", nullable = false )
    private Long positionX;

    @Column( name = "POSITION_Y", nullable = false )
    private Long positionY;

    @CreatedDate
    @Column( name = "CREATED_AT", nullable = false )
    @JsonIgnore
    private ZonedDateTime createdAt = ZonedDateTime.now();
    
    
    public POI() {
        
    }
    
    
    public POI( String name, Long positionX, Long positionY ) {
        
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
    }


    public Long getId() {

        return id;
    }


    public void setId( Long id ) {

        this.id = id;
    }


    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }


    public Long getPositionX() {

        return positionX;
    }


    public void setPositionX( Long positionX ) {

        this.positionX = positionX;
    }


    public Long getPositionY() {

        return positionY;
    }


    public void setPositionY( Long positionY ) {

        this.positionY = positionY;
    }


    public ZonedDateTime getCreatedAt() {

        return createdAt;
    }


    public void setCreatedAt( ZonedDateTime createdAt ) {

        this.createdAt = createdAt;
    }

}
