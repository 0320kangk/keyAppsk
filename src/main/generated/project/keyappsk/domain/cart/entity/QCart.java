package project.keyappsk.domain.cart.entity;

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

    private static final long serialVersionUID = 885430280L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCart cart = new QCart("cart");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final project.keyappsk.domain.member.entity.QMember member;

    public final project.keyappsk.domain.product.entity.QProduct product;

    public final NumberPath<Integer> productCount = createNumber("productCount", Integer.class);

    public final project.keyappsk.domain.store.entity.QStore store;

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
        this.member = inits.isInitialized("member") ? new project.keyappsk.domain.member.entity.QMember(forProperty("member")) : null;
        this.product = inits.isInitialized("product") ? new project.keyappsk.domain.product.entity.QProduct(forProperty("product"), inits.get("product")) : null;
        this.store = inits.isInitialized("store") ? new project.keyappsk.domain.store.entity.QStore(forProperty("store"), inits.get("store")) : null;
    }

}

