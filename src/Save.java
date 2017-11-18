import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by henne on 17.11.2017.
 */
public class Save extends Thread {
    private final String FILEPATH = "out\\";
    private final String FILENAME = "Results";
    private final String FILEENDING = ".txt";

    private String toBeSaved;

    public Save(String toBeSaved){
        this.toBeSaved = toBeSaved;
    }

    @Override
    public void run(){
        String filename = FILEPATH+FILENAME+"_"+LocalDateTime.now().getDayOfMonth()+"_"+LocalDateTime.now().getMonthValue()+FILEENDING;
        File f  = new File(filename);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter out = new FileWriter(f, true);
            System.out.println("Saved: \n"+toBeSaved);
            out.append(toBeSaved);
            out.close();
        }catch (IOException e){

        }

    }
}
