package project.keyappsk.domain.orders.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -1559560575L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final project.keyappsk.domain.member.entity.QMember memberBuyer;

    public final ListPath<project.keyappsk.domain.ordersProduct.entity.OrdersProduct, project.keyappsk.domain.ordersProduct.entity.QOrdersProduct> orderProducts = this.<project.keyappsk.domain.ordersProduct.entity.OrdersProduct, project.keyappsk.domain.ordersProduct.entity.QOrdersProduct>createList("orderProducts", project.keyappsk.domain.ordersProduct.entity.OrdersProduct.class, project.keyappsk.domain.ordersProduct.entity.QOrdersProduct.class, PathInits.DIRECT2);

    public final EnumPath<project.keyappsk.domain.orders.entity.enumerate.OrdersStatus> ordersStatus = createEnum("ordersStatus", project.keyappsk.domain.orders.entity.enumerate.OrdersStatus.class);

    public final DateTimePath<java.time.LocalDateTime> updatedDate = createDateTime("updatedDate", java.time.LocalDateTime.class);

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberBuyer = inits.isInitialized("memberBuyer") ? new project.keyappsk.domain.member.entity.QMember(forProperty("memberBuyer")) : null;
    }

}

