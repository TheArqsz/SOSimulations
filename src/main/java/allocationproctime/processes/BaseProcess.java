package allocationproctime.processes;

/**
 * @author amaruszc
 *
 */
public abstract class BaseProcess {

  protected int id;

  protected int arrivalTime = 0;

  protected int burstTime = 0;

  protected int awaitingTime = 0;

  protected int processingTime = 0;

  /**
   * @return the id of the process
   */
  public Integer getId() {

    return this.id;
  }

  /**
   * @return the value of awaiting time of the process
   */
  public Integer getAwaitingTime() {

    return this.awaitingTime;
  }

  /**
   * @return the value of burst time of the process
   */
  public Integer getBurstTime() {

    return this.burstTime;
  }

  /**
   * @return the value of processing time of the process
   */
  public Integer getProcessingTime() {

    return this.processingTime;
  }

  /**
   * @return the value of arrival time of the process
   */
  public Integer getArrivalTime() {

    return this.arrivalTime;
  }

  /**
   * <p>Sets id for the process</p>
   * @param id value of id to be set for the process
   */
  public void setId(int id) {

    this.id = id;
  }

  /**
   * <p>Sets awaiting time for the process</p>
   * @param awaitingTime value of awaiting time to be set for the process
   */
  public void setAwaitingTime(int awaitingTime) {

    this.awaitingTime = awaitingTime;
  }

  /**
   * <p>Sets processing time for the process</p>
   * @param processingTime value of processing time to be set for the process
   */
  public void setProcessingTime(int processingTime) {

    this.processingTime = processingTime;
  }

  /**
   * <p>Sets time of arrival for the process</p>
   * @param arrivalTime value of arrival time to be set for the process
   */
  public void setArrivalTime(int arrivalTime) {

    this.arrivalTime = arrivalTime;
  }

  /**
   * <p>Sets time of burst for the process</p>
   * @param burstTime value of burst time to be set for the process
   */
  public void setBurstTime(int burstTime) {

    this.burstTime = burstTime;
  }

}
