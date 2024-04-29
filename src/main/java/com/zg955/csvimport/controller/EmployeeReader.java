package com.zg955.csvimport.controller;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.zg955.csvimport.CustomProperties;
import com.zg955.csvimport.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Controller
public class EmployeeReader {

    //TODO try methods from here
    // https://www.baeldung.com/java-csv-file-array
    // Jackson too : https://stackoverflow.com/questions/22485041/how-to-easily-process-csv-file-to-listmyclass

    // TODO detect & skip headline if the csv file has one ?

    @Autowired
    CustomProperties customProperties;

    @Autowired
    EmployeeService employeeService;

    public void readCsvWithBufferedReader() throws Exception {
        log.info("Reading data from CSV file with a Buffered Reader");
        List<List<String>> results = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(customProperties.getDataPath()));
        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] values = line.split(customProperties.getDelimiter());
            results.add(Arrays.asList(values));
        }

        employeeService.handleCsvValues(results);
    }

    //FIXME doesnt read any data as is
    public void readCsvWithScanner() throws Exception {
        log.info("Reading data from CSV file with a Scanner");
        List<List<String>> results = new ArrayList<>();
        Scanner scanner = new Scanner(new File(customProperties.getDataPath()));
        while (scanner.hasNextLine()) {
            log.info("first while entered");
            List<String> values = new ArrayList<>();
            Scanner rowScanner = new Scanner(scanner.nextLine());
            rowScanner.useDelimiter(customProperties.getDelimiter());
            while (rowScanner.hasNext()) {
                log.info("second while entered");
                values.add(rowScanner.next());
                log.info("values : {}", values);
            }
            log.info("second while ended with values : {}", values);
            results.add(values);
        }
        log.info("first while ended with results : {}", results);

        employeeService.handleCsvValues(results);
    }

    public void readCsvWithOpenCsv() throws Exception {
        log.info("Reading data from CSV file with open csv");
        List<List<String>> results = new ArrayList<>();

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(customProperties.getDelimiter().toCharArray()[0])
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(new FileReader(customProperties.getDataPath()))
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();

        String[] values = null;
        while ((values = csvReader.readNext()) != null) {
            results.add(Arrays.asList(values));
        }

        employeeService.handleCsvValues(results);
    }

}
