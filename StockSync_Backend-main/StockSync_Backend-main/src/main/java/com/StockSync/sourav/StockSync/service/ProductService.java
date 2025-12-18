package com.StockSync.sourav.StockSync.service;

import com.StockSync.sourav.StockSync.dto.ProductDTO;
import com.StockSync.sourav.StockSync.dto.ProductDTOWithImage;
import com.StockSync.sourav.StockSync.dto.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {

    Response saveProduct(ProductDTOWithImage productDTO, MultipartFile imageFile) throws IOException;

    Response updateProduct(ProductDTOWithImage productDTO,MultipartFile imageFile) throws IOException;

    Response getAllProducts();

    Response getProductById(Long id);

    Response deleteProduct(Long id);

    byte[] getProductImageById(Long id);
}
