
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * class DualOutputStream can be used to print data to
 * both the console and file using FileWriter object
 * @author Aditi Datta
 */
public class DualOutputStream {

    PrintStream out1;
    PrintWriter out2;

    /**
     * Constructor
     * @param out1 PrintStream object - a reference to System.out
     * @param out2 PrintWriter object
     */
    public DualOutputStream(PrintStream out1, PrintWriter out2){
        this.out1 = out1;
        this.out2 = out2;
    }

    /**
     * prints to console (System.out.print()) and
     * to File (using FileWriter object's print())
     * @param msg message to be printed
     */
    public void print(String msg){
        out1.print(msg);
        out2.print(msg);
    }

    /**
     * prints to console (System.out.println()) and
     * to File (using FileWriter object's println()) with a
     * next line
     * @param msg message to be printed
     */
    public void println(String msg){
        out1.println(msg);
        out2.println(msg);
    }

    /**
     * prints to console (System.out.print()) and
     * to File (using FileWriter object's print())
     * @param msg int message to be printed
     */
    public void print(int msg){
        out1.print(msg);
        out2.print(msg);
    }

    /**
     * prints to console (System.out.println()) and
     * to File (using FileWriter object's println()) with a
     * next line
     * @param msg int message to be printed
     */
    public void println(int msg){
        out1.println(msg);
        out2.println(msg);
    }

    /**
     * prints to console (System.out.println()) and
     * to File (using FileWriter object's println()) with a
     * next line
     */
    public void println(){
        out1.println();
        out2.println();
    }
}
