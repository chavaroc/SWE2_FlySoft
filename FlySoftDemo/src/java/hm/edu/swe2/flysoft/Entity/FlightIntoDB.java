/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.Entity;

import hm.edu.swe2.flysoft.interfaces.IFlight;
import hm.edu.swe2.flysoft.interfaces.IFlightEndPoints;

/**
 *
 * @author Zwen
 */
public class FlightIntoDB {
    
    private final IFlight flight;
    private final IFlightEndPoints endpoint;
    
    public FlightIntoDB(final IFlight flight, final IFlightEndPoints endpoint){       
        this.flight = flight;
        this.endpoint = endpoint;
    }
    
    public IFlightEndPoints getEndpoint(){
        return endpoint;
    }
    
    public IFlight getFlight(){
        return flight;
    }   
}
