package me.zhengjie.modules.address.repository;

import me.zhengjie.modules.address.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-29
*/
public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor {
}