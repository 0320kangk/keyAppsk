package project.keyappsk.domain.orders.entity.enumerate;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrdersStatus {
    //대기, 준비,완료, 취소
    WAITING("WAITING", "주문 대기"),
    PREPARATION("PREPARATION", "주문 준비"),
    COMPLETE("COMPLETE", "주문 완료"),
    CANCEL("CANCEL","주문 취소");

    private final String key;
    private final String title;
}
