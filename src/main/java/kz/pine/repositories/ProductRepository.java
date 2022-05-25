package kz.pine.repositories;

import kz.pine.domain.Category;
import kz.pine.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByCategory(Category category, Pageable pageable);
    Page<Product> findAllByPriceBetween(double min, double max, Pageable pageable);
    Page<Product> findAllByCategoryAndPriceBetween(Category category, double min, double max, Pageable pageable);
}
