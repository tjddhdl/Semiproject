package com.example.demo.title.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTitle is a Querydsl query type for Title
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTitle extends EntityPathBase<Title> {

    private static final long serialVersionUID = 1477363005L;

    public static final QTitle title = new QTitle("title");

    public final NumberPath<Integer> ageRate = createNumber("ageRate", Integer.class);

    public final EnumPath<Category> category = createEnum("category", Category.class);

    public final StringPath image = createString("image");

    public final EnumPath<ModelName> model = createEnum("model", ModelName.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DatePath<java.time.LocalDate> releaseDate = createDate("releaseDate", java.time.LocalDate.class);

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public final StringPath titleName = createString("titleName");

    public final NumberPath<Integer> tNo = createNumber("tNo", Integer.class);

    public QTitle(String variable) {
        super(Title.class, forVariable(variable));
    }

    public QTitle(Path<? extends Title> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTitle(PathMetadata metadata) {
        super(Title.class, metadata);
    }

}

