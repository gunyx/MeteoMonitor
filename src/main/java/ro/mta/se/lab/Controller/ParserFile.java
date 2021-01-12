package ro.mta.se.lab.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ParserFile {

    private static ParserFile instance = null;

    private List<String> idList;
    private List<String> nameList;
    private List<String> latList;
    private List<String> lonList;
    private List<String> countryList;


    private ParserFile() {

    }

    public static ParserFile getInstance() {
        if (instance == null) {
            instance = new ParserFile();
            //aici eroare ca nu am trimis fisier de in
        }
        return instance;
    }

    public static ParserFile getInstance(String configFileLocation) throws FileNotFoundException {
        if (instance == null) {
            instance = new ParserFile();
            citireDate(configFileLocation);
        }
        return instance;
    }

    private static void citireDate(String fileInLocation) throws FileNotFoundException {

        File file = new File(fileInLocation);
        Scanner scanner = new Scanner(file);

        String linie=null;
        while (scanner.hasNextLine()) {
            linie = scanner.nextLine();
            String[] elementeLinie = linie.split("\t");
            if (elementeLinie.length != 5)
               //exceptie
            setareDate(elementeLinie);
        }


    }

    private static void setareDate(String[]information)
    {
        instance.idList.add(information[0]);
        instance.nameList.add(information[1]);
        instance.latList.add((information[2]));
        instance.lonList.add((information[3]));
        instance.countryList.add(information[4]);
    }

    public List<String> getIdList() {
        return idList;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public List<String> getLatList() {
        return latList;
    }

    public List<String> getLonList() {
        return lonList;
    }

    public List<String> getCountryList() {
        return countryList;
    }
}
