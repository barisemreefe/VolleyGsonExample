package barisemre.volleygsonexample;

/**
 * Created by barisemre on 24/06/2014.
 */
public class MyResponse {
    private String time ;
    private String date ;
    private double milliseconds_since_epoch ;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMilliseconds_since_epoch() {
        return milliseconds_since_epoch;
    }

    public void setMilliseconds_since_epoch(double milliseconds_since_epoch) {
        this.milliseconds_since_epoch = milliseconds_since_epoch;
    }
}
