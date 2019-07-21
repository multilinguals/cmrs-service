package org.multilinguals.enterprise.cmrs.infrastructure.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class CMRSPage<P> {
    private List<P> content;

    private PageInfo pageInfo;

    class PageInfo {
        private long totalElements;

        private int totalPages;

        private int pageNumber;

        private int numberOfElements;

        PageInfo(long totalElements, int totalPages, int pageNumber, int numberOfElements) {
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.pageNumber = pageNumber;
            this.numberOfElements = numberOfElements;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }
    }

    public CMRSPage(Page<P> page) {
        this.content = page.getContent();
        this.pageInfo = new PageInfo(page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getNumberOfElements());
    }

    public List<P> getContent() {
        return content;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }
}
