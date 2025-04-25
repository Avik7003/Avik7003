package com.example.RegisterUser.RegisterUser.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.RegisterUser.RegisterUser.entity.BooksModule;
import com.example.RegisterUser.RegisterUser.entity.Orders;

@Repository
public interface OrderModuleRepo extends JpaRepository<Orders,Long> {

	
	

	@Query(value="SELECT b FROM BooksModule b where b.title=:title")
	BooksModule findByName(@Param("title")String title);
	//@Query(value="SELECT * FROM orders o WHERE o.customer_id=:customerId AND o.createDate>CURDATE()-INTERVAL 7 DAY",nativeQuery=true)
	@Query(value = "SELECT * FROM orders o WHERE o.customer_id = :customerId AND o.create_date > CURDATE() - INTERVAL 7 DAY", nativeQuery = true)
	public List<Orders> bookOrderLastweek(Long customerId);


}
