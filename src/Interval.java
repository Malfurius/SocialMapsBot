import java.util.Date;

/**
 * Created by henne on 15.11.2017.
 */
public class Interval {
    private Time from;
    private Time to;
    public Interval(Time from, Time to){
        this.from = from;
        this.to = to;
    }

    public boolean contains(int d){
        return (d>=from.getTimeInSec() && (d-10)<=to.getTimeInSec());
    }
}
