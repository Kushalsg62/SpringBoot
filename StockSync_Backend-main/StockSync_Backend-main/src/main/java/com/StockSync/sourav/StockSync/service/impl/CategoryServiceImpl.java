package com.StockSync.sourav.StockSync.service.impl;

import com.StockSync.sourav.StockSync.dto.CategoryDTO;
import com.StockSync.sourav.StockSync.dto.Response;
import com.StockSync.sourav.StockSync.entity.Category;
import com.StockSync.sourav.StockSync.exception.NotFoundException;
import com.StockSync.sourav.StockSync.repository.CategoryRepository;
import com.StockSync.sourav.StockSync.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Response createCategory(CategoryDTO categoryDTO) {
        Category categoryToSave = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.save(categoryToSave);

        return Response.builder()
                .status(201)
                .message("Category Created successfully")
                .build();
    }

    @Override
    public Response getAllCategories() {
        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<CategoryDTO> categoryDTOS = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .categories(categoryDTOS)
                .message("success")
                .build();
    }

    @Override
    public Response getCategoryById(Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Category Not Found"));

        CategoryDTO categoryDTO = modelMapper.map(existingCategory, CategoryDTO.class);

        return Response.builder()
                .status(200)
                .category(categoryDTO)
                .message("success")
                .build();
    }

    @Override
    public Response updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Category Not Found"));

        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);

        return Response.builder()
                .status(204)
                .message("Category Successfully updated")
                .build();
    }

    @Override
    public Response deleteCategory(Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Category Not Found"));

        categoryRepository.deleteById(id);

        return Response.builder()
                .status(204)
                .message("Category Successfully deleted")
                .build();
    }
}
