package com.mitron.onlinestore.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.mitron.onlinestore.domain.models.binding.CategoryAddBindingModel;
import com.mitron.onlinestore.domain.models.service.CategoryServiceModel;
import com.mitron.onlinestore.domain.models.view.CategoryViewModel;
import com.mitron.onlinestore.service.CategoryService;
import com.mitron.onlinestore.util.error.CategoryNotFoundException;
import com.mitron.onlinestore.web.annotations.PageTitle;

import javax.validation.Valid;

import static com.mitron.onlinestore.util.constants.AppConstants.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PageTitle("Add Category")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategory(@ModelAttribute(name = MODEL) CategoryAddBindingModel categoryAddBindingModel,
                                    ModelAndView modelAndView) {

        return loadAndReturnModelAndView(categoryAddBindingModel, modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategoryConfirm(@Valid @ModelAttribute(name = MODEL) CategoryAddBindingModel model,
                                           BindingResult bindingResult, ModelAndView modelAndView) {

        CategoryServiceModel categoryServiceModel =
                modelMapper.map(model, CategoryServiceModel.class);

        if (bindingResult.hasErrors() ||
                categoryService.addCategory(categoryServiceModel) == null) {

            return loadAndReturnModelAndView(model, modelAndView);
        }
        return redirect("/categories/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(CATEGORIES)
    public ModelAndView allCategories(ModelAndView modelAndView) {

        List<CategoryViewModel> categories =
                mapCategoryServiceToViewModel(categoryService.findAllFilteredCategories());

        modelAndView.addObject(CATEGORIES_TO_LOWER_CASE, categories);

        return view("category/all-categories", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Edit Category")
    public ModelAndView editCategory(@PathVariable String id, ModelAndView modelAndView) {

        CategoryViewModel categoryViewModel =
                modelMapper.map(categoryService.findCategoryById(id), CategoryViewModel.class);

        modelAndView.addObject(MODEL, categoryViewModel);

        return view("category/edit-category", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editCategoryConfirm(@PathVariable String id, @Valid @ModelAttribute(name = "model") CategoryAddBindingModel model,
                                            BindingResult bindingResult, ModelAndView modelAndView) {

        CategoryServiceModel categoryServiceModel =
                modelMapper.map(model, CategoryServiceModel.class);

        if (bindingResult.hasErrors() ||
                categoryService.editCategory(id, categoryServiceModel) == null) {

            modelAndView.addObject(MODEL, model);

            return view("category/edit-category", modelAndView);
        }

        return redirect("/categories/all");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Delete Category")
    public ModelAndView deleteCategory(@PathVariable String id, ModelAndView modelAndView) {

        CategoryViewModel categoryViewModel =
                modelMapper.map(categoryService.findCategoryById(id), CategoryViewModel.class);

        modelAndView.addObject(MODEL, categoryViewModel);

        return view("category/delete-category", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteCategoryConfirm(@PathVariable String id) {

        categoryService.deleteCategory(id);

        return redirect("/categories/all");
    }

    @GetMapping("/fetch")
    /*@PreAuthorize("hasRole('ROLE_MODERATOR')")*/
    @ResponseBody
    public List<CategoryViewModel> fetchCategories() {
        List<CategoryViewModel> categories =
                mapCategoryServiceToViewModel(categoryService.findAllFilteredCategories());

        return categories;
    }

    private List<CategoryViewModel> mapCategoryServiceToViewModel
            (List<CategoryServiceModel> categoryServiceModels){
        return categoryServiceModels.stream()
                .map(product -> modelMapper.map(product, CategoryViewModel.class))
                .collect(Collectors.toList());
    }

    private ModelAndView loadAndReturnModelAndView
            (CategoryAddBindingModel categoryAddBindingModel, ModelAndView modelAndView) {

        modelAndView.addObject(MODEL, categoryAddBindingModel);

        return view("category/add-category", modelAndView);
    }

    @ExceptionHandler({CategoryNotFoundException.class})
    public ModelAndView handleProductNotFound(CategoryNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView(ERROR);
        modelAndView.addObject(MESSAGE, e.getMessage());
        modelAndView.addObject(STATUS_CODE, e.getStatusCode());

        return modelAndView;
    }
}


