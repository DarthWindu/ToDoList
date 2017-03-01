package frontend;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class Tester {
    public static void main(String[] args) {
    	Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        System.out.println(sdf.format(date));
    }
}