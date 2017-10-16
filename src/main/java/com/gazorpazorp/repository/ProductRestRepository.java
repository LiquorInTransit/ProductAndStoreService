package com.gazorpazorp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.gazorpazorp.model.Product;

@RepositoryRestResource(path="products")
public interface ProductRestRepository extends PagingAndSortingRepository<Product, Long>{
	
	
	
	@RestResource(path="secondaryCategory")
	public Page<Product> findBySecondaryCategoryIgnoreCase(@Param("secondaryCategory") String category, Pageable p);
	
	@RestResource(path="primaryCategory")
	public Page<Product> findByPrimaryCategoryIgnoreCase(@Param("primaryCategory") String category, Pageable p);
	
	@RestResource(path="tags")
	public Page<Product> findByTagsIgnoreCaseContaining(@Param("tags") String tags, Pageable p);
	
	
	@Override
	@RestResource(exported=false)
	List<Product> findAllById(Iterable<Long> ids);
	
	/*
	 * Excluded endpoints
	 */
	@Override
	@RestResource(exported=false)
	void deleteById(Long id);


	@Override
	@RestResource(exported=false)
	void delete(Product entity);

	@Override
	@RestResource(exported=false)
	void deleteAll(Iterable<? extends Product> entities);

	@Override
	@RestResource(exported=false)
	void deleteAll();
	
	@Override
	@RestResource(exported=false)
	<S extends Product> S save(S entity);

	@Override
	@RestResource(exported=false)
	<S extends Product> Iterable<S> saveAll(Iterable<S> entities);
}
