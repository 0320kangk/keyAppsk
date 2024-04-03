package project.keyappsk.domain.alarm.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlarmMessage is a Querydsl query type for AlarmMessage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlarmMessage extends EntityPathBase<AlarmMessage> {

    private static final long serialVersionUID = 1085460121L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlarmMessage alarmMessage = new QAlarmMessage("alarmMessage");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final project.keyappsk.domain.member.entity.QMember member;

    public final StringPath message = createString("message");

    public final EnumPath<project.keyappsk.domain.alarm.entity.enumerate.ReadStatus> readStatus = createEnum("readStatus", project.keyappsk.domain.alarm.entity.enumerate.ReadStatus.class);

    public QAlarmMessage(String variable) {
        this(AlarmMessage.class, forVariable(variable), INITS);
    }

    public QAlarmMessage(Path<? extends AlarmMessage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlarmMessage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlarmMessage(PathMetadata metadata, PathInits inits) {
        this(AlarmMessage.class, metadata, inits);
    }

    public QAlarmMessage(Class<? extends AlarmMessage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new project.keyappsk.domain.member.entity.QMember(forProperty("member")) : null;
    }

}

