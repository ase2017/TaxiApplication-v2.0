package main.log;

/**
 * Created by Giorgos on 21-Mar-17.
 */
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataFileWriter {

    private final String OUTPUT_FILE = "logs.txt";

    public void exportLogs(String content) {

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {

            fileWriter = new FileWriter(OUTPUT_FILE);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);

            JOptionPane.showMessageDialog(null,"Logs exported successfully to file: " + OUTPUT_FILE);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {

            try {

                if (bufferedWriter != null)
                    bufferedWriter.close();

                if (fileWriter != null)
                    fileWriter.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
