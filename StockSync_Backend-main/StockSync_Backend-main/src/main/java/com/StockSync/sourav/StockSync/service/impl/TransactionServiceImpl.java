package com.StockSync.sourav.StockSync.service.impl;

import com.StockSync.sourav.StockSync.dto.Response;
import com.StockSync.sourav.StockSync.dto.SupplierDTO;
import com.StockSync.sourav.StockSync.dto.TransactionDTO;
import com.StockSync.sourav.StockSync.dto.TransactionRequest;
import com.StockSync.sourav.StockSync.entity.Product;
import com.StockSync.sourav.StockSync.entity.Supplier;
import com.StockSync.sourav.StockSync.entity.Transaction;
import com.StockSync.sourav.StockSync.entity.User;
import com.StockSync.sourav.StockSync.enums.TransactionStatus;
import com.StockSync.sourav.StockSync.enums.TransactionType;
import com.StockSync.sourav.StockSync.exception.NameValueRequiredException;
import com.StockSync.sourav.StockSync.exception.NotFoundException;
import com.StockSync.sourav.StockSync.repository.ProductRepository;
import com.StockSync.sourav.StockSync.repository.SupplierRepository;
import com.StockSync.sourav.StockSync.repository.TransactionRepository;
import com.StockSync.sourav.StockSync.service.TransactionService;
import com.StockSync.sourav.StockSync.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;



    @Override
    public Response getAllTransactions(int page, int size, String searchText) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Transaction> transactionPage = transactionRepository.searchTransactionsNative(searchText, pageable);

        transactionPage.forEach(transaction -> System.out.println(transaction.toString()));


        List<Transaction> modifiableTransactionList = new ArrayList<>(transactionPage.getContent());

        List<TransactionDTO> transactionDTOS = modelMapper
                .map(modifiableTransactionList, new TypeToken<List<TransactionDTO>>() {}.getType());



        transactionDTOS.forEach(transactionDTOItem -> {
            transactionDTOItem.setUser(null);
            transactionDTOItem.setProduct(null);
            transactionDTOItem.setSupplier(null);
        });


        return Response.builder()
                .status(200)
                .message("success")
                .transactions(transactionDTOS)
                .build();
    }

    @Override
    public Response restockInventory(TransactionRequest transactionRequest) {
        Long productId = transactionRequest.getProductId();
        Long supplierId = transactionRequest.getSupplierId();
        Integer quantity = transactionRequest.getQuantity();

        if (supplierId == null) throw new NameValueRequiredException("Supplier Id is required");

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product Not Found"));

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(()-> new NotFoundException("Supplier Not Found"));

        User user = userService.getCurrentLoggedInUser();

        //update the stock quantity and re-save
        product.setStockQuantity(product.getStockQuantity() + quantity);
        productRepository.save(product);

        //create a transaction
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.PURCHASE)
                .status(TransactionStatus.PROCESSING) // Ensure this value is valid
                .product(product)
                .user(user)
                .supplier(supplier)
                .totalProducts(quantity)
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .description(transactionRequest.getDescription())
                .build();

        transactionRepository.save(transaction);


        return Response.builder()
                .status(200)
                .message("Transaction Made Successfully")
                .build();

    }

    @Override
    public Response sell(TransactionRequest transactionRequest) {
        Long productId = transactionRequest.getProductId();
        Integer quantity = transactionRequest.getQuantity();

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product Not Found"));


        User user = userService.getCurrentLoggedInUser();

        //update the stock quantity and re-save
        // *** Check and Update it call service method not repo ***
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);

        //create a transaction
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.SALE)
                .status(TransactionStatus.PROCESSING)
                .product(product)
                .user(user)
                .totalProducts(quantity)
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .description(transactionRequest.getDescription())
                .build();

        transactionRepository.save(transaction);


        return Response.builder()
                .status(200)
                .message("Transaction Sold Successfully")
                .build();

    }

    @Override
    public Response returnToSupplier(TransactionRequest transactionRequest) {
        Long productId = transactionRequest.getProductId();
        Long supplierId = transactionRequest.getSupplierId();
        Integer quantity = transactionRequest.getQuantity();

        if (supplierId == null) throw new NameValueRequiredException("Supplier Id is required");

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product Not Found"));

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(()-> new NotFoundException("Supplier Not Found"));

        User user = userService.getCurrentLoggedInUser();

        //update the stock quantity and re-save
        // *** Check and Update it call service method not repo ***
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);

        //create a transaction
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.RETURN_TO_SUPPLIER)
                .status(TransactionStatus.PROCESSING)
                .product(product)
                .user(user)
                .supplier(supplier)
                .totalProducts(quantity)
                .totalPrice(BigDecimal.ZERO)
                .description(transactionRequest.getDescription())
                .build();

        transactionRepository.save(transaction);


        return Response.builder()
                .status(200)
                .message("Transaction Returned Successfully Initialized")
                .build();
    }



    @Override
    public Response getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Transaction Not Found"));

        TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);

        if (transactionDTO.getUser() != null) {
            transactionDTO.getUser().setTransactions(null);
        } //removing the user transaction list

        return Response.builder()
                .status(200)
                .message("success")
                .transaction(transactionDTO)
                .build();

    }

    @Override
    public Response getAllTransactionByMonthAndYear(int month, int year) {
        List<Transaction> transactions = transactionRepository.findAllByMonthAndYear(month, year);

        List<TransactionDTO> transactionDTOS = modelMapper
                .map(transactions, new TypeToken<List<TransactionDTO>>() {}.getType());

        transactionDTOS.forEach(transactionDTOItem -> {
            transactionDTOItem.setUser(null);
            transactionDTOItem.setProduct(null);
            transactionDTOItem.setSupplier(null);
        });

        return Response.builder()
                .status(200)
                .message("success")
                .transactions(transactionDTOS)
                .build();
    }

    @Override
    public Response updateTransactionStatus(Long transactionId, TransactionStatus transactionStatus) {
        Transaction existingTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(()-> new NotFoundException("Transaction Not Found"));

        existingTransaction.setStatus(transactionStatus);
        existingTransaction.setUpdatedAt(LocalDateTime.now());

        transactionRepository.save(existingTransaction);

        return Response.builder()
                .status(200)
                .message("Transaction Status Successfully Updated")
                .build();
    }

    public static TransactionType convertToTransactionType(Object value) {
        if (value instanceof String) {
            try {
                return TransactionType.valueOf((String) value);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid TransactionType: " + value);
            }
        } else if (value instanceof Number) {
            int index = ((Number) value).intValue();
            TransactionType[] values = TransactionType.values();
            if (index >= 0 && index < values.length) {
                return values[index];
            } else {
                throw new RuntimeException("Invalid TransactionType index: " + index);
            }
        }
        throw new RuntimeException("Invalid type for TransactionType: " + value);
    }

    //    @Override
