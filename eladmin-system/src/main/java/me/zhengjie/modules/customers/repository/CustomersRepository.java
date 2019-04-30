package me.zhengjie.modules.customers.repository;

import me.zhengjie.modules.customers.domain.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-29
*/
public interface CustomersRepository extends JpaRepository<Customers, Long>, JpaSpecificationExecutor {
}