import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by henne on 17.11.2017.
 */
public class ApiResponse {

    String origin;
    String destination;
    int distance;
    int duration;
    int duration_traffic;
    Date date;

    public ApiResponse(String origin, String destination, int distance, int duration, int duration_traffic, Date date){
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.duration_traffic = duration_traffic;
        this.date = date;

    }

    public String toJson(){
       return new Gson().toJson(this);
    }
}
