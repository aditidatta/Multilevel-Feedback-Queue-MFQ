import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class implements a Multi Feedback Queue Scheduling System
 * @author Aditi Datta
 */
public class MFQ {
    private static int[] cpuQuantClock= {2,4,8,16};
    private PrintWriter pw;
    private ObjectQueue jobsQueue;
    private ObjectQueue[] fourQueues;
    private CPU cpu;
    private int sysClock;
    private int numJobs;
    private int totalJobTime;
    private int totalIdleTime;
    private int resTime;
    private int waitTime;
    private DualOutputStream outStream;

    /**
     * Creates an object of MFQ class
     * @param pw PrintWriter object
     */
    public MFQ(PrintWriter pw) {
        this.pw       = pw;
        jobsQueue     = new ObjectQueue();
        fourQueues    = new ObjectQueue[4];
        fourQueues[0] = new ObjectQueue();
        fourQueues[1] = new ObjectQueue();
        fourQueues[2] = new ObjectQueue();
        fourQueues[3] = new ObjectQueue();
        cpu           = new CPU(2);
        sysClock      = 0;
        outStream = new DualOutputStream(System.out, pw);
    }

    /**
     * Read all the jobs from the input file, and stores them 
     * internally
     */
    public void getJobs() {
        try {
            Scanner sc = new Scanner(new File("mfq.txt"));
            String line;
            while(sc.hasNextLine()){
                line = sc.nextLine();
                String[] tokens = line.trim().split("\\s+");
                Job job = new Job(Integer.parseInt(tokens[1]),
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[2]));
                jobsQueue.insert(job);

            }
        }catch(FileNotFoundException ex){
            outStream.println("Input File Not Found! Exiting...");
            System.exit(1);
        }

    }

    /**
     * Prints out the header for the output
     */
    public void outputHeader(){
        outStream.println("                        \t\tCPU     \tTotal    "+
                "\tLowest");
        outStream.println("       \t\tSystem           \tTime    \tTime in  "+
                "\tLevel");
        outStream.println("Event  \t\tTime   \t\tPID   \tNeeded  \tSystem   "+
                "\tQueue");
        outStream.println();
    }

    private void printArrivalMsg(Job job, int sysClk){
        outStream.println("Arrival\t\t"+sysClk+"\t\t\t"+job.getPid()+
                            "\t\t"+job.getCpuTimeRequired());
    }

    private void printDepartMsg(Job job, int sysClk){
        outStream.println("Departure\t"+sysClk+"\t\t\t"+job.getPid()+
                "\t\t\t\t\t"+(sysClk-job.getArrivalTime())+"\t\t\t"+
                (job.getCurrentQueue()+1));
        totalJobTime += sysClk-job.getArrivalTime();
    }

    /**
     * This method runs the whole simulation
     */
    public void runSimulation(){

        while(true) {
            sysClock++; // Tick clock

            // Submit new job to Queue 1.
            if(!jobsQueue.isEmpty()) {
                Job nextJob = (Job) jobsQueue.query();
                if (nextJob.getArrivalTime() == sysClock) {
                    fourQueues[0].insert(nextJob);
                    jobsQueue.remove();
                    printArrivalMsg(nextJob, sysClock);
                    numJobs++;
                }
            }
            // Check if CPU busy
            if(cpu.isBusy()){
                // Decrement quantum clock and
                // Decrement job clock
                cpu.getJob().decrementCpuTimeRemaining();
                cpu.decrementClock();

                if(cpu.getJob().getCpuTimeRemaining()==0){
                    // Take job off CPU and output message
                    // Reset busy flag
                    printDepartMsg(cpu.getJob(), sysClock);
                    cpu.setJob(null);
                    cpu.setBusyFlag(false);
                }
                else if(cpu.getCpuQuantumClock()==0){
                    // PREEMPTION
                    // Remove job from CPU and send to next Queue
                    // Reset busy flag
                    int currentQueue = cpu.getJob().getCurrentQueue();
                    if(currentQueue != 3) {
                        fourQueues[currentQueue + 1].insert(cpu.getJob());
                        cpu.getJob().setCurrentQueue(currentQueue+1);
                    }
                    else{
                        fourQueues[currentQueue].insert(cpu.getJob());
                    }
                    cpu.getJob().setLastPreemption(sysClock);
                    cpu.setJob(null);
                    cpu.setBusyFlag(false);
                }
            }
            if(!cpu.isBusy()){
                // Submit appropriate job to CPU from Queue 1-2-3-4
                // Set quantum clock and busy flag

                if(!fourQueues[0].isEmpty()){
                    Job nextJob = (Job)fourQueues[0].remove();
                    cpu.setJob(nextJob);
                    cpu.setCpuQuantumClock(cpuQuantClock[0]);
                    cpu.setBusyFlag(true);
                    resTime += sysClock - nextJob.getArrivalTime();
                    waitTime += sysClock- nextJob.getLastPreemption();
                }
                else if(!fourQueues[1].isEmpty()){
                    Job nextJob = (Job)fourQueues[1].remove();
                    cpu.setJob(nextJob);
                    cpu.setCpuQuantumClock(cpuQuantClock[1]);
                    cpu.setBusyFlag(true);
                    waitTime += sysClock- nextJob.getLastPreemption();
                }
                else if(!fourQueues[2].isEmpty()){
                    Job nextJob = (Job)fourQueues[2].remove();
                    cpu.setJob(nextJob);
                    cpu.setCpuQuantumClock(cpuQuantClock[2]);
                    cpu.setBusyFlag(true);
                    waitTime += sysClock- nextJob.getLastPreemption();
                }
                else if(!fourQueues[3].isEmpty()){
                    Job nextJob = (Job)fourQueues[3].remove();
                    cpu.setJob(nextJob);
                    cpu.setCpuQuantumClock(cpuQuantClock[3]);
                    cpu.setBusyFlag(true);
                    waitTime += sysClock- nextJob.getLastPreemption();
                }
                else if(jobsQueue.isEmpty()){
                    break;
                }
                else{
                    totalIdleTime++;
                }
            }
        }
    }

    /**
     * Prints out the System Statistics
     */
    public void outStats(){
        DecimalFormat dec = new DecimalFormat("#.##");
        outStream.println();
        outStream.println("System Stats:...............................");
        outStream.println();
        outStream.println("Total number of Jobs: "+ numJobs);
        outStream.println("Total time of all jobs in the system: " +
                totalJobTime);
        outStream.println("Average response time: " +
                dec.format((double)resTime/numJobs));
        outStream.println("Average turnaround time for the jobs: " +
                dec.format((double)totalJobTime/numJobs));
        outStream.println("Average waiting time: "+
                dec.format((double)waitTime/numJobs));
        outStream.println("Average throughput for the system as a whole: " +
                dec.format((double)numJobs/totalJobTime));
        outStream.println("Total CPU idle time: " + totalIdleTime);
    }
}
