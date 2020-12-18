package scheduling.processes;

/**
 * @author Arkadiusz Maruszczak
 * @deprecated This class can be used if processes contains priorities
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
   *
   * @param id
   * @param burstTime
   * @param priority
   */
  public PriorityProcess(int id, double burstTime, int priority) {

    setId(id);
    setBurstTime(burstTime);
    setPriotity(priority);
  }
  /**
   * The constructor.
   *
   * @param id
   * @param priority
   * @param burstTime
   * @param awaitTime
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
