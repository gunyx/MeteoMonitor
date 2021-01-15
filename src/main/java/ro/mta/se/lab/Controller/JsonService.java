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
    public HashMap<String,String> parsareJson(JSONObject inputJson) {
        HashMap<String,String>tabelaDate=new HashMap<>();
        JSONObject raspunsSv = new JSONObject(inputJson.toString());

        tabelaDate.putIfAbsent("id",raspunsSv.getJSONObject("sys").get("id").toString());
        tabelaDate.putIfAbsent("temp",raspunsSv.getJSONObject("main").get("temp").toString());
        tabelaDate.putIfAbsent("tempMin",raspunsSv.getJSONObject("main").get("temp_min").toString());
        tabelaDate.putIfAbsent("tempMax",raspunsSv.getJSONObject("main").get("temp_max").toString());
        tabelaDate.putIfAbsent("pressure",raspunsSv.getJSONObject("main").get("pressure").toString());
        tabelaDate.putIfAbsent("description",raspunsSv.getJSONArray("weather").getJSONObject(0).getString("description"));
        tabelaDate.putIfAbsent("icon",raspunsSv.getJSONArray("weather").getJSONObject(0).getString("icon"));
        tabelaDate.putIfAbsent("humidity",raspunsSv.getJSONObject("main").get("humidity").toString());
        tabelaDate.putIfAbsent("wind",raspunsSv.getJSONObject("wind").get("speed").toString());
        tabelaDate.putIfAbsent("clouds",raspunsSv.getJSONObject("clouds").get("all").toString());
        tabelaDate.putIfAbsent("dt",raspunsSv.get("dt").toString());
        tabelaDate.putIfAbsent("dtDiff",raspunsSv.get("timezone").toString());

        return tabelaDate;
    }
    public HashMap<String,String> parsareJsonDays(JSONObject inputJson) {
        HashMap<String,String>tabelaDate=new HashMap<>();
        JSONObject raspunsSv = new JSONObject(inputJson.toString());

        tabelaDate.putIfAbsent("id",raspunsSv.getJSONArray("daily").getJSONObject(1).getJSONArray("weather").getJSONObject(0).get("id").toString());
        tabelaDate.putIfAbsent("temp",raspunsSv.getJSONArray("daily").getJSONObject(1).getJSONObject("temp").get("day").toString());
        tabelaDate.putIfAbsent("tempMin",raspunsSv.getJSONArray("daily").getJSONObject(1).getJSONObject("temp").get("min").toString());
        tabelaDate.putIfAbsent("tempMax",raspunsSv.getJSONArray("daily").getJSONObject(1).getJSONObject("temp").get("max").toString());
        tabelaDate.putIfAbsent("pressure",raspunsSv.getJSONArray("daily").getJSONObject(1).get("pressure").toString());
        tabelaDate.putIfAbsent("description",raspunsSv.getJSONArray("daily").getJSONObject(1).getJSONArray("weather").getJSONObject(0).get("description").toString());
        tabelaDate.putIfAbsent("icon",raspunsSv.getJSONArray("daily").getJSONObject(1).getJSONArray("weather").getJSONObject(0).get("icon").toString());
        tabelaDate.putIfAbsent("humidity",raspunsSv.getJSONArray("daily").getJSONObject(1).get("humidity").toString());
        tabelaDate.putIfAbsent("wind",raspunsSv.getJSONArray("daily").getJSONObject(1).get("wind_speed").toString());
        tabelaDate.putIfAbsent("clouds",raspunsSv.getJSONArray("daily").getJSONObject(1).get("clouds").toString());
        tabelaDate.putIfAbsent("dt",raspunsSv.getJSONArray("daily").getJSONObject(1).get("dt").toString());
        tabelaDate.putIfAbsent("dtDiff",raspunsSv.get("timezone_offset").toString());
        System.out.println(raspunsSv.getJSONArray("daily").getJSONObject(0).getJSONObject("temp").get("day").toString());

        return tabelaDate;
    }
}
