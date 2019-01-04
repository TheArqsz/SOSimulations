package allocationproctime.processes;

/**
 * <p>Base abstract class for all processes</p>
 * @author Arkadiusz Maruszczak
 *
 */
public abstract class BaseProcess {

  /**
   * variable that stores id of process
   */
  protected int id;

  /**
   * variable that stores arrival time of process
   */
  protected int arrivalTime = 0;

  /**
   * variable that stores burst time of process
   */
  protected double burstTime = 0;

  /**
   * variable that stores waiting time of process
   */
  protected double awaitingTime = 0;

  /**
   * variable that stores processing time of process
   */
  protected double processingTime = 0;

  /**
   * Gets id of process
   * @return the id of the process
   */
  public Integer getId() {

    return this.id;
  }

  /**
   * Gets waiting time
   * @return the value of awaiting time of the process
   */
  public Double getAwaitingTime() {

    return this.awaitingTime;
  }

  /**
   * <p>Gets burst time of process</p>
   * @return the value of burst time of the process
   */
  public Double getBurstTime() {

    return this.burstTime;
  }

  /**
   * <p>Gets processing time of process</p>
   * @return the value of processing time of the process
   */
  public Double getProcessingTime() {

    return this.processingTime;
  }

  /**
   * <p>Gets arrival time of process</p>
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
  public void setAwaitingTime(double awaitingTime) {

    this.awaitingTime = awaitingTime;
  }

  /**
   * <p>Sets processing time for the process</p>
   * @param processingTime value of processing time to be set for the process
   */
  public void setProcessingTime(double processingTime) {

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
  public void setBurstTime(double burstTime) {

    this.burstTime = burstTime;
  }

}
