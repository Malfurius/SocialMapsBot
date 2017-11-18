import java.util.Date;

/**
 * Created by henne on 15.11.2017.
 */
public class Time {

    private int hrs;
    private int min;

    public Time(int hrs, int min){
        this.hrs = hrs;
        this.min = min;
    }

    public int getTimeInMin(){
        return (hrs*60)+min;
    }

    public static int getNow(){
        Date now = new Date();
        return (now.getHours()*60)+(now.getMinutes());
    }
}
