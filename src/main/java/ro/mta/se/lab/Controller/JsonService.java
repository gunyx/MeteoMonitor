package ro.mta.se.lab.Controller;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author Gunyx
 *
 * Clasa care se ocupa cu parsarea jsoanelor
 */
public class JsonService {

    public JsonService() {
    }
    public HashMap<String,String> parsareJson(JSONObject inputJson)
    {
        HashMap<String,String>tabelaDate=new HashMap<>();
        JSONObject raspunsSv = new JSONObject(inputJson.toString());

        tabelaDate.putIfAbsent("id",raspunsSv.getJSONObject("sys").get("id").toString());
        tabelaDate.putIfAbsent("temp",raspunsSv.getJSONObject("main").get("temp").toString());
        tabelaDate.putIfAbsent("tempMin",raspunsSv.getJSONObject("main").get("temp_min").toString());
        tabelaDate.putIfAbsent("tempMax",raspunsSv.getJSONObject("main").get("temp_max").toString());
        tabelaDate.putIfAbsent("pressure",raspunsSv.getJSONObject("main").get("pressure").toString());
        tabelaDate.putIfAbsent("humidity",raspunsSv.getJSONObject("main").get("humidity").toString());
        tabelaDate.putIfAbsent("wind",raspunsSv.getJSONObject("wind").get("speed").toString());
        tabelaDate.putIfAbsent("clouds",raspunsSv.getJSONObject("clouds").get("all").toString());
        tabelaDate.putIfAbsent("dt",raspunsSv.get("dt").toString());
        tabelaDate.putIfAbsent("dtDiff",raspunsSv.get("timezone").toString());

        return tabelaDate;
    }
}
