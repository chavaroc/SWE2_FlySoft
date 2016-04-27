package hm.edu.swe2.flysoft.parser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hm.edu.swe2.flysoft.parser.CsvParser;
import hm.edu.swe2.flysoft.parser.ParserConfig;
import hm.edu.swe2.flysoft.parser.model.Flight;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.util.Assert;

/**
 *
 * @author Philipp Chavaroche
 */
public class TestCsvParser {
    
    @Test
    public void TestCsvParser() throws Exception {
        String path = "C:\\Users\\xYrs\\Documents\\Hm\\6_Semester\\SWE2\\Transtats_Data\\Dometic\\968296915_T_ONTIME.csv";
        ParserConfig config = ParserConfig.getInstance();
        CsvParser<Flight> parser = new CsvParser<Flight>(path, config,
            ',', Flight.class);
        List<Flight> result = parser.parse();
        Assert.isTrue(true); // TODO dummy
    
    }
    
}
