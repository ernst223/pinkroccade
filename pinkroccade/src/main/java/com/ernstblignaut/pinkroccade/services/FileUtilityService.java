package com.ernstblignaut.pinkroccade.services;

import com.ernstblignaut.pinkroccade.models.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtilityService {
    public File getCSV(List<Person> persons) {
        File myFile = new File("requirement3b.csv");
        try {
            FileWriter out = new FileWriter(myFile);
            String[] myHeaders = new String[4];
            myHeaders[0] = "Id";
            myHeaders[1] = "Name";
            myHeaders[2] = "Parents";
            myHeaders[3] = "Partner";
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(myHeaders));
            List<String> body;

            for(Person person: persons) {
                body = new ArrayList<>();
                body.add(person.getId().toString());
                body.add(person.getName());
                body.add(person.getParent1().getName() + " and " + person.getParent2().getName());
                body.add(person.getPartner().getName());
                printer.printRecord(body);
            }

            printer.close();
            return myFile;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
