package com.example.shopapplication;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFile {

    public static void writeToFile(ArrayList<ControllerAdmin.WarehouseItem> data, String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.append("Name, Price, Size\n"); // add header row

        for (ControllerAdmin.WarehouseItem item : data) {
            writer.append(item.getName());
            writer.append(",");
            writer.append(String.valueOf(item.getPrice()));
            writer.append(",");
            writer.append(String.valueOf(item.getSize()));
            writer.append("\n");
        }

        writer.flush();
        writer.close();
    }
}
