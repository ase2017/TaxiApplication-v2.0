package main.log;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class Name: DataFileWriter.java
 *
 * Description: This class exports a given string to a predefined
 *              text file.
 *
 * @author George Goniotakis
 * @since Mar 18, 2017
 */

public class DataFileWriter {

    private final String OUTPUT_FILE = "logs.txt"; //The name of the output file

    /**
     * This method exports a string to a predefined file.
     *
     * @param content The string that we want to print to the file
     * @throws IOException If there is an exception with the file
     * @throws SecurityException If there is a security error
     */
    public void exportLogs(String content) {

        FileWriter fileWriter = null; //Create a new FileWriter
        BufferedWriter bufferedWriter = null; //Create a new BufferedReader

        try {

            fileWriter = new FileWriter(OUTPUT_FILE); //Initialize the FileWriter
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content); //Write the string to the BufferedWriter

            JOptionPane.showMessageDialog(null,"Logs exported successfully to file: " + OUTPUT_FILE); //Show success message after epxorting the file

        } catch (IOException e) {
            System.err.println(e.getMessage()); //Show the error message
        } finally {

            try {

                /* Finally close the BufferedWriter */
                if (bufferedWriter != null)
                    bufferedWriter.close();

                /* Finally close the FileWriter */
                if (fileWriter != null)
                    fileWriter.close();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            } catch (SecurityException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
