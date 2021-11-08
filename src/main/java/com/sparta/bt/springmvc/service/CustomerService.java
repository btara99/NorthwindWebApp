package com.sparta.bt.springmvc.service;

import com.sparta.bt.springmvc.entities.CustomersEntity;

public class CustomerService {
    public CustomerService() {
    }

    public static void update(CustomersEntity updatedCustomer, CustomersEntity customersEntity) {
        customersEntity.setCompanyName(updatedCustomer.getCompanyName());
        customersEntity.setContactName(updatedCustomer.getContactName());
        customersEntity.setContactTitle(updatedCustomer.getContactTitle());
        customersEntity.setAddress(updatedCustomer.getAddress());
        customersEntity.setCity(updatedCustomer.getCity());
        customersEntity.setRegion(updatedCustomer.getRegion());
        customersEntity.setPostalCode(updatedCustomer.getPostalCode());
        customersEntity.setCountry(updatedCustomer.getCountry());
        customersEntity.setPhone(updatedCustomer.getPhone());
        customersEntity.setFax(updatedCustomer.getFax());
    }
}