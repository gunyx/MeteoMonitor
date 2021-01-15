package ro.mta.se.lab.Controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.mta.se.lab.Model.Locatie;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParserFileTest {

    ParserFile parserTest;
    ParserFile obiectComparat;
    List<Locatie> listaLocatiiIntrare=new ArrayList<>();
    int val_testata;
    @Before
    public void setUp() throws Exception {
        ParserFile setter=ParserFile.getInstance("in.txt");
        val_testata=setter.getListaLocatiiIntrare().size();
        setter=null;
        System.out.println("TESTAREA SE REALIZEAZA CU 7 LOCATII IN FISIERUL DE INTRARE");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Testare cu succes. Functiile ruleaza corect");
    }

    @Test
    public void getListaLocatiiIntrare() throws FileNotFoundException {

        parserTest=ParserFile.getInstance("in.txt");
        obiectComparat=ParserFile.getInstance();
        listaLocatiiIntrare=obiectComparat.getListaLocatiiIntrare();

        assertEquals(parserTest.getListaLocatiiIntrare().size(),val_testata);
        assertEquals(parserTest.getListaLocatiiIntrare().size(),parserTest.getListaLocatiiIntrare().size());
        assertEquals(parserTest.getListaLocatiiIntrare(),listaLocatiiIntrare);
        assertEquals(parserTest.getListaLocatiiIntrare().get(0).getCodTara(),"RU");
        assertEquals(parserTest.getListaLocatiiIntrare().get(6).getCodTara(),"DE");
        assertEquals(parserTest.getListaLocatiiIntrare().get(6).getNumeOras(),"Germany");
        assertEquals(parserTest.getListaLocatiiIntrare().get(1).getCodTara(),"RU");
        assertEquals(parserTest.getListaLocatiiIntrare().get(1).getLatitudine(),55.752220,0.002);
        assertEquals(parserTest.getListaLocatiiIntrare().get(6).getLatitudine(),52.520008,0.002);

        parserTest=null;
        parserTest=ParserFile.getInstance("inexistent.txt");
        assertEquals(parserTest.getListaLocatiiIntrare(),listaLocatiiIntrare);
        assertEquals(parserTest.getListaLocatiiIntrare().size(),val_testata);

    }

    @Test
    public void getInstance() throws FileNotFoundException {

        assertEquals(parserTest,obiectComparat);

        parserTest=ParserFile.getInstance("in.txt");
        obiectComparat=ParserFile.getInstance();
        assertEquals(parserTest,obiectComparat);

        parserTest=null;
        ParserFile testareSingleton=ParserFile.getInstance("ceva.txt");
        parserTest=ParserFile.getInstance("altceva.txt");
        assertEquals(parserTest,testareSingleton);
        assertEquals(parserTest,obiectComparat);

        parserTest=null;
        parserTest=ParserFile.getInstance();
        assertEquals(parserTest,testareSingleton);
    }
}