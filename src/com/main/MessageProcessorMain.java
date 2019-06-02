/**
 * 
 */
package com.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.processor.LineParser;
import com.processor.SalesProcessor;

/**
 * @author smita.banerjee
 *
 */
public class MessageProcessorMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SalesProcessor sales = new SalesProcessor();
		// read input file
		try {
			String input;
			BufferedReader inputFile = new BufferedReader(new FileReader("inputFileFolder/inputFile.txt"));

			while ((input = inputFile.readLine()) != null) {
				sales.lineProcessor(input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
