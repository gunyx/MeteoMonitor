package ro.mta.se.lab.Controller;

import org.json.JSONObject;
import ro.mta.se.lab.Model.Locatie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MeteoMonitorController {

    /**
     * Acest parametru seteaza tabela cu orase si tari afisate utilizatorilor
     */
    HashMap<String,List<String>>infoAfisare = new HashMap<String, List<String>>();


    public void functie1(String inFile) throws IOException { // o sa fie redenumita

        List<Locatie> auxiliar=new ArrayList<>();
        auxiliar=ParserFile.getInstance(inFile).getListaLocatiiIntrare();
        organizarePeTari(auxiliar);//hashmap-ul il folosesc sa-l afisez la ScrollBar/cautare
        /*
        aici legatura cu interfata
        primeste cod_tara+oras
         */
        String oras="Bucharest";
        String cod="ro";
        HashMap<String,String>informatiiZona=new HashMap<>();
        InterogareServer nou=new InterogareServer();
        JSONObject dateCautare=nou.obtinereDateSelectie(cod,oras);
        informatiiZona=(new JsonService()).parsareJson(dateCautare);

    }

    private void organizarePeTari(List<Locatie>primit)
    {
        for (Locatie step : primit) {
            if (infoAfisare.containsKey(step.getCodTara())) {

                infoAfisare.get(step.getCodTara()).add(step.getNumeOras());

            } else {

                infoAfisare.putIfAbsent(step.getCodTara(),null);
                infoAfisare.get(step.getCodTara()).add(step.getNumeOras());
            }

        }

    }
}
