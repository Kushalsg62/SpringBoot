package com.StockSync.sourav.StockSync.service;

import com.StockSync.sourav.StockSync.dto.CategoryDTO;
import com.StockSync.sourav.StockSync.dto.Response;
import com.StockSync.sourav.StockSync.dto.SupplierDTO;

public interface SupplierService {

    Response addSupplier(SupplierDTO supplierDTO);

    Response updateSupplier(Long id, SupplierDTO supplierDTO);

    Response getAllSuppliers();

    Response getSupplierById(Long Id);

    Response deleteSupplier(Long id);

}
