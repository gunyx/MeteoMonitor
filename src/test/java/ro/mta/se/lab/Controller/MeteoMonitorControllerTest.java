package ro.mta.se.lab.Controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ro.mta.se.lab.Model.Locatie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class MeteoMonitorControllerTest {

    @Mock
    MeteoMonitorController controlerTestat;

    @InjectMocks
    ParserFile obj;

    List<Locatie> listaLocatiiIntrare=new ArrayList<>();
    @Before
    public void setUp() throws Exception {
        Locatie noua=new Locatie(555,"vladivostok","ru",(float)43.10562,(float)131.87353);
        listaLocatiiIntrare.add(noua);
        obj= ParserFile.getInstance("test.txt");
       // obj=mock(ParserFile.class);
       // when(obj.getListaLocatiiIntrare()).thenReturn(listaLocatiiIntrare);

        controlerTestat=mock(MeteoMonitorController.class);

    }

    @Test
    public void ceva() throws IOException {
        controlerTestat=new MeteoMonitorController("in.txt","out.txt");
        controlerTestat.creareDateInterfata("in.txt");
        assertEquals(controlerTestat.dateCautare.size(),1);
        assertEquals(controlerTestat.dateCautare.get(0).getNumeOras(),"vladivostok");
        assertEquals(controlerTestat.dateCautare.get(0).getLatitudine(),52.520008,43.10562);
    }

}