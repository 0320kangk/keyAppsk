package project.keyappsk.domain.store.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = 73217934L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore store = new QStore("store");

    public final ListPath<project.keyappsk.domain.cart.entity.Cart, project.keyappsk.domain.cart.entity.QCart> carts = this.<project.keyappsk.domain.cart.entity.Cart, project.keyappsk.domain.cart.entity.QCart>createList("carts", project.keyappsk.domain.cart.entity.Cart.class, project.keyappsk.domain.cart.entity.QCart.class, PathInits.DIRECT2);

    public final ListPath<project.keyappsk.domain.category.entity.Category, project.keyappsk.domain.category.entity.QCategory> categories = this.<project.keyappsk.domain.category.entity.Category, project.keyappsk.domain.category.entity.QCategory>createList("categories", project.keyappsk.domain.category.entity.Category.class, project.keyappsk.domain.category.entity.QCategory.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final StringPath detailAddress = createString("detailAddress");

    public final StringPath extraAddress = createString("extraAddress");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath jibunAddress = createString("jibunAddress");

    public final project.keyappsk.domain.member.entity.QMember member;

    public final StringPath name = createString("name");

    public final ListPath<project.keyappsk.domain.orders.entity.Order, project.keyappsk.domain.orders.entity.QOrder> orders = this.<project.keyappsk.domain.orders.entity.Order, project.keyappsk.domain.orders.entity.QOrder>createList("orders", project.keyappsk.domain.orders.entity.Order.class, project.keyappsk.domain.orders.entity.QOrder.class, PathInits.DIRECT2);

    public final ListPath<project.keyappsk.domain.product.entity.Product, project.keyappsk.domain.product.entity.QProduct> products = this.<project.keyappsk.domain.product.entity.Product, project.keyappsk.domain.product.entity.QProduct>createList("products", project.keyappsk.domain.product.entity.Product.class, project.keyappsk.domain.product.entity.QProduct.class, PathInits.DIRECT2);

    public final StringPath roadAddress = createString("roadAddress");

    public final QStoreImage storeImage;

    public final EnumPath<project.keyappsk.domain.store.entity.enumerate.StoreStatus> storeStatus = createEnum("storeStatus", project.keyappsk.domain.store.entity.enumerate.StoreStatus.class);

    public final DateTimePath<java.time.LocalDateTime> updatedDate = createDateTime("updatedDate", java.time.LocalDateTime.class);

    public QStore(String variable) {
        this(Store.class, forVariable(variable), INITS);
    }

    public QStore(Path<? extends Store> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStore(PathMetadata metadata, PathInits inits) {
        this(Store.class, metadata, inits);
    }

    public QStore(Class<? extends Store> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new project.keyappsk.domain.member.entity.QMember(forProperty("member")) : null;
        this.storeImage = inits.isInitialized("storeImage") ? new QStoreImage(forProperty("storeImage"), inits.get("storeImage")) : null;
    }

}

