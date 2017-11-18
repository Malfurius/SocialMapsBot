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
        System.out.println(d);
        System.out.println(from.getTimeInMin()+"|"+to.getTimeInMin());
        return (d>=from.getTimeInMin() && (d-10)<=to.getTimeInMin());
    }
}
