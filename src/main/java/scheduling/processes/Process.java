package scheduling.processes;

/**
 * <p>Class that describes process</p>
 * @author Arkadiusz Maruszczak
 *
 */
public class Process extends BaseProcess {

  /**
   * The default constructor. Everything is set to 0
   */
  public Process() {

    setId(0);
    setAwaitingTime(0);
    setBurstTime(0);
    setArrivalTime(0);
    setProcessingTime(0);
  }

  /**
   * The constructor with burst time to set.
   * @param id value of id to be set to specific process
   * @param burstTime value of burst time to be set to specific process
   */
  public Process(int id, double burstTime) {

    setId(id);
    setBurstTime(burstTime);
  }
  /**
   * The constructor with burst time and waiting time to set.
   * @param id value of id to be set to specific process
   * @param burstTime value of burst time to be set to specific process
   * @param awaitTime value of waiting time to be set to specific process
   */
  public Process(int id, double burstTime, double awaitTime) {

    setId(id);
    setBurstTime(burstTime);
    setAwaitingTime(awaitTime);
  }
}
