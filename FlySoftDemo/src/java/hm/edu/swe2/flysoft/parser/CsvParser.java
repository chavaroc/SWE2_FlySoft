/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hm.edu.swe2.flysoft.parser;

import com.opencsv.CSVReader;
import hm.edu.swe2.flysoft.parser.model.MethodDescriptor;
import hm.edu.swe2.flysoft.parser.model.interfaces.ICsvFieldMapping;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Parse a csv file to java objects via a mapping.
 * @author Philipp Chavaroche
 * @version 29.04.2016.
 */
public class CsvParser<T> {
    
    private String csvFileName;
    private ICsvFieldMapping mapper;
    private List<String> headerColumnNames;
    private char csvSeperator;
    private Class<T> genType;

    /**
     * Construct a new csv parser.
     * @param csvFileName The file name of the csv file, that should be parsed.
     * @param mapping The mappting between csv columns and object setter.
     * @param csvSeperator The seperator, that is used in the csv file.
     * @param genType The type of the object.
     */
    public CsvParser(String csvFileName, ICsvFieldMapping mapping,
        char csvSeperator, Class<T> genType) {
        this.csvFileName = csvFileName;
        this.mapper = mapping;
        this.csvSeperator = csvSeperator;
        headerColumnNames = new ArrayList<>();
        this.genType = genType;
    }
    
    /**
     * Parse all lines from the csv file to a list of objects.
     * @return
     * @throws Exception 
     */
    public List<T> parse() throws Exception {
        List<T> resultList = new ArrayList<>();
        
        try(FileReader fileReader = new FileReader(csvFileName);
            CSVReader csvReader = new CSVReader(fileReader, csvSeperator))
        {
            String[] line;
            int lineIndex = 0;
            // Read out csv file
            while ((line = csvReader.readNext()) != null) {                
                if(lineIndex == 0){
                    headerColumnNames = Arrays.asList(line);
                }
                else{
                    try{
                        resultList.add(parseToObject(line));
                    }
                    catch(Exception ex){
                        throw ex;
                    }
                }
                lineIndex++;
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
		return resultList;
    }
   
    
    /**
     * Parse a csv token set to a object
     * @param csvTokens
     * @param type
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException 
     */
    private T parseToObject(String[] csvTokens) throws InstantiationException,
        IllegalAccessException, NoSuchMethodException, IllegalArgumentException,
        InvocationTargetException{
        T newObject = genType.newInstance();
        // Iterate over all input columns.
        // If we have a mapping for one of them -> parse, otherwise skip
        // Attentsion: order (via position) of the header and the given
        // token list must be equals
        for(int position = 0; position < csvTokens.length; position++){
            String token = csvTokens[position];
            // Try to find mapping
            MethodDescriptor target = mapper.getMapping()
                .get(headerColumnNames.get(position));
            if(target != null){
                // A mapping is defined for that column
                // Load setter method via mapping 
                Method setMethod = genType.getMethod(
                    target.getMethodName(),
                    target.getArgumentType());
                Object argument = null;
                // Try to parse value to the same type as the argument 
                // of the setter have.
                try {
                    argument = parseArgument(token, target.getArgumentType());
                    // Set value into object
                    setMethod.invoke(newObject, argument);
                } catch (NumberFormatException | ParseException ex) {
                    System.out.println("Error while try to parse '"+token
                        +"' to type '"+target.getArgumentType().getName()
                        +"'. Target method: "+setMethod.getName()+".");
                }
            }
            else{
                // No mapping defined for that column -> skip and continue
            }
        }
        System.out.println(newObject.toString());
        return newObject;
    }
    
    /**
     * Parse the given string into the given type.
     * Supports only int, date, boolean conversions.
     * @param arg
     * @param targetType
     * @return The value in the given type or null, if it couldnt be parsed.
     * @throws IllegalArgumentException if 
     */
    private Object parseArgument(String arg, Class<?> targetType) throws IllegalArgumentException, ParseException{
        Object parsedValue = null;
        if(targetType.equals(int.class) || targetType.equals(Integer.class)){
            parsedValue = Integer.parseInt(arg);
        }
        else if(targetType.equals(double.class)){
            if("".equals(arg)){
                parsedValue = 0.0;
            }
            else{
                parsedValue = Double.parseDouble(arg);
            }
        }
        else if (targetType.equals(boolean.class)){
            if("1".equals(arg) || "1.00".equals(arg)){
                arg = "true";
            }
            else if ("0".equals(arg) || "0.00".equals(arg)){
                arg = "false";
            }
            parsedValue = Boolean.parseBoolean(arg);
        }
        else if (targetType.equals(String.class)){
            parsedValue = arg;
        }
        else if (targetType.equals(Date.class)){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            parsedValue = format.parse(arg);
        }
        else{
            throw new IllegalArgumentException("Error: Could not parse '"
                + arg + "' to " +targetType.getName()
                + ". Type not supported.");
        }
        return parsedValue;
    }
}
