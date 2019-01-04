package allocationproctime.processes;

/**
 * @author Arkadiusz Maruszczak
 *
 */
public class PriorityProcess extends BaseProcess {

  private int priority;
  /**
   * The default constructor.
   */
  public PriorityProcess() {

    setId(1);
    setAwaitingTime(1);
    setBurstTime(1);
    setArrivalTime(1);
    setProcessingTime(1);
    setPriotity(1);
  }


  /**
   * The constructor.
   */
  public PriorityProcess(int id, double burstTime, int priority) {

    setId(id);
    setBurstTime(burstTime);
    setPriotity(priority);
  }
  /**
   * The constructor.
   */
  public PriorityProcess(int id, double burstTime, int priority, double awaitTime) {

    setId(id);
    setBurstTime(burstTime);
    setPriotity(priority);
    setAwaitingTime(awaitTime);
  }

  private void setPriotity(int priority) {
    this.priority=priority;
  }

}
