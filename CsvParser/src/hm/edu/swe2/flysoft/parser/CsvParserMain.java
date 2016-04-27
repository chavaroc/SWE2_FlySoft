/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.parser;

import hm.edu.swe2.flysoft.parser.model.Flight;
import java.util.List;

/**
 *
 * @author xYrs
 */
public class CsvParserMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ParserConfig config = ParserConfig.getInstance();
        CsvParser<Flight> parser = new CsvParser<Flight>(args[0], config,
            ",", Flight.class);
        List<Flight> result = parser.parse();
    }
}
