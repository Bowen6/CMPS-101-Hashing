package com.BowenBrooks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestDriver {

    public static void main(String[] args) {
        UIMS program = new UIMS();
        String fileName = "src/INPUT.txt";
        // This will reference one line at a time
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                program.add(line);
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        program.display();

        program.writetoFile();
    }
}
