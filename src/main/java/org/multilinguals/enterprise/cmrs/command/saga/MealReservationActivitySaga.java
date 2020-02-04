package org.multilinguals.enterprise.cmrs.command.saga;

import org.axonframework.deadline.DeadlineManager;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event.MealReservationActivityCreatedEvent;
import org.multilinguals.enterprise.cmrs.constant.aggregate.mrgroup.MealReservationActivityStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

@Saga
public class MealReservationActivitySaga extends AbstractSaga {
    private DeadlineManager deadlineManager;
    private String mrStartManagerId = null;
    private String mrTimeOutManagerId = null;

    @Autowired
    public MealReservationActivitySaga(DeadlineManager deadlineManager) {
        this.deadlineManager = deadlineManager;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(MealReservationActivityCreatedEvent event) {
        // 如果点餐活动是未开始状态，才创建开始的死信
        if(event.getStatus().equals(MealReservationActivityStatus.PENDING)){
            this.mrStartManagerId = deadlineManager.schedule(Duration.ofMillis(500), "mealReservationActivityTimeOut");
        }
        this.mrTimeOutManagerId = deadlineManager.schedule(Duration.ofMillis(500), "mealReservationActivityTimeOut");
    }

//    @SagaEventHandler(associationProperty = "invoiceId")
//    public void handle(InvoicePaidEvent event) {
//        paid = true;
//        if (delivered) {
//            SagaLifecycle.end();
//        }
//    }
}
