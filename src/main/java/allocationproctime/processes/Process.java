package allocationproctime.processes;

/**
 * @author Arkadiusz Maruszczak
 *
 */
public class Process extends BaseProcess {

  /**
   * The default constructor.
   */
  public Process() {

    setId(1);
    setAwaitingTime(0);
    setBurstTime(1);
    setArrivalTime(0);
    setProcessingTime(0);
  }

  /**
   * The constructor.
   */
  public Process(int id, int burstTime) {

    setId(id);
    setBurstTime(burstTime);
  }
  /**
   * The constructor.
   */
  public Process(int id, int burstTime, int awaitTime) {

    setId(id);
    setBurstTime(burstTime);
    setAwaitingTime(awaitTime);
  }
}
