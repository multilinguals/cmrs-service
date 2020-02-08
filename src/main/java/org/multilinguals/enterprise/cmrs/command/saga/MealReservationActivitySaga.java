package org.multilinguals.enterprise.cmrs.command.saga;

import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.annotation.DeadlineHandler;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.command.CloseMealReservationActivityCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event.MealReservationActivityCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.Calendar;

// @Saga
public class MealReservationActivitySaga extends AbstractSaga {
    private DeadlineManager deadlineManager;
    private MealReservationActivityId activityId;
    private String mrTimeOutManagerId = null;

    @Autowired
    public MealReservationActivitySaga(DeadlineManager deadlineManager) {
        this.deadlineManager = deadlineManager;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(MealReservationActivityCreatedEvent event) {
        Calendar clndOverTime = Calendar.getInstance();
        clndOverTime.add(Calendar.HOUR, 8);
        long timeOutDuration = clndOverTime.getTimeInMillis();

        // 强制在开始8个小时后结束
        this.mrTimeOutManagerId = deadlineManager.schedule(Duration.ofMillis(timeOutDuration), "mealReservationActivityTimeOut", event.getId());
    }

//    @EndSaga
//    @SagaEventHandler(associationProperty = "id")
//    public void handle(MealReservationActivityStartedEvent event) {
//        long endAfterTime = this.endAt.getTime() - new Date().getTime();
//        // 创建结束的
//        deadlineManager.schedule(Duration.ofMillis(endAfterTime), "mealReservationActivityTimeOut");
//    }

    @DeadlineHandler(deadlineName = "mealReservationActivityTimeOut")
    public void on(MealReservationActivityId id) {
        this.commandGateway.send(new CloseMealReservationActivityCommand(id));
    }
}
