package com.StockSync.sourav.StockSync.controller;

import com.StockSync.sourav.StockSync.dto.ProductDTO;
import com.StockSync.sourav.StockSync.dto.ProductDTOWithImage;
import com.StockSync.sourav.StockSync.dto.Response;
import com.StockSync.sourav.StockSync.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/all")
    public ResponseEntity<Response> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        byte[] imageData = productService.getProductImageById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }



    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Response> saveProduct(
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("name") String name,
            @RequestParam("sku") String sku,
            @RequestParam("price") BigDecimal price,
            @RequestParam("stockQuantity") Integer stockQuantity,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam(value = "description", required = false) String description
    ) {
        if (imageFile.isEmpty()) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("Image file is required")
                    .build());
        }

        ProductDTOWithImage productDTOToSave = new ProductDTOWithImage();
        productDTOToSave.setName(name);
        productDTOToSave.setImageName(name + "_image");
        productDTOToSave.setImageType(imageFile.getContentType());
        productDTOToSave.setSku(sku);
        productDTOToSave.setPrice(price);
        productDTOToSave.setStockQuantity(stockQuantity);
        productDTOToSave.setCategoryId(categoryId);
        productDTOToSave.setDescription(description);

        log.info("Saving product: {}", productDTOToSave);

        try {
            return ResponseEntity.ok(productService.saveProduct(productDTOToSave, imageFile));
        } catch (IOException e) {
            log.error("Error saving product", e); // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Response.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("Failed to save product due to internal error.")
                            .build()
            );
        }

    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Response> updateProduct(

    @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
    @RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "sku", required = false) String sku,
    @RequestParam(value = "price", required = false) BigDecimal price,
    @RequestParam(value = "stockQuantity", required = false) Integer stockQuantity,
    @RequestParam(value = "productId",required = true) Long  productId,
    @RequestParam(value = "categoryId", required = false) Long categoryId,
    @RequestParam(value = "description", required = false) String description
    ) {
        if (imageFile.isEmpty()) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("Image file is required")
                    .build());
        }

        ProductDTOWithImage productDTOToSave = new ProductDTOWithImage();
        productDTOToSave.setName(name);
        productDTOToSave.setImageName(name + "_image");
        productDTOToSave.setImageType(imageFile.getContentType());
        productDTOToSave.setSku(sku);
        productDTOToSave.setPrice(price);
        productDTOToSave.setStockQuantity(stockQuantity);
        productDTOToSave.setCategoryId(categoryId);
        productDTOToSave.setProductId(productId);
        productDTOToSave.setDescription(description);

        log.info("Update product: {}", productDTOToSave);

        try {
            return ResponseEntity.ok(productService.updateProduct(productDTOToSave, imageFile));
        } catch (IOException e) {
            log.error("Error saving product", e); // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Response.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("Failed to save product due to internal error.")
                            .build()
            );
        }

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
