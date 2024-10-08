package xyz.sangdam.counseling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -1473331418L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final xyz.sangdam.global.entities.QBaseEntity _super = new xyz.sangdam.global.entities.QBaseEntity(this);

    public final EnumPath<xyz.sangdam.counseling.constants.PersonalCategory> category = createEnum("category", xyz.sangdam.counseling.constants.PersonalCategory.class);

    public final QCounseling counseling;

    public final StringPath counselingName = createString("counselingName");

    public final EnumPath<xyz.sangdam.counseling.constants.CounselingType> counselingType = createEnum("counselingType", xyz.sangdam.counseling.constants.CounselingType.class);

    public final StringPath counselorEmail = createString("counselorEmail");

    public final StringPath counselorName = createString("counselorName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath email = createString("email");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final DateTimePath<java.time.LocalDateTime> rDateTime = createDateTime("rDateTime", java.time.LocalDateTime.class);

    public final StringPath reason = createString("reason");

    public final StringPath record = createString("record");

    public final NumberPath<Long> rNo = createNumber("rNo", Long.class);

    public final EnumPath<xyz.sangdam.counseling.constants.Status> status = createEnum("status", xyz.sangdam.counseling.constants.Status.class);

    public final StringPath userName = createString("userName");

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.counseling = inits.isInitialized("counseling") ? new QCounseling(forProperty("counseling")) : null;
    }

}

