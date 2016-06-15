package hm.edu.swe2.flysoft.entity.querybuilder;

/**
 * Class to select data categories for the query builder.
 * If an category is set to true, the table will be joined.
 * @author Philipp Chavaroche
 * @version 09.06.2016
 */
public class DataCategorySelector {
    
    private boolean airlineNeeded = false;
    private boolean destNeeded = false;
    private boolean endpointsNeeded = false;

    public DataCategorySelector() {
    }


    public boolean isAirlineNeeded() {
        return airlineNeeded;
    }

    public void setAirlineNeeded(boolean airlineNeeded) {
        this.airlineNeeded = airlineNeeded;
    }

    public boolean isDestNeeded() {
        return destNeeded;
    }

    public void setDestNeeded(boolean destNeeded) {
        this.destNeeded = destNeeded;
    }

    public boolean isEndpointsNeeded() {
        return endpointsNeeded;
    }

    public void setEndpointsNeeded(boolean endpointsNeeded) {
        this.endpointsNeeded = endpointsNeeded;
    } 

}
