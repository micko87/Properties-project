/**
 *   File Name: ConProperties.java<br>
 *
 *   LastName, FirstName<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: Apr 27, 2016
 *
 */

package com.sqa.MA.util.helpers;

import java.io.*;
import java.util.*;

/**
 * ConProperties //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author LastName, FirstName
 * @version 1.0.0
 * @since 1.0
 *
 */
public class ConProperties {

	public static HashMap<String, String> conProperties(Properties props) {
		// Create HashMap<String, String>
		HashMap<String, String> hmProps = new HashMap<String, String>();
		// Use a loop to put in Property values inside the HashMap
		for (Object key : props.keySet()) {
			// Add the element from the Properties file to the HashMap
			hmProps.put((String) key, props.getProperty((String) key));
		}

		// Return created HashMap
		return hmProps;
	}

	public static HashMap<String, String> conProperties(String propertiesFileLocation) {
		// Convert a text file into an ArrayList with each String element
		// representing a separate line
		ArrayList<String> dataLines = ConProperties.evalTextDataLines(propertiesFileLocation);
		// DIsplay the contents which are stored in an ArrayList<String> type of
		// variable
		//// ConProperties.displayDataLines(dataLines);
		// Further convert the ArrayList<String> into a HashMap collection of
		// key/ value
		HashMap<String, String> dataHashMap = ConProperties.convertLineContents(dataLines);
		// Display the contents of a HashMap<String,String> to console.
		ConProperties.displayDataContents(dataHashMap);

		return dataHashMap;

	}

	public static HashMap<String, String> convertLineContents(ArrayList<String> textFileLineContents) {
		// Create a HashMap of String keys and String values and initialize it.
		HashMap<String, String> dataHashMap = new HashMap<>();
		// Use a loop to iterate through all Stings in the collection of String
		// to retrieve the Keys and associated values.
		for (int i = 0; i < textFileLineContents.size(); i++) {
			// Convert the line for each element inside the collection of String
			// into an array with two elements representing the key and value
			// based on separating the String where there is a '=' present
			String[] lineElements = textFileLineContents.get(i).split("=");
			// Set the first element to the key variable and also use trim to
			// remove white space
			String key = lineElements[0].trim();
			// Set the second element to the value variable and also use trim to
			// remove white space
			String value = lineElements[1].trim();
			// Add the Keys and Values to the HashMap
			dataHashMap.put(key, value);
		}

		return dataHashMap;
	}

	public static void displayDataContents(HashMap<String, String> properties) {
		// Display properties header
		System.out.println("\nHM Props:");
		// Use a Set name keys to hold the HaspMap properties key values
		Set<String> keys = properties.keySet();
		// Use a for loop to iterate through keys and get their corresponding
		// key value
		for (String key : keys) {
			// Display key
			System.out.print("Property:[" + key);
			// Display value associated with that key
			System.out.println("] - Value:[" + properties.get(key) + "]");
		}
	}

	public static void displayDataLines(ArrayList<String> textFileLineContents) {
		// TODO Display Contents of passed parameter to console
		for (int i = 0; i < textFileLineContents.size(); i++) {
			System.out.println(textFileLineContents.get(i));
		}
	}

	// Method to display properties
	public static void displayProperties(Properties props) {
		HashMap<String, String> hmProps = conProperties(props);
		ConProperties.displayDataContents(hmProps);

	}

	public static ArrayList<String> evalTextDataLines(String textFileLoadLocation) {
		// Create ArrayList and initialize it
		ArrayList<String> dataLines = new ArrayList<String>();
		// This will reference one line at a time
		String line;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(textFileLoadLocation);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				// Add the line to the ArrayList Collection
				dataLines.add(line);
			}
			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + textFileLoadLocation + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + textFileLoadLocation + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		// TODO Return the ArrayList collection
		return dataLines;
	}

	// Method to load properties
	public static Properties loadProperties(String fileLocation) {
		// Create a Properties object
		Properties props = new Properties();

		try {
			// Create a FileInputStream by passing in a fileLocation
			InputStream is = new FileInputStream(fileLocation);
			// Load the properties based on a fileLocation
			props.load(is);
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Return the properties object loaded
		return props;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties props = ConProperties.loadProperties("src/main/resources/data03.properties");
		ConProperties.displayProperties(props);
	}

}
