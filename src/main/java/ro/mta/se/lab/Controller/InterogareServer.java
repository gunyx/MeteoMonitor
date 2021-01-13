package ro.mta.se.lab.Controller;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author  Gunyx
 * Clasa ce se ocupa cu trimiterea request-ului la server pentru obtinerea JSON-ului de date
 *
 * */
public class InterogareServer {

    public InterogareServer() {
    }

    /**
     * Metoda de interogare a serverului. Prin aceasta metoda se trimite request-ul si se asteapta raspunsul care are codul 200(succes)
     * @param codTara reques-ul de la utilizator catre server se face prin selecarea codului de tara
     * @param selectieOras trimiterea orasului pentru care se doreste obtinerea vremii
     * @return este intors obiectul JSON care urmeaza sa fie parsat in Controller-ul de vreme
     * @throws IOException exceptie pentru url inexistent
     */
    public JSONObject obtinereDateSelectie(String codTara, String selectieOras) throws IOException {

        String inputLine=null;
        String url="http://api.openweathermap.org/data/2.5/weather?q="+selectieOras+","+codTara+"&APPID=c89ff4c798a13d0c8e93203d06e31fd9&units=metric";

        URL obj = new URL(url);
        HttpURLConnection conexiune = (HttpURLConnection) obj.openConnection();
        conexiune.setRequestMethod("GET");
        conexiune.setRequestProperty("User-Agent", "Mozilla/5.0");// se pot modifica proprietatile

        int responseCode = conexiune.getResponseCode();//verificare cod 200 =Succes
        if(responseCode!=200)
        {
            //exceptie;
        }

        BufferedReader dataRecv = new BufferedReader(new InputStreamReader(conexiune.getInputStream()));
        StringBuffer response = new StringBuffer();

        while ((inputLine = dataRecv.readLine()) != null) {
            response.append(inputLine);
        }
        dataRecv.close();

        //System.out.println(response.toString());
        JSONObject obiectObtinut = new JSONObject(response.toString());

        return obiectObtinut;
    }
}
