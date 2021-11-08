package com.sparta.bt.springmvc.repositories;

import com.sparta.bt.springmvc.entities.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<CustomersEntity,String> {

}
