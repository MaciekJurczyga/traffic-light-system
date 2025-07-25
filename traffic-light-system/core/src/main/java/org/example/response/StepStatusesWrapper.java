package org.example.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Class on which the output file is build on
 * Wraps outcome of every step of simulation
 */
public class StepStatusesWrapper {

  private final List<StepStatus> stepStatuses;


  private StepStatusesWrapper(List<StepStatus> statuses) {
    stepStatuses = new ArrayList<>(statuses);
  }

  public List<StepStatus> getStepStatuses() {
    return stepStatuses;
  }

  public static StepStatusesWrapper from(List<StepStatus> statuses) {
    return new StepStatusesWrapper(statuses);
  }
}
