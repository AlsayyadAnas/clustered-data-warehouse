package com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDTO {

    private String errorCode;
    private String errorMessage;
}
