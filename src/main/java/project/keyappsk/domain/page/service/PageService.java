package project.keyappsk.domain.page.service;

import org.springframework.stereotype.Service;
import project.keyappsk.domain.page.dto.PageDto;

@Service
public class PageService {
    //115-> 111
    public PageDto get10unitPageInit(int presentPage, int totalPage) {
        int startPage = presentPage - (presentPage % 10) + 1;
        int endPage = Math.min(startPage + 9, totalPage);
        PageDto pageDto = PageDto.builder()
                .startPage(startPage)
                .endPage(endPage)
                .presentPage(presentPage + 1)
                .totalPage(totalPage)
                .build();
        return pageDto;
    }

}
