package rw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * LotrCharListReader.java
 * 
 * Modified from Lab 6.
 * 
 * Want to generate a known character list based on what the tree knows. We have
 * saved the known character list in a text file. So read from that text file to
 * get the character list.
 * 
 * @author adelyn.yeoh
 *
 *         CS201 - Final Project
 */
public class LotrCharListReader {

	// file variable
	private static File file = new File("KnownCharacters.txt");

	// buffered reader
	private static BufferedReader reader;

	// Save character list as a string
	private static String lotrCharList;

	

	/**
	 * instantiate the file reader and catch a file not found exception
	 * 
	 * @param file
	 *            File
	 * 
	 * @return the line or null if there was no line
	 **/

	public static void readFile() {
		// attempt to create the fileReader
		try {
			// first creates a FileReader of the specified file
			// may throw exception
			FileReader fileReader = new FileReader(file);

			// if control gets here, no exception was thrown

			// wrap fileReader in BufferedReader for
			// line reading capability
			reader = new BufferedReader(fileReader);

		}
		// catch the exception, if thrown
		catch (FileNotFoundException fnfe) {
			// file was not found
			// print error
			fnfe.printStackTrace();
		}
	}

	/**
	 * Method to get the character list from file
	 * 
	 * @return lotrCharList String
	 */
	public static String getCharList() {
		
		// Initialize variable
		lotrCharList = "";

		// Want to break string into tokens so use String Tokenizer with "*"
		// character as a delimiter. Use "*" because this character is unlikely
		// to show up in a name or title
		StringTokenizer tokenizer = new StringTokenizer(parseFile(), "*");

		// Save tokens as a vertical list to be printed
		while (tokenizer.hasMoreTokens()) {
			lotrCharList = lotrCharList + tokenizer.nextToken("*") + "\n";
		}

		return lotrCharList;
	}

	/**
	 * parse the file line by line
	 * 
	 * @return return the body of text to be displayed
	 **/
	public static String parseFile() {
		
		readFile();
		
		// hold the next line
		String currentLine = getNextLine();

		// body is empty at first
		String bodyText = "";

		// while there is a line
		while (currentLine != null) {
			// update body
			bodyText = bodyText + currentLine + "\n";

			// get next line
			currentLine = getNextLine();
		}

		return bodyText;
	}

	/**
	 * Get the next line in the file.
	 * 
	 * @return the line or null if there was no line
	 **/
	private static String getNextLine() {
		
		// try to parse each line of file
		try {
			// try to read a line
			// if this is successful, return it
			return reader.readLine();
		}
		// catch exception, if one is thrown
		catch (IOException e) {
			// if there was an exception,
			// return the empty string
			return null;
		}
	}

}
