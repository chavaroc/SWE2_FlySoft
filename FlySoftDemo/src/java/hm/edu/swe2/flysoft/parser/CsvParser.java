package hm.edu.swe2.flysoft.parser;

import com.opencsv.CSVReader;
import hm.edu.swe2.flysoft.parser.model.MethodDescriptor;
import hm.edu.swe2.flysoft.interfaces.ICsvFieldMapping;
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
 * Parse a csv file to java objects (T) via a mapping.
 * @author Philipp Chavaroche
 * @version 29.04.2016. 
 */
public class CsvParser<T> {
    
    private final String csvFileName;
    private final ICsvFieldMapping mapper;
    private List<String> headerColumnNames;
    private final char csvSeperator;
    private final Class<T> genType;

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
     * The first line will be interpretet as header column line.
     * @return A list of parsed objects.
     * @throws Exception In case of problems with the mapping (reflection)
     * or the input file.
     */
    public List<T> parse() throws Exception {
        List<T> resultList = new ArrayList<>();
        
        // init csv reader
        try(FileReader fileReader = new FileReader(csvFileName);
            CSVReader csvReader = new CSVReader(fileReader, csvSeperator))
        {
            String[] line;
            int lineIndex = 0;
            // Read out line per line
            while ((line = csvReader.readNext()) != null) {                
                // If its the first line -> save header column names
                if(lineIndex == 0){
                    headerColumnNames = Arrays.asList(line);
                }
                else{
                    try{
                        resultList.add(parseToObject(line));
                    }
                    catch(InstantiationException | IllegalAccessException
                        | NoSuchMethodException | IllegalArgumentException
                        | InvocationTargetException ex){
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
     * Parse a csv token set to a object.
     * @param csvTokens The tokens from the csv line, that should be parsed.
     * @return The object that is generated out of the given csv tokens.
     * @throws InstantiationException Object can not creaete an instance.
     * @throws IllegalAccessException If object is not compatible to mapping.
     * @throws NoSuchMethodException In case of mapping error.
     * @throws IllegalArgumentException In case of mapping error.
     * @throws InvocationTargetException In case of mapping error.
     */
    private T parseToObject(String[] csvTokens) throws InstantiationException,
        IllegalAccessException, NoSuchMethodException, IllegalArgumentException,
        InvocationTargetException{
        Object argument;
        String token;
        MethodDescriptor target;
        Method setMethod;
        T newObject = genType.newInstance();
        // Iterate over all input columns.
        // If we have a mapping for one of them -> parse, otherwise skip
        // Attentsion: order (via position) of the header and the given
        // token list must be equal.
        for(int position = 0; position < csvTokens.length; position++){
            token = csvTokens[position];
            // Try to find mapping (match via column / header name)
            target = mapper.getMapping()
                .get(headerColumnNames.get(position));
            if(target != null){
                // A mapping is defined for that column.
                // Find setter method of the target type via reflection.
                setMethod = genType.getMethod(target.getMethodName(),
                    target.getArgumentType());
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
        return newObject;
    }
    
    /**
     * Parse the given string into the given type.
     * Supports only int, date, boolean conversions.
     * @param arg The string value, that should be parsed.
     * @param targetType The target type of the conversion.
     * @return The value in the given type or null, if it couldnt be parsed.
     * @throws IllegalArgumentException If type is not supported.
     */
    private Object parseArgument(String arg, Class<?> targetType) throws IllegalArgumentException, ParseException{
        Object parsedValue = null;
        if(targetType.equals(int.class) || targetType.equals(Integer.class)){
            // if the string contains a point, it can parsed directly to int
            // So we parse first into double, and than to int.
            if(arg.contains("."))
            {
                double tempDblValue = Double.parseDouble(arg);
                parsedValue = (int)tempDblValue;
            }
            else if("".equals(arg)){
                parsedValue = 0;
            }
            else{
                parsedValue = Integer.parseInt(arg);
            }
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