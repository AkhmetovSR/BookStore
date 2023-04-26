package com.example.bookstore.repositories;

import com.example.bookstore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByTitleContainingIgnoreCase(String title);

    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3)", nativeQuery = true)
    List<Product> findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(String title, int from, int to);

    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3) order by price asc", nativeQuery = true)
    List<Product> findByTitleOrderByPriceAsc(String title, float from, float to);

    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3) order by price desc", nativeQuery = true)
    List<Product> findByTitleOrderByPriceDesc(String title, float from, float to);

    @Query(value = "select * from product order by price asc", nativeQuery = true)
    List<Product> findAllByOrderByPriceAsc();

    @Query(value = "select * from product order by price desc", nativeQuery = true)
    List<Product> findAllByOrderByPriceDesc();

    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1')) order by price asc", nativeQuery = true)
    List<Product> findByTitleOrderByPriceAsc(String title);

    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1')) order by price desc", nativeQuery = true)
    List<Product> findByTitleOrderByPriceDesc(String title);

    @Query(value = "select * from product where (price >= ?1 and price <= ?2)", nativeQuery = true)
    List<Product> findAllByPrice(float from, float to);

    @Query(value = "select * from product where (price >= ?1 and price <= ?2) order by price asc", nativeQuery = true)
    List<Product> findAllByOrderByPriceAsc(float from, float to);

    @Query(value = "select * from product where (price >= ?1 and price <= ?2) order by price desc", nativeQuery = true)
    List<Product> findAllByOrderByPriceDesc(float from, float to);
}
