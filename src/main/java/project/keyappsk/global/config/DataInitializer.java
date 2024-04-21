package project.keyappsk.global.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import project.keyappsk.domain.cart.entity.Cart;
import project.keyappsk.domain.cart.repository.CartRepository;
import project.keyappsk.domain.category.entity.Category;
import project.keyappsk.domain.category.repository.CategoryRepository;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.member.entity.enumerate.Role;
import project.keyappsk.domain.member.entity.enumerate.SignType;
import project.keyappsk.domain.member.repository.MemberRepository;
import project.keyappsk.domain.orders.service.OrderService;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.product.entity.ProductImage;
import project.keyappsk.domain.product.entity.enumerate.ProductStatus;
import project.keyappsk.domain.product.repository.ProductImageRepository;
import project.keyappsk.domain.product.repository.ProductRepository;
import project.keyappsk.domain.store.entity.Store;
import project.keyappsk.domain.store.entity.StoreImage;
import project.keyappsk.domain.store.entity.enumerate.StoreStatus;
import project.keyappsk.domain.store.repository.StoreImageRepository;
import project.keyappsk.domain.store.repository.StoreRepository;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer  {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final StoreRepository storeRepository;
    private final StoreImageRepository storeImageRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CartRepository cartRepository;
    private final OrderService orderService;
    private final String password = "1234";

    @Value("${storeImgFile.dir}")
    private String storeImgFileDir;

    @Value("${productImgFile.dir}")
    private String productImgFileDir;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void dataInit() throws IOException {
/*        memberInit();
        storeInit();
        categoryInit();
        productInit();
        cartInit();*/
//        orderInit();
    }
    @Transactional
    public void memberInit(){
        Member member = Member.builder()
                .email("testUser1@test")
                .name("testUser1")
                .role(Role.GUEST)
                .signType(SignType.ORIGIN)
                .password(passwordEncoder.encode(password))
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        memberRepository.save(member);

        Member member2 = Member.builder()
                .email("testUser2@test")
                .name("testUser2")
                .role(Role.GUEST)
                .signType(SignType.ORIGIN)
                .password(passwordEncoder.encode(password))
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        memberRepository.save(member2);
    }

    @Transactional
    public void storeInit() throws IOException {
        Member member = memberRepository.findByEmail("testUser1@test").orElseThrow(() -> new IllegalArgumentException());
        String filePath = "C:\\Users\\82103\\Desktop\\과일\\banana1.jpg";
        for (int i = 0; i < 130; i++) {
            StoreImage image = createStoreImage(filePath);
            Store store = Store.builder()
                    .member(member)
                    .name("우리 가게 최고" + "_" + i)
                    .detailAddress("testAddress")
                    .roadAddress("오전로 179")
                    .jibunAddress("오전동")
                    .extraAddress("추가 주소")
                    .detailAddress("백합 아파트")
                    .storeStatus(StoreStatus.OPEN)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();
            image.setStore(store);
            storeRepository.save(store);
            storeImageRepository.save(image);
        }

    }
    @Transactional
    public void categoryInit() {
        Store store = storeRepository.findByName("우리 가게 최고_1").orElseThrow(() -> new IllegalArgumentException());
        Category category = Category.builder()
                .store(store)
                .name("과자")
                .build();
        categoryRepository.save(category);
    }
    @Transactional
    public void productInit() throws IOException {
        Store store = storeRepository.findByName("우리 가게 최고_1").orElseThrow(() -> new IllegalArgumentException());
        Category category = categoryRepository.findByName("과자").orElseThrow(() -> new IllegalArgumentException());
        String filePath = "C:\\Users\\82103\\Desktop\\과일\\pear1.jpg";
        for (int i = 0; i < 130; i++) {
            ProductImage productImage = createProductImage(filePath);
            Product product = Product.builder()
                    .store(store)
                    .productImage(productImage)
                    .name("새우깡_" + i)
                    .category(category)
                    .count(3)
                    .price(1000)
                    .description("새우 깡 입니다.")
                    .productStatus(ProductStatus.SALE)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();
            productImage.setProduct(product);
            productRepository.save(product);
            productImageRepository.save(productImage);
        }

    }
    @Transactional
    public ProductImage createProductImage(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        int pos = filePath.lastIndexOf(".");
        String ext= filePath.substring(pos);
        String uuid = UUID.randomUUID().toString();
        String storeName = uuid + ext;
        String storePath = productImgFileDir + storeName;
        Path destinationPath = Paths.get(storePath);
        Files.copy(fis, destinationPath);
        ProductImage productImage = ProductImage
                .builder()
                .uploadFileName("banana1.jpg")
                .storeFileName(storeName)
                .build();
        return productImage;
    }

    @Transactional
    public StoreImage createStoreImage(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        int pos = filePath.lastIndexOf(".");
        String ext= filePath.substring(pos);
        String uuid = UUID.randomUUID().toString();
        String storeName = uuid + ext;
        String storePath = storeImgFileDir + storeName;
        Path destinationPath = Paths.get(storePath);
        Files.copy(fis, destinationPath);
        StoreImage storeImage = StoreImage
                .builder()
                .uploadFileName("banana1.jpg")
                .storeFileName(storeName)
                .build();
        return storeImage;
    }
    @Transactional
    public void cartInit(){
        Member member = memberRepository.findByEmail("testUser1@test").orElseThrow(() -> new IllegalArgumentException());
        List<Store> stores = storeRepository.findStoreByMemberId(member.getId());
        Product product = productRepository.findByName("새우깡_0").orElseThrow(() -> new IllegalArgumentException());
        for (Store store : stores) {
            Cart cart = Cart
                    .builder()
                    .member(member)
                    .store(store)
                    .product(product)
                    .productCount(3)
                    .build();
            cartRepository.save(cart);
        }
    }
    @Transactional
    public void orderInit(){
        Member buyer = memberRepository.findByEmail("testUser2@test").orElseThrow(() -> new IllegalArgumentException());
        Member seller = memberRepository.findByEmail("testUser1@test").orElseThrow(() -> new IllegalArgumentException());
        List<Store> stores = storeRepository.findStoreByMemberId(seller.getId());
        for (Store store : stores) {
            log.info("orderInit test");

            orderService.createOrder(buyer,store.getId());

        }
    }

}
