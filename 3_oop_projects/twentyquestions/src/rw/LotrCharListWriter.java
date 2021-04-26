package rw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * LotrCharListWriter.java
 * 
 * Modified from Lab6
 * 
 * Want to save known character list as a text file. Important when game wants
 * to learn characters. This is a quicker way to obtain known characters instead
 * of having to traverse the tree to obtain all the leafs. (assume when tree
 * grows)
 * 
 * @author adelyn.yeoh
 *
 *         CS201- Final Project
 */
public class LotrCharListWriter {

	private static FileWriter writer;

	/**
	 * Method to write file given a string
	 * 
	 * @param someString
	 *            String
	 * @param writeFile
	 *            File
	 */
	public static void writeFile(String someString, File writeFile) {

		try {
			writer = new FileWriter(writeFile);
			// catch IO error

			writer.write(someString);

			// ensure all data has been written
			writer.flush();

			// close file
			writer.close();
		}
		// catch error, if thrown
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
