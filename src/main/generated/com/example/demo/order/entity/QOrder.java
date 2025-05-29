package com.example.demo.order.entity;

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

    private static final long serialVersionUID = 1091634429L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> arrivedDate = createDateTime("arrivedDate", java.time.LocalDateTime.class);

    public final com.example.demo.member.entity.QMember memberNo;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> orderDate = _super.orderDate;

    public final NumberPath<Integer> orderNo = createNumber("orderNo", Integer.class);

    public final EnumPath<Status> status = createEnum("status", Status.class);

    public final NumberPath<Integer> totalCount = createNumber("totalCount", Integer.class);

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

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
        this.memberNo = inits.isInitialized("memberNo") ? new com.example.demo.member.entity.QMember(forProperty("memberNo")) : null;
    }

}

