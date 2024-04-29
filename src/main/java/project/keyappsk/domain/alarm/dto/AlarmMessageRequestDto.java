package project.keyappsk.domain.alarm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.orders.entity.enumerate.OrdersStatus;

@Getter
@Setter
@Builder
@ToString
public class AlarmMessageRequestDto {
    Member receiver;
    String Sender;
    String storeName;
    OrdersStatus ordersStatus;

    /**
     *
     * @param receiver: receiver
     *
     * @param: ordersStatus
     * @return
     */
    public static AlarmMessageRequestDto create(Member receiver,String sender ,String storeName, OrdersStatus ordersStatus) {
        return AlarmMessageRequestDto.builder()
                .receiver(receiver)
                .Sender(sender)
                .storeName(storeName)
                .ordersStatus(ordersStatus)
                .build();
    }

}
