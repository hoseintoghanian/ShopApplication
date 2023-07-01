package com.example.shopapplication;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFile {

    public static void writeToFile(ArrayList<Item> data, String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.append("Name, Price, Size\n"); // add header row

        for (Item item : data) {
            writer.append(item.name);
            writer.append(",");
            writer.append(String.valueOf(item.price));
            writer.append(",");
            writer.append(String.valueOf(item.size));
            writer.append("\n");
        }

        writer.flush();
        writer.close();
    }
}
