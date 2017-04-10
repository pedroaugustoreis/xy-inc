package br.com.xyinc.view;


import br.com.xyinc.model.POI;


/**
 *
 * @author pedro
 */
public class POIView {

    private String name;

    private Long positionX;

    private Long positionY;


    public POIView( POI poi ) {

        name = poi.getName();
        positionX = poi.getPositionX();
        positionY = poi.getPositionY();
        
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

}
