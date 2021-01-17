package ro.mta.se.lab.Controller;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class InterogareServerTest {
   JSONObject verificare;

    @Before
    public void setUp() throws Exception {

        System.out.println("Testare creare Json de la server");
    }

    @Test
    public void obtinereDateSelectie() throws IOException {
        InterogareServer obiectDeTest=new InterogareServer();
        assertEquals(obiectDeTest.obtinereDateSelectie("da","nu"),null);
        assertEquals(obiectDeTest.obtinereDateSelectie("da","nu"),obiectDeTest.obtinereDateSelectie("da","nu"));
        //avem 2 interogari la server (timpi diferiti.. testam ca nu se returneaza acelasi obiect)
        assertNotEquals(obiectDeTest.obtinereDateSelectie("de","Germany"),obiectDeTest.obtinereDateSelectie("de","germany"));
        assertNotEquals(obiectDeTest.obtinereDateSelectie("de","Germany"),null);

        InterogareServer auxiliar=new InterogareServer();
        verificare=auxiliar.obtinereDateSelectie("ro","bucharest");
        assertEquals(verificare.length(),13);
    }

    @Test
    public void obtinereDateZile() throws IOException {
        InterogareServer obiectDeTest=new InterogareServer();
        assertEquals(obiectDeTest.obtinereDateZile("da","nu"),null);
        assertEquals(obiectDeTest.obtinereDateZile("da","nu"),obiectDeTest.obtinereDateZile("da","nu"));
        assertNotEquals(obiectDeTest.obtinereDateZile("52.520008","52.520008"),obiectDeTest.obtinereDateZile("52.520008","52.520008"));
    }
}