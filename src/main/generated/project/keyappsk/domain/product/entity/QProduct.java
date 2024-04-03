package project.keyappsk.domain.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -1955851314L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final ListPath<project.keyappsk.domain.cart.entity.Cart, project.keyappsk.domain.cart.entity.QCart> carts = this.<project.keyappsk.domain.cart.entity.Cart, project.keyappsk.domain.cart.entity.QCart>createList("carts", project.keyappsk.domain.cart.entity.Cart.class, project.keyappsk.domain.cart.entity.QCart.class, PathInits.DIRECT2);

    public final project.keyappsk.domain.category.entity.QCategory category;

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final ListPath<project.keyappsk.domain.orders.entity.Order, project.keyappsk.domain.orders.entity.QOrder> orders = this.<project.keyappsk.domain.orders.entity.Order, project.keyappsk.domain.orders.entity.QOrder>createList("orders", project.keyappsk.domain.orders.entity.Order.class, project.keyappsk.domain.orders.entity.QOrder.class, PathInits.DIRECT2);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final QProductImage productImage;

    public final EnumPath<project.keyappsk.domain.product.entity.enumerate.ProductStatus> productStatus = createEnum("productStatus", project.keyappsk.domain.product.entity.enumerate.ProductStatus.class);

    public final project.keyappsk.domain.store.entity.QStore store;

    public final DateTimePath<java.time.LocalDateTime> updatedDate = createDateTime("updatedDate", java.time.LocalDateTime.class);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new project.keyappsk.domain.category.entity.QCategory(forProperty("category"), inits.get("category")) : null;
        this.productImage = inits.isInitialized("productImage") ? new QProductImage(forProperty("productImage"), inits.get("productImage")) : null;
        this.store = inits.isInitialized("store") ? new project.keyappsk.domain.store.entity.QStore(forProperty("store"), inits.get("store")) : null;
    }

}

