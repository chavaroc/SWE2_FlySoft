package hm.edu.swe2.flysoft.ui;
/**
 * Represents a list cities.
 * Necessary for the Spring-Form-Object-Binding.
 * @author Philipp Chavaroche
 * @version 11.05.2016
 */
public class CityFilter {

    private String[] cities;

    /**
     * Get all current cities.
     * @return A list of cities.
     */
    public String[] getCities() {
        return cities;
    }

    /**
     * Set a list of cities.
     * @param cities The cities, that should be set.
     */
    public void setCities(String[] cities) {
        this.cities = cities;
    }
    
    
}
