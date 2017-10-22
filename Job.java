/**
 * Implements a CPU Job.
 * @author Aditi Datta
 */
public class Job {
    private int pid;
    private int arrivalTime;
    private int cpuTimeRequired;
    private int cpuTimeRemaining;
    private int currentQueue;
    private int lastPreemption;

    /**
     * Creates an instance of Job class
     * @param pid int- PID of the Job
     * @param at  int- arrival time of the Job
     * @param ctr int- total cpu time required for the job
     */
    public Job(int pid, int at, int ctr){
        this.pid         = pid;
        arrivalTime      = at;
        cpuTimeRequired  = ctr;
        cpuTimeRemaining = ctr;
        currentQueue     = 0;
        lastPreemption   = at;
    }

    /**
     * sets the job PID to the input PID
     * @param pid int - PID of the Job
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * sets the arrival time to the input Arrival time
     * @param arrivalTime int- the arrival time of the job
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Sets the CPU time required field to the input time
     * @param cpuTimeRequired int-total cpu time required for the job
     */
    public void setCpuTimeRequired(int cpuTimeRequired) {
        this.cpuTimeRequired = cpuTimeRequired;
    }

    /**
     * Decrements the CPU Time remaining with each clock tick
     */
    public void decrementCpuTimeRemaining() {
        cpuTimeRemaining--;
    }

    /**
     * sets the currentQueue field to the current queue
     * @param currentQueue int-current queue number
     */
    public void setCurrentQueue(int currentQueue) {
        this.currentQueue = currentQueue;
    }

    /**
     * Returns the Job PID
     * @return int
     */
    public int getPid(){
        return pid;
    }

    /**
     * returns the Arrival Time of the job
     * @return int
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * returns the CPU time required
     * @return int
     */
    public int getCpuTimeRequired() {
        return cpuTimeRequired;
    }

    /**
     * returns the CPU time remaining
     * @return int
     */
    public int getCpuTimeRemaining() {
        return cpuTimeRemaining;
    }

    /**
     * Returns the current queue number
     * @return int
     */
    public int getCurrentQueue() {
        return currentQueue;
    }

    /**
     * returns the time of the last preemption
     * @return int
     */
    public int getLastPreemption() {
        return lastPreemption;
    }

    /**
     * Set the time of last preemption to the input time
     * @param lastPreemption int-time of last preemption
     */
    public void setLastPreemption(int lastPreemption) {
        this.lastPreemption = lastPreemption;
    }

    /**
     * returns the Job object as a string
     * @return String
     */
    @Override
    public String toString() {
        return "Job{" +
                "pid=" + pid +
                ", arrivalTime=" + arrivalTime +
                ", cpuTimeRequired=" + cpuTimeRequired +
                '}';
    }
}
