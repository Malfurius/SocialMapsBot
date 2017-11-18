import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by henne on 15.11.2017.
 */
public class main {

    public static void main(String[] args){
        String[] living = {"Garching bei München, 85748"};
        String[] working = {"Ostbahnhof, 81667 München", "Pasing Bf., 81241 München"};
        Interval morning = new Interval(new Time(6,0),new Time(10,0));
        Interval evening = new Interval(new Time(11,0), new Time(20,0));
        Runner sim = new Runner(morning,evening,1, living,working);
        try {
            sim.run(new Date(117,10,15,16,56,00));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
