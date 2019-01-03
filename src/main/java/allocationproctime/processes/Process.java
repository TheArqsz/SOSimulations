package allocationproctime.processes;

/**
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
    setBurstTime(1);
    setArrivalTime(0);
    setProcessingTime(0);
  }

  /**
   * The constructor.
   * @param id value of id to be set to specific process
   * @param burstTime value of burst time to be set to specific process
   */
  public Process(int id, int burstTime) {

    setId(id);
    setBurstTime(burstTime);
  }
  /**
   * The constructor.
   * @param id value of id to be set to specific process
   * @param burstTime value of burst time to be set to specific process
   * @param awaitTime value of waiting time to be set to specific process
   */
  public Process(int id, int burstTime, int awaitTime) {

    setId(id);
    setBurstTime(burstTime);
    setAwaitingTime(awaitTime);
  }
}
