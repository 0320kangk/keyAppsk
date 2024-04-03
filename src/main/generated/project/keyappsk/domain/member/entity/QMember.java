package project.keyappsk.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1574108996L;

    public static final QMember member = new QMember("member1");

    public final ListPath<project.keyappsk.domain.alarm.entity.AlarmMessage, project.keyappsk.domain.alarm.entity.QAlarmMessage> alarmMessages = this.<project.keyappsk.domain.alarm.entity.AlarmMessage, project.keyappsk.domain.alarm.entity.QAlarmMessage>createList("alarmMessages", project.keyappsk.domain.alarm.entity.AlarmMessage.class, project.keyappsk.domain.alarm.entity.QAlarmMessage.class, PathInits.DIRECT2);

    public final ListPath<project.keyappsk.domain.cart.entity.Cart, project.keyappsk.domain.cart.entity.QCart> carts = this.<project.keyappsk.domain.cart.entity.Cart, project.keyappsk.domain.cart.entity.QCart>createList("carts", project.keyappsk.domain.cart.entity.Cart.class, project.keyappsk.domain.cart.entity.QCart.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final ListPath<project.keyappsk.domain.orders.entity.Order, project.keyappsk.domain.orders.entity.QOrder> orders = this.<project.keyappsk.domain.orders.entity.Order, project.keyappsk.domain.orders.entity.QOrder>createList("orders", project.keyappsk.domain.orders.entity.Order.class, project.keyappsk.domain.orders.entity.QOrder.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final EnumPath<project.keyappsk.domain.member.entity.enumerate.Role> role = createEnum("role", project.keyappsk.domain.member.entity.enumerate.Role.class);

    public final EnumPath<project.keyappsk.domain.member.entity.enumerate.SignType> signType = createEnum("signType", project.keyappsk.domain.member.entity.enumerate.SignType.class);

    public final ListPath<project.keyappsk.domain.store.entity.Store, project.keyappsk.domain.store.entity.QStore> stores = this.<project.keyappsk.domain.store.entity.Store, project.keyappsk.domain.store.entity.QStore>createList("stores", project.keyappsk.domain.store.entity.Store.class, project.keyappsk.domain.store.entity.QStore.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updatedDate = createDateTime("updatedDate", java.time.LocalDateTime.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

