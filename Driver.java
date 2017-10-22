import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Driver class for the MFQ project
 */
public class Driver {
    public static void main(String[] args) throws IOException{
        PrintWriter pw = new PrintWriter(new FileWriter("csis.txt"));
        MFQ mfq = new MFQ(pw);
        mfq.getJobs();
        mfq.outputHeader();
        mfq.runSimulation();
        mfq.outStats();
        pw.close();
    }
}
