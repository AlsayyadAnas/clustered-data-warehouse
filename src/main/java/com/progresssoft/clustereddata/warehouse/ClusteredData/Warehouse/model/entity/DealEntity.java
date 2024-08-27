package com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "deals")
public class DealEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String dealUniqueId;
    private String orderingFromCurrency;
    private String orderingToCurrency;
    private LocalDateTime dealDateTime;
    private BigDecimal dealAmount;

}
