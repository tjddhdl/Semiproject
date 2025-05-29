package com.example.demo.cart.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCart is a Querydsl query type for Cart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCart extends EntityPathBase<Cart> {

    private static final long serialVersionUID = 969118743L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCart cart = new QCart("cart");

    public final NumberPath<Integer> cartNo = createNumber("cartNo", Integer.class);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final com.example.demo.member.entity.QMember memberNo;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final com.example.demo.title.entity.QTitle tNo;

    public QCart(String variable) {
        this(Cart.class, forVariable(variable), INITS);
    }

    public QCart(Path<? extends Cart> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCart(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCart(PathMetadata metadata, PathInits inits) {
        this(Cart.class, metadata, inits);
    }

    public QCart(Class<? extends Cart> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberNo = inits.isInitialized("memberNo") ? new com.example.demo.member.entity.QMember(forProperty("memberNo")) : null;
        this.tNo = inits.isInitialized("tNo") ? new com.example.demo.title.entity.QTitle(forProperty("tNo")) : null;
    }

}

