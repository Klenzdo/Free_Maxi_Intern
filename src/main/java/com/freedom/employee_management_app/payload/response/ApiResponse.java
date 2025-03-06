package com.freedom.employee_management_app.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;


public class ApiResponse<T> {

    private String responseMessage;
    private T responseData;
    private Boolean isFirst;
    private Boolean isLast;
    private Integer page;
    private Integer pageSize;
    private Integer totalElement;

    public ApiResponse(String responseMessage, T responseData) {
        this.responseMessage = responseMessage;
        this.responseData = responseData;
    }

    public ApiResponse(String responseMessage, T responseData, Boolean isFirst, Boolean isLast, Integer page, Integer pageSize, Integer totalElement) {
        this.responseMessage = responseMessage;
        this.responseData = responseData;
        this.isFirst = isFirst;
        this.isLast = isLast;
        this.page = page;
        this.pageSize = pageSize;
        this.totalElement = totalElement;
    }

    public ApiResponse() {
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public Boolean getFirst() {
        return isFirst;
    }

    public void setFirst(Boolean first) {
        isFirst = first;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(Integer totalElement) {
        this.totalElement = totalElement;
    }
}
