package com.StockSync.sourav.StockSync.service.impl;

import com.StockSync.sourav.StockSync.dto.CategoryDTO;
import com.StockSync.sourav.StockSync.dto.Response;
import com.StockSync.sourav.StockSync.dto.SupplierDTO;
import com.StockSync.sourav.StockSync.entity.Supplier;
import com.StockSync.sourav.StockSync.exception.NotFoundException;
import com.StockSync.sourav.StockSync.repository.SupplierRepository;
import com.StockSync.sourav.StockSync.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class SupplierServiceImpl implements SupplierService {


    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Response addSupplier(SupplierDTO supplierDTO) {
        Supplier supplierToSave = modelMapper.map(supplierDTO, Supplier.class);
        supplierRepository.save(supplierToSave);

        return Response.builder()
                .status(201)
                .message("Supplier Created successfully")
                .build();
    }

    @Override
    public Response getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<SupplierDTO> supplierDTOS = modelMapper.map(suppliers, new TypeToken<List<CategoryDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .suppliers(supplierDTOS)
                .message("success")
                .build();
    }

    @Override
    public Response getSupplierById(Long id) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Supplier Not Found"));

        SupplierDTO supplierDTO = modelMapper.map(existingSupplier, SupplierDTO.class);

        return Response.builder()
                .status(200)
                .supplier(supplierDTO)
                .message("success")
                .build();
    }


    @Override
    public Response updateSupplier(Long id, SupplierDTO supplierDTO) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Supplier Not Found"));

        if (supplierDTO.getName() != null) existingSupplier.setName(supplierDTO.getName());
        if(supplierDTO.getEmail() != null) existingSupplier.setEmail(supplierDTO.getEmail());
        if(supplierDTO.getAddress() != null) existingSupplier.setAddress(supplierDTO.getAddress());
        if(supplierDTO.getUpiId() != null) existingSupplier.setUpiId(supplierDTO.getUpiId());
        supplierRepository.save(existingSupplier);


        return Response.builder()
                .status(204)
                .message("Supplier Successfully updated")
                .build();
    }

    @Override
    public Response deleteSupplier(Long id) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Supplier Not Found"));

        supplierRepository.deleteById(id);

        return Response.builder()
                .status(204)
                .message("Supplier Successfully deleted")
                .build();
    }
}
