package kz.pine.services;

import kz.pine.domain.Category;
import kz.pine.domain.Category;
import kz.pine.domain.Views;
import kz.pine.dto.EventType;
import kz.pine.dto.ObjectType;
import kz.pine.repositories.CategoryRepository;
import kz.pine.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiConsumer;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private final BiConsumer<EventType, Category> wsSender;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, WsSender wsSender) {
        this.categoryRepository = categoryRepository;
        this.wsSender = wsSender.getSender(ObjectType.PRODUCT, Views.FullCategoryInfo.class);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category create(Category category) {
        Category savedCategory = this.categoryRepository.save(category);
        wsSender.accept(EventType.CREATE, savedCategory);
        return savedCategory;
    }

    public Category update(Category old, Category category) {
        Category updatedCategory = this.categoryRepository.save(copyProperties(old, category));
        wsSender.accept(EventType.UPDATE, updatedCategory);
        return updatedCategory;
    }

    public void delete(Category category){
        this.categoryRepository.delete(category);
        wsSender.accept(EventType.REMOVE, category);
    }

    private Category copyProperties(Category old, Category category){
        old.setName(category.getName());
        old.setImage(category.getImage());
        return old;
    }

}
