package com.example.wagonmanager.dto;

import java.util.List;

public class PagedResponse<T> {
    private List<T> content;
    private PageableInfo pageable;
    private long totalElements;

    public PagedResponse() {};

    public PagedResponse(List<T> content, PageableInfo pageable, long totalElements) {
        this.content = content;
        this.pageable = pageable;
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public PageableInfo getPageable() {
        return pageable;
    }

    public void setPageable(PageableInfo pageable) {
        this.pageable = pageable;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public static class PageableInfo {
        private int page;
        private int size;

        public PageableInfo() {}

        public PageableInfo(int page, int size) {
            this.page = page;
            this.size = size;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
}
