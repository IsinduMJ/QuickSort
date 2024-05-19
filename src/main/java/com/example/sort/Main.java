package com.example.sort;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class Main {

    public static void main(String[] args) {
        try {
            // Read data from Excel file
            List<Integer> data = new ArrayList<>();

            // Set the Excel file
            FileInputStream fis = new FileInputStream(new File("products.xlsx"));
            Workbook workbook = new XSSFWorkbook(fis);
            
            // Assuming the data is in the first sheet
            Sheet sheet = workbook.getSheetAt(0); 

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        data.add((int) cell.getNumericCellValue());
                    }
                }
            }

            // Close the workbook
            workbook.close();

            // Implement QuickSort
            quickSort(data, 0, data.size() - 1);

            // Display sorted data
            System.out.println("Sorted Data:");
            for (Integer value : data) {
                System.out.print(value + " ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void quickSort(List<Integer> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(List<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr.get(j) <= pivot) {
                i++;

                // Swap arr[i] and arr[j]
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        // Swap arr[i+1] and arr[high] (or pivot)
        int temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        return i + 1;
    }
}
