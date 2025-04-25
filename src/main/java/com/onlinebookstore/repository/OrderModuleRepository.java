package com.onlinebookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlinebookstore.entity.BooksModule;
import com.onlinebookstore.entity.Orders;

@Repository
public interface OrderModuleRepository extends JpaRepository<Orders, Long> {

	@Query("SELECT b FROM BooksModule b WHERE b.title = :title")
	BooksModule findByName(@Param("title") String title);

	@Query(value = "SELECT * FROM orders o WHERE o.custmer_id = :custmertId AND o.create_date > CURDATE() - INTERVAL 7 DAY", nativeQuery = true)
	public List<Orders> findByOrdersPlacedlastweek(Long custmertId);

	
	
	@Query(value = "select * from orders where book_id = :bookId and custmer_id =:custmerId", nativeQuery = true)
	public Orders findByBookIdandCustmerId(Long bookId, Long custmerId);

	
  

}
