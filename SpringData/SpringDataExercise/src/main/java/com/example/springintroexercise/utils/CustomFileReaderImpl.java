package com.example.springintroexercise.utils;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CustomFileReaderImpl implements CustumFileReader {
    @Override
    public List<String> read(String path) throws FileNotFoundException {
        List<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(new FileInputStream(path));
        String line = scanner.nextLine();
        while (scanner.hasNextLine()){
            if (!line.equals("")) {
                result.add(line);
            }
            line = scanner.nextLine();
        }

        return result;
    }
}
