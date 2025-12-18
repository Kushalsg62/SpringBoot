package com.StockSync.sourav.StockSync.service;

import com.StockSync.sourav.StockSync.dto.CategoryDTO;
import com.StockSync.sourav.StockSync.dto.Response;

public interface CategoryService {

    Response createCategory(CategoryDTO categoryDTO);

    Response getAllCategories();

    Response getCategoryById(Long Id);

    Response updateCategory(Long id, CategoryDTO categoryDTO);

    Response deleteCategory(Long id);

}
