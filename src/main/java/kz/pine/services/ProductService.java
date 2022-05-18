package kz.pine.services;

import kz.pine.domain.Category;
import kz.pine.domain.Product;
import kz.pine.domain.Views;
import kz.pine.dto.EventType;
import kz.pine.dto.ObjectType;
import kz.pine.repositories.ProductRepository;
import kz.pine.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.function.BiConsumer;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private final BiConsumer<EventType, Product> wsSender;

    @Autowired
    public ProductService(ProductRepository productRepository, WsSender wsSender) {
        this.productRepository = productRepository;
        this.wsSender = wsSender.getSender(ObjectType.PRODUCT, Views.FullProductInfo.class);
    }

    public List<Product> findAll(Category category){
        return productRepository.findAll();
    }

    public Product create(Product product) {
        Product savedProduct = this.productRepository.save(product);
        wsSender.accept(EventType.CREATE, savedProduct);
        return savedProduct;
    }

    public Product update(Product old, Product product) {
        Product updatedProduct = this.productRepository.save(copyProperties(old, product));
        wsSender.accept(EventType.UPDATE, updatedProduct);
        return updatedProduct;
    }

    public void delete(Product product){
        this.productRepository.delete(product);
        wsSender.accept(EventType.REMOVE, product);
    }

    private Product copyProperties(Product old, Product product){
        old.setName(product.getName());
        old.setBrand(product.getBrand());
        old.setImage(product.getImage());
        old.setPrice(product.getPrice());
        old.setCategory(product.getCategory());
        return old;
    }

}
