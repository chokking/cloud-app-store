package com.webcloud.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PageVo<T> implements Serializable {
    private static final long serialVersionUID = 4348683076964072685L;
    private Long count;
    private T result;
}
