package com.StockSync.sourav.StockSync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSearchResultDTO {
    private Long transactionId;
    private String description;
    private String status;
    private Long productId;
    private String name;
    private String sku;
}
