package project.keyappsk.domain.store.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.keyappsk.domain.category.service.CategoryService;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.member.repository.MemberRepository;
import project.keyappsk.domain.store.dto.MemberStoreDto;
import project.keyappsk.domain.store.dto.StoreAddFormDto;
import project.keyappsk.domain.store.dto.StoreSearchDto;
import project.keyappsk.domain.store.dto.StoreUpdateFormDto;
import project.keyappsk.domain.store.entity.Store;
import project.keyappsk.domain.store.entity.StoreImage;
import project.keyappsk.domain.store.entity.enumerate.StoreStatus;
import project.keyappsk.domain.store.repository.StoreImageRepository;
import project.keyappsk.domain.store.repository.StoreRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final StoreImageRepository storeImageRepository;

    @Value("${storeImgFile.dir}")
    private String imgFileDir;

    @Transactional
    public Page<StoreSearchDto> searchStorePagination (String query, Pageable pageable){
        return storeRepository.findByRoadAddressContainingOrJibunAddressContaining(query, pageable);
    }

    @Transactional
    public void updateStore(Integer storeId, StoreUpdateFormDto storeUpdateFormDto) throws IOException {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException());
        store.setName(storeUpdateFormDto.getName());
        store.setStoreStatus(storeUpdateFormDto.getStatus() ? StoreStatus.OPEN : StoreStatus.CLOSE);
        store.setRoadAddress(storeUpdateFormDto.getRoadAddress());
        store.setJibunAddress(storeUpdateFormDto.getJibunAddress());
        store.setDetailAddress(storeUpdateFormDto.getDetailAddress());
        store.setExtraAddress(storeUpdateFormDto.getExtraAddress());
        store.setUpdatedDate(LocalDateTime.now());
        log.info("storeUpdateFormDto.getImage() {}", storeUpdateFormDto.getImage().toString());
        if(!storeUpdateFormDto.getImage().isEmpty() ){
            StoreImage storeImage = filesImgSave(storeUpdateFormDto.getImage());
            StoreImage originImage = store.getStoreImage();
            originImage.setStoreFileName(storeImage.getStoreFileName());
            originImage.setUploadFileName(storeImage.getUploadFileName());
            storeImageRepository.save(originImage);
        }
        storeRepository.save(store);
    }

    @Transactional
    public StoreUpdateFormDto storeIdToStoreUpdateFormDto (Integer storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException());
        return StoreUpdateFormDto.builder()
                .name(store.getName())
                .status(store.getStoreStatus() == StoreStatus.OPEN)
                .roadAddress(store.getRoadAddress())
                .jibunAddress(store.getJibunAddress())
                .detailAddress(store.getDetailAddress())
                .extraAddress(store.getExtraAddress())
                .build();
    }

    @Transactional
    public void storeAddFormDtoSave(StoreAddFormDto storeAddFormDto, Member member) throws IOException {
        Store store = addFormDtoToStore(storeAddFormDto, member);
        StoreImage storeImage = filesImgSave(storeAddFormDto.getMultipartFile());
        store.setStoreImage(storeImage);
        Store saveStore = storeRepository.save(store);
        storeImage.storeChange(saveStore);
        storeImageRepository.save(storeImage);
    }
    private StoreImage filesImgSave(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) {
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createSaveImgName(originalFilename);

        multipartFile.transferTo(new File(getFullPath(storeFileName)));
       /* File file = new File(getFullPath(storeFileName));
        Thumbnails.of(file)
                .size(500, 350)
                .toFile(file);*/
        StoreImage storeImage = StoreImage.builder()
                .uploadFileName(originalFilename)
                .storeFileName(storeFileName)
                .build();

        return storeImage;

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
    private Store addFormDtoToStore(
            StoreAddFormDto storeAddFormDto,
            Member member){
        Store newStore = Store.builder()
                .member(member)
                .name(storeAddFormDto.getName())
                .storeStatus(StoreStatus.CLOSE)
                .roadAddress(storeAddFormDto.getRoadAddress())
                .jibunAddress(storeAddFormDto.getJibunAddress())
                .detailAddress(storeAddFormDto.getDetailAddress())
                .extraAddress(storeAddFormDto.getExtraAddress())
                .updatedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
        return newStore;
    }
    @Transactional
    public Page<MemberStoreDto> getStores(Member member, Pageable pageable) {
        return  memberRepository.getMemberStoreDto(member.getId(), pageable) ;
    }



}
