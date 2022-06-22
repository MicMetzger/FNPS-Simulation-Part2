package main.java.com.individuals.task;

public abstract class EmployeeTask {
  private static final String TASKSTATUS_INCOMPLETE = "Incomplete";
  private static final String TASKSTATUS_COMPLETE   = "Complete";
  private static final String TASKSTATUS_ACTIVE     = "Active";
  private static final String TASKSTATUS_CANCELED   = "Canceled";

  private String status;

  private void statusChange(String status) {
    switch (status) {
      case TASKSTATUS_INCOMPLETE  -> status = TASKSTATUS_ACTIVE;
      case TASKSTATUS_ACTIVE      -> status = TASKSTATUS_COMPLETE;
      case TASKSTATUS_COMPLETE    -> status = TASKSTATUS_CANCELED;
      case TASKSTATUS_CANCELED    -> status = TASKSTATUS_INCOMPLETE;
    }
  }
}
