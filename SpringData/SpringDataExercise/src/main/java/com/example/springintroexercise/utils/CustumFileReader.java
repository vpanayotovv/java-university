package com.example.springintroexercise.utils;

import java.io.FileNotFoundException;
import java.util.List;

public interface CustumFileReader {

    List<String> read(String path) throws FileNotFoundException;

}
