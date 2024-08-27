package com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.service.ValidCurrencyCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DealDTO {

    @NotBlank(message = "Deal Id is required")
    private String dealUniqueId;
    @NotBlank(message = "order from currency is required")
    @ValidCurrencyCode(message = "The currency value not valid IOS code")
    private String orderingFromCurrency;
    @NotBlank(message = "order to currency is required")
    @ValidCurrencyCode(message = "The currency value not valid IOS code")
    private String orderingToCurrency;
    @NotNull(message = "deal datetime is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dealDateTime;
    @NotNull(message = "order amount is required")
    private BigDecimal dealAmount;
}
