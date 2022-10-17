package com.ml.bff.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurcharseInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer total;
    private String offset;
    private String limit;
    private List<PurcharseData> data;
}