/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hm.edu.swe2.flysoft.parser;

import hm.edu.swe2.flysoft.parser.model.Flight;
import hm.edu.swe2.flysoft.parser.model.MethodDescriptor;
import hm.edu.swe2.flysoft.parser.model.interfaces.ICsvFieldMapping;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Philipp Chavaroche
 * @version 
 */
public class CsvParser<T> {
    
    private String csvFileName;
    private ICsvFieldMapping mapper;
    private List<String> headerColumnNames;
    private String csvSeperator;
    private Class<T> genType;

    public CsvParser(String csvFileName, ICsvFieldMapping mapping,
        String csvSeperator, Class<T> genType) {
        this.csvFileName = csvFileName;
        this.mapper = mapping;
        this.csvSeperator = csvSeperator;
        headerColumnNames = new ArrayList<String>();
        //this.genType = (Class<T>)(((ParameterizedType)CsvParser.class.getGenericSuperclass()).getActualTypeArguments()[0]);
        this.genType = genType;
    }
    
    public List<T> parse() {
        List<T> resultList = new ArrayList<T>();
        
        try(FileReader fileReader = new FileReader(csvFileName);
        BufferedReader br = new BufferedReader(fileReader))
        {
            String line;
            int lineIndex = 0;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] columnEntries = line.split(csvSeperator);
                
                if(lineIndex == 0){
                    processHeaderLine(columnEntries);
                }
                else{
                    try {
                        resultList.add(parseToObject(columnEntries));
                        /*try{
                        resultList.add(parseToObject(columnEntries));
                        }
                        catch(Exception ex){
                        System.out.println(ex);
                        } */
                    } catch (InstantiationException ex) {
                        Logger.getLogger(CsvParser.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(CsvParser.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(CsvParser.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(CsvParser.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(CsvParser.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void processHeaderLine(String[] headerTokens){
        // its the first line -> header
        for(int index = 0; index < headerTokens.length; index++){
            headerTokens[index] = trimQuotes(headerTokens[index]);
        }
        headerColumnNames = Arrays.asList(headerTokens);
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
        // Attentsion: order (via position) of the header and the given
        // token list must be equals
        for(int position = 0; position < csvTokens.length; position++){
            String token = trimQuotes(csvTokens[position]);
            MethodDescriptor target = mapper.getMapping()
                .get(headerColumnNames.get(position));
            if(target != null){
                // A mapping is defined for that column -> set to object
                Method setMethod = genType.getMethod(
                    target.getMethodName(),
                    target.getArgumentType());
                setMethod.invoke(newObject,
                    parseArgument(token, target.getArgumentType()));
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
     * @param arg
     * @param targetType
     * @return The value in the given type or null, if it couldnt be parsed.
     * @throws IllegalArgumentException if 
     */
    private Object parseArgument(String arg, Class<?> targetType) throws IllegalArgumentException{
        Object parsedValue;
        try{
            if(targetType.equals(int.class)){
                parsedValue = Integer.parseInt(arg);
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
        }
        catch(ParseException ex){
            System.out.println(ex);
            parsedValue = null;
        }
        return parsedValue;
    }
    
    private String trimQuotes(String str){
        str = str.replace("\"", "");
        return str;
    }
}