//    public Response getAllTransactions(int page, int size, String searchText) {
//
//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
//        Page<Object[]> transactionPage = transactionRepository.searchTransactions(searchText, pageable);
//
//        Page<Object[]> resultPage = transactionRepository.searchTransactions(searchText, pageable);  // Fetch data from DB
//
//        // Print each row
//        for (Object[] row : resultPage.getContent()) {
//            System.out.println(Arrays.toString(row));  // Print array elements
//        }
//
//
//        // Convert Object[] to TransactionDTO manually
//        List<TransactionDTO> transactionDTOS = transactionPage.getContent().stream().map(obj -> {
//            TransactionDTO dto = new TransactionDTO();
//            dto.setId((Long) obj[0]);
//            dto.setDescription((String) obj[1]);
//
//            dto.setStatus(convertToTransactionStatus(obj[2]));
//            dto.setTransactionType(convertToTransactionType(obj[3]));
//
//            dto.setTotalPrice(new BigDecimal(((Number) obj[4]).toString()));
//
//            if (obj[5] instanceof Number) {
//                dto.setTotalProducts(((Number) obj[5]).intValue());
//            } else {
//                log.error("Unexpected value for totalProducts: " + obj[5]);
//                dto.setTotalProducts(0);
//            }
//
//            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
//            dto.setCreatedAt(obj[6] instanceof String ? LocalDateTime.parse((String) obj[6], formatter) : (LocalDateTime) obj[6]);
//            dto.setUpdatedAt(obj[7] instanceof String ? LocalDateTime.parse((String) obj[7], formatter) : (LocalDateTime) obj[7]);
//
//            // ✅ Fix: Map SupplierDTO properly
//            dto.setSupplier(mapToSupplierDTO(Arrays.copyOfRange(obj, 8, obj.length))); // Extract supplier fields
//
//            return dto;
//        }).collect(Collectors.toList());
//
//
//
//        // Set unnecessary fields to null
//        transactionDTOS.forEach(transactionDTO -> {
//            transactionDTO.setUser(null);
//            transactionDTO.setProduct(null);
//            transactionDTO.setSupplier(null);
//        });
//
//        return Response.builder()
//                .status(200)
//                .message("success")
//                .transactions(transactionDTOS)
//                .build();
//    }

}
