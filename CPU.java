/**
 * This class takes a job and processes it, based on its quantum clock.
 * @author Aditi Datta
 */
public class CPU {

    private Job job;
    private int cpuQuantumClock;
    private boolean busyFlag;

    /**
     * Default constructor, sets the instance variables to their
     * initial values
     */
    public CPU() {
    }

    /**
     * Creates an object of CPU class with provided cpu quantum time
     * @param cpuQuantumClock CPU Quantum Time for the particular operation
     */
    public CPU(int cpuQuantumClock) {
        this.job = null;
        this.cpuQuantumClock = cpuQuantumClock;
        this.busyFlag = false;
    }

    /**
     * Decrements the quantum clock by 1
     */
    public void decrementClock(){
        cpuQuantumClock--;
    }

    /**
     * Returns the current job at the cpu
     * @return Job job
     */
    public Job getJob() {
        return job;
    }

    /**
     * Takes a job and sets it to the current job
     * @param job Current CPU bound Job object
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * returns cpu quantum clock
     * @return int CPU quantum for the current job
     */
    public int getCpuQuantumClock() {
        return cpuQuantumClock;
    }

    /**
     * sets cpu quantum clock
     * @param cpuQuantumClock CPU Quantum Time for the particular operation
     */
    public void setCpuQuantumClock(int cpuQuantumClock) {
        this.cpuQuantumClock = cpuQuantumClock;
    }

    /**
     * Checks if CPU is busy or not
     * @return boolean
     */
    public boolean isBusy() {
        return busyFlag;
    }

    /**
     * Sets the busy flag to the input flag (true or false)
     * @param busyFlag true or false
     */
    public void setBusyFlag(boolean busyFlag) {
        this.busyFlag = busyFlag;
    }

    /**
     * Returns the object as a string
     * @return String
     */
    @Override
    public String toString() {
        return "CPU{" +
                "job=" + job +
                ", QuantumClock=" + cpuQuantumClock +
                ", busyFlag=" + busyFlag +
                '}';
    }
}
