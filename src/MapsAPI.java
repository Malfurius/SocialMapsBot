import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.time.Duration;
import java.util.Date;

/**
 * Created by henne on 15.11.2017.
 */
public class MapsAPI {
    private final String APIKEY = "AIzaSyCel-b3dECpNu28LesVNtWbGsArSRvYXSg";
    private final String URLBEG = "https://maps.googleapis.com/maps/api/directions/json?";
    private final Date DAYZERO = new Date(1970, 1, 1, 0, 0, 0);

    private static MapsAPI instance = null;

    public static MapsAPI getInstance(){
        if(instance == null){
            instance = new MapsAPI();
        }
        return instance;
    }

    public ApiResponse sendRequest(String org, String dest) throws IOException {
        String url = URLBEG;
        url += "origin=" + formatLocation(org)+"&";
        url += "destination=" + formatLocation(dest) + "&";
        url += "departure_time=" + "now"+ "&";
        url += "traffic_model=best_guess&";
        url += "key=" + APIKEY;

        URL request = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) request.openConnection();
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(url);
        wr.close();

        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while((line = rd.readLine()) != null){
            response.append(line);
            response.append("\n");
        }
        rd.close();
        //System.out.println(response.toString());
        return  parseResponse(response.toString(), org, dest);
    }

    private ApiResponse parseResponse(String response, String org, String dest){
        Date now = new Date();
        String nowStr = now.toString();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        try {
            int dur = Integer.parseInt(jsonObject.getAsJsonArray("routes").get(0).getAsJsonObject().getAsJsonArray("legs").get(0).getAsJsonObject().getAsJsonObject("duration").get("value").getAsString());
            int durTraf = Integer.parseInt(jsonObject.getAsJsonArray("routes").get(0).getAsJsonObject().getAsJsonArray("legs").get(0).getAsJsonObject().getAsJsonObject("duration_in_traffic").get("value").getAsString());
            int distance = Integer.parseInt(jsonObject.getAsJsonArray("routes").get(0).getAsJsonObject().getAsJsonArray("legs").get(0).getAsJsonObject().getAsJsonObject("distance").get("value").getAsString());
            ApiResponse apiResponse = new ApiResponse(org,dest,distance, dur, durTraf, now );
            return apiResponse;
        }catch (Exception e){
            return new ApiResponse(org, dest, -1, -1, -1, now);
        }
    }

    private String formatLocation(String loc)
    {
        return loc.replace(' ', '+');
    }

    private int formatDate(Date date)
    {
       return (int)(date.getTime()-DAYZERO.getTime());
    }
}
