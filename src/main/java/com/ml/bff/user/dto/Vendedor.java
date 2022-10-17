package com.ml.bff.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vendedor implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nickname;
}