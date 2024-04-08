package project.keyappsk.domain.store.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Projection;
import project.keyappsk.domain.product.dto.ProductMyStoreDto;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.product.entity.QProduct;
import project.keyappsk.domain.store.entity.QStore;

import java.util.List;

@RequiredArgsConstructor
public class StoreCustomRepositoryImpl implements  StoreCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductMyStoreDto> findByIdFetch(Integer storeId) {

        return null;
    }
}
