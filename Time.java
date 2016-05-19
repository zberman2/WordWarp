import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 * This class utilizes a timer object to count down from a specified time.
 */
public class Time {
    Timer timer;
    double sec;
    int count;
    long startTime;
    /**
     * Creates a time object with a specified length of time in seconds
     * 
     * @param int seconds is the time limit for the timer
     */
    public Time(int seconds) {
        timer = new Timer();
        sec = (double) seconds;
        timer.schedule(new EndTask(), seconds*1000);
        startTime = System.nanoTime();
    }

    /**
     * Returns the number of seconds that have past 
     * since the beginning of the Timer
     * @return (int) elapsed seconds
     */
    public double getElapsedTime(){
        return (System.nanoTime() - startTime) / 1000000000.0;
    }

    public void pause(){
        double newSeconds = sec - getElapsedTime();
        stop();
        sec = newSeconds;
        String paused = "\fGame Paused. ";
        String secs = ""; String mins = ""; String and = "";
        int remainingSec = (int)sec % 60;
        int min = (int)sec / 60;
        if (remainingSec == 1)
            secs += remainingSec + " second ";
        else if (remainingSec > 1)
            secs += remainingSec + " seconds ";
        if (min == 1)
            mins += min + " minute ";
        else if (min > 1)
            mins += min + " minutes ";
        if (remainingSec > 0 && min > 0)
            and += "& ";
        paused += mins; paused += and; paused += secs;
        paused += "left. \nHit <Enter> to resume";
        System.out.println(paused);
    }
    /*
    public String getPauseMessage(){
        double secondsLeft = sec - getElapsedTime();
        String paused = "\fGame Paused. ";
        String secs = ""; String mins = ""; String and = "";
        int remainingSec = (int)secondsLeft % 60;
        int min = (int)secondsLeft / 60;
        if (remainingSec == 1)
            secs += remainingSec + " second ";
        else if (remainingSec > 1)
            secs += remainingSec + " seconds ";
        if (min == 1)
            mins += min + " minute ";
        else if (min > 1)
            mins += min + " minutes ";
        if (remainingSec > 0 && min > 0)
            and += "& ";
        paused += mins; paused += and; paused += secs;
        paused += "left. \nHit <Enter> to resume";
        return paused;
    }
    */
    public String getTimeLeftMessage(){
        double secondsLeft = sec - getElapsedTime();
        String secs = ""; String mins = ""; String and = "";
        int remainingSec = (int)secondsLeft % 60;
        int min = (int)secondsLeft / 60;
        if (remainingSec == 1)
            secs += remainingSec + " second ";
        else if (remainingSec > 1)
            secs += remainingSec + " seconds ";
        if (min == 1)
            mins += min + " minute ";
        else if (min > 1)
            mins += min + " minutes ";
        if (remainingSec > 0 && min > 0)
            and += "& ";
        String timeLeft = mins + and + secs + "left";
        return timeLeft;
    }
    

    public void resume(){
        timer = new Timer();
        timer.schedule(new EndTask(), (int)sec * 1000);
        startTime = System.nanoTime();
    }

    /**
     * Sets the second value to 0 and cancels 
     * the current timer
     */
    public void stop(){sec = 0; timer.cancel();}

    /**
     * Accessor method that returns the value for seconds.
     * @return seconds */
    public double getSeconds(){return sec;}
    /**
     * Class that performs an action when the timer is at 0
     */
    class EndTask extends TimerTask {
        /**
         * Clears the terminal window and prompts the user for input
         */
        public void run() {
            System.out.print("\f");
            System.out.format("Time's up!\nHit <Enter> to see the answers\n");
            stop();
        }
    }

    /**
     * Class that performs an action in between start and finish of the timer.
     */
    class TimeLeftTask extends TimerTask {
        /**
         * Gives out the current time left. Was not implemented due to time constraints.
         */
        public void run() {
            int count = 0;
            System.out.print("\f");
            System.out.format(sec - count + " seconds left.\n");
            count++;
        }
    }

    public static void main(String args[]) {
        new Time(60);
        System.out.format("Start.%n");
    }
}

