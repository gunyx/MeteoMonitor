package ro.mta.se.lab.Controller;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class JsonServiceTest {
    InterogareServer auxiliar=new InterogareServer();
    JSONObject testat;
    JSONObject testatZile;
    JsonService serviciuTestat=new JsonService();
    JsonService comparator=new JsonService();
    @Before
    public void setUp() throws Exception {
        testat=auxiliar.obtinereDateSelectie("ro","bucharest");
        testatZile=auxiliar.obtinereDateZile("52.520008","52.520008");
    }

    @Test
    public void parsareJson() {
        assertNotEquals(testat,testatZile);
        assertEquals(serviciuTestat.parsareJson(testat),comparator.parsareJson(testat));
        assertEquals(serviciuTestat.parsareJson(testat).size(),comparator.parsareJson(testat).size());
        assertEquals(serviciuTestat.parsareJson(testat).get("name"),"Bucharest");
        assertNull(serviciuTestat.parsareJson(null));
        assertEquals(serviciuTestat.parsareJson(testat).get("lat"),"44.4323");
        assertEquals(serviciuTestat.parsareJson(testat).get("lon"),"26.1063");
        assertNotNull((serviciuTestat.parsareJson(testat).get("temp")));
        assertNotNull((serviciuTestat.parsareJson(testat).get("description")));
        assertNotNull((serviciuTestat.parsareJson(testat).get("icon")));
    }
}