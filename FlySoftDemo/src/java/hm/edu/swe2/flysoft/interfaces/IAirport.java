/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.interfaces;

/**
 *
 * @author Philipp Chavaroche
 * @version
 */
public interface IAirport {

    int getAirportId();

    Integer getCityId();

    String getShortName();

    void setAirportId(int airportId);

    void setCityId(Integer cityId);

    void setShortName(String shortname);
    
}
