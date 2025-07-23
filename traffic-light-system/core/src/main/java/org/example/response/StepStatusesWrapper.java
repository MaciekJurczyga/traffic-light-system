package org.example.response;

import java.util.ArrayList;
import java.util.List;

public class StepStatusesWrapper {

    private final List<StepStatus> stepStatuses;

    public List<StepStatus> getStepStatuses() {
        return new ArrayList<>(stepStatuses);
    }
    private StepStatusesWrapper(List<StepStatus> statuses){
        stepStatuses = new ArrayList<>(statuses);
    }

    public static StepStatusesWrapper from(List<StepStatus> statuses){
       return new StepStatusesWrapper(statuses);
    }
}