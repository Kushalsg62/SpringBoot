package com.StockSync.sourav.StockSync.service;


import com.StockSync.sourav.StockSync.dto.Response;
import com.StockSync.sourav.StockSync.dto.TransactionRequest;
import com.StockSync.sourav.StockSync.enums.TransactionStatus;

public interface TransactionService {

    Response restockInventory(TransactionRequest transactionRequest);

    Response sell(TransactionRequest transactionRequest);

    Response returnToSupplier(TransactionRequest transactionRequest);

    Response getAllTransactions(int page, int size, String searchText);

    Response getTransactionById(Long id);

    Response getAllTransactionByMonthAndYear(int month, int year);

    Response updateTransactionStatus(Long transactionId, TransactionStatus transactionStatus);

}
