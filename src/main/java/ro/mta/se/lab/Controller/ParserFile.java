package ro.mta.se.lab.Controller;

import ro.mta.se.lab.Model.Locatie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gunyx
 * Clasa singletone ce se ocupa cu parsarea fisierului de intrare. Acesta clasa citeste fisierul de intrare si stocheaza intr-o lista toate locatiile trimise.
 *
 */

public class ParserFile {

    private static ParserFile instance = null;

    private static final List<Locatie> listaLocatiiIntrare=new ArrayList<>();


    private ParserFile() {

    }

    public List<Locatie> getListaLocatiiIntrare() {
        return listaLocatiiIntrare;
    }

    public static ParserFile getInstance() {
        if (instance == null) {
            instance = new ParserFile();
            //aici exceptie ca nu am trimis fisier de in
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

    /**
     * Metoda pentru citirea fisierului de intrare
     * @param fileInLocation parametru prin care este trimisa locatia(numele) fisierului de intrare
     * @throws FileNotFoundException exceptie pentru inexistenta fisier in
     */
    private static void citireDate(String fileInLocation) throws FileNotFoundException {

        File file = new File(fileInLocation);
        if (!file.exists()) {
        //exceptie
            System.out.println("Fisier de intrare lipsa");
            return;
        }
        Scanner scanner = new Scanner(file);

        String linie;
        while (scanner.hasNextLine()) {
            linie = scanner.nextLine();
            //System.out.println(linie);
            String[] elementeLinie = linie.split("\t");//Several non-whitespace characters(//s+)
            if (elementeLinie.length != 5) {
                //exceptie
                System.out.println("Fisier de intrare cu argumente gresite");
            }

            setareDate(elementeLinie);
        }


    }

    /**
     * Metoda care seteaza informatiile
     * @param information primeste string-ul ce contine fiecare linie din fisierul de IN
     */
    private static void setareDate(String[] information) {
            int idNou = Integer.parseInt(information[0].trim());
            String numeNou = information[1];
            float latNou = Float.parseFloat(information[2].trim());
            float longNou = Float.parseFloat(information[3].trim());
            String codTara = information[4];


            Locatie locatieNoua = new Locatie(idNou, numeNou, codTara, longNou, latNou);
            listaLocatiiIntrare.add(locatieNoua);
    }
}
