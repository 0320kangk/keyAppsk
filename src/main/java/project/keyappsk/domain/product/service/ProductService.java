package project.keyappsk.domain.product.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.keyappsk.domain.category.entity.Category;
import project.keyappsk.domain.category.repository.CategoryRepository;
import project.keyappsk.domain.product.dto.ProductAddFormDto;
import project.keyappsk.domain.product.dto.ProductMyStoreDto;
import project.keyappsk.domain.product.dto.ProductUpdateFormDto;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.product.entity.ProductImage;
import project.keyappsk.domain.product.entity.enumerate.ProductStatus;
import project.keyappsk.domain.product.repository.ProductImageRepository;
import project.keyappsk.domain.product.repository.ProductRepository;

import project.keyappsk.domain.store.repository.StoreRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;


    @Value("${productImgFile.dir}")
    private String imgFileDir;

    @Transactional
    public Integer findStoreId(Integer productId){
        Product product = productRepository.findById(productId).get();
        return product.getStore().getId();
    }

    @Transactional
    public void updateProduct(ProductUpdateFormDto productUpdateFormDto, Integer productId) throws IOException {
        Product originProduct = productRepository.findById(productId).orElseThrow(()-> new IllegalArgumentException());
        originProduct.setName(productUpdateFormDto.getName());
        originProduct.setCount(productUpdateFormDto.getCount());
        originProduct.setPrice(productUpdateFormDto.getPrice());
        originProduct.setDescription(productUpdateFormDto.getDescription());
        originProduct.setProductStatus(productUpdateFormDto.getStatus() ? ProductStatus.SALE : ProductStatus.DEADLINE);
        if(!productUpdateFormDto.getImage().isEmpty()){
            ProductImage productImage = filesImgSave(productUpdateFormDto.getImage());
            ProductImage originProductImage = originProduct.getProductImage();
            originProductImage.setUploadFileName(productImage.getUploadFileName());
            originProductImage.setStoreFileName(productImage.getStoreFileName());
            log.info("productImge :id {}", productImage.getId());
            productImageRepository.save(originProductImage);
        }
        productRepository.save(originProduct);
    }

    @Transactional
    public List<ProductMyStoreDto> getProductMyStoreDto(Integer storeId){
        return productRepository.findByStoreId(storeId);
    }

    @Transactional
    public void storeAddFormDtoSave(ProductAddFormDto productAddFormDto, Integer categoryId ) throws IOException {

        Category category = categoryRepository.findById(categoryId).get();
        Product product = addFormDtoToProduct(productAddFormDto, category);
        ProductImage productImage = filesImgSave(productAddFormDto.getImage());
        product.setProductImage(productImage);
        Product saveProduct = productRepository.save(product);
        productImage.setProduct(saveProduct);
        log.info("storeFilename: {} ",productImage.getStoreFileName());
        productImageRepository.save(productImage);

    }
    private Product addFormDtoToProduct(ProductAddFormDto productAddFormDto, Category category){
        Product product = Product.builder()
                .name(productAddFormDto.getName())
                .price(productAddFormDto.getPrice())
                .count(productAddFormDto.getCount())
                .description(productAddFormDto.getDescription())
                .productStatus(ProductStatus.DEADLINE)
                .store(category.getStore())
                .category(category)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        return product;
    }

    private ProductImage filesImgSave(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) {
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createSaveImgName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        ProductImage productImage = ProductImage.builder()
                .uploadFileName(originalFilename)
                .storeFileName(storeFileName)
                .build();

        return productImage;

    }
    public String getFullPath(String filename) {
        return imgFileDir + filename;
    }

    private String createSaveImgName(String originalImgName){
        String ext = extractExt(originalImgName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "."+  ext;
    }
    private String extractExt(String originalFilename){
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}
