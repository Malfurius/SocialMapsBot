import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by henne on 15.11.2017.
 */
public class Runner {

    private Interval morning;
    private Interval evening;
    private String[] livingLoc;
    private String[] workingLoc;
    private int minutesBeetweenRequests;

    public Runner(Interval morning, Interval evening,int minutesBeetweenRequests, String[] livingLoc, String[] workingLoc){
        this.morning = morning;
        this.evening = evening;
        this.livingLoc = livingLoc;
        this.workingLoc = workingLoc;
        this.minutesBeetweenRequests = minutesBeetweenRequests;
    }

    public void run(Date start) throws InterruptedException {
        Date now;
        do{
            now = new Date();
            System.out.println(now.toString());
            System.out.println(start.toString());
            Thread.sleep(10000);
        }while (now.getTime()<start.getTime());
        while(true){
            now = new Date();
            if(morning.contains(Time.getNow())){
                for(int i = 0; i < livingLoc.length;i++){
                    for(int j = 0; j < workingLoc.length;j++){
                        sendRequest(livingLoc[i], workingLoc[j]);
                    }
                }
            }
            if(evening.contains(Time.getNow())){
                for(int i = 0; i < workingLoc.length;i++){
                    for(int j = 0; j < livingLoc.length;j++){
                        sendRequest(workingLoc[i], livingLoc[j]);
                    }
                }
            }
            Thread.sleep(minutesBeetweenRequests*60000);
        }
    }

    private void sendRequest(String org, String dest){
        try {
            MapsAPI.getInstance().sendRequest(org, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
