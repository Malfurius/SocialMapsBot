import java.util.Date;

/**
 * Created by henne on 15.11.2017.
 */
public class Time {

    private int hrs;
    private int sec;

    public Time(int hrs, int sec){
        this.hrs = hrs;
        this.sec = sec;
    }

    public int getTimeInSec(){
        return (hrs*60)+sec;
    }

    public static int getNow(){
        Date now = new Date();
        return (now.getMinutes()*60)+now.getSeconds();
    }
}
