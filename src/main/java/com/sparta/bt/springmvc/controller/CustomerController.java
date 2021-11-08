package com.sparta.bt.springmvc.controller;

import com.sparta.bt.springmvc.entities.CustomersEntity;
import com.sparta.bt.springmvc.repositories.CustomersRepository;
import com.sparta.bt.springmvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {

    private final CustomersRepository customersRepository;
    private final CustomerService customerService = new CustomerService();

    @Autowired
    public CustomerController(CustomersRepository customersRepository){
        this.customersRepository = customersRepository;
    }
    @GetMapping("/welcome")
    public String goToIndex(){
        return "index";
    }

    @GetMapping("/customers")
    public String getAllCustomers(Model model){
        model.addAttribute("customers",customersRepository.findAll());
        return "customers";
    }

    @GetMapping("/add-customer")
    public String addCustomer(Model model){
        CustomersEntity customersEntity = new CustomersEntity();
        model.addAttribute("customer",customersEntity);
        return "add-customer";
    }

    @PostMapping("/save-customer")
    public String saveCustomer(@ModelAttribute("customer")CustomersEntity customersEntity){
        customersRepository.save(customersEntity);
        return "saved-customer";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id")String id){
        CustomersEntity customersEntity = customersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"+ id));
        customersRepository.delete(customersEntity);
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id")String id,Model model){
        CustomersEntity customersEntity = customersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"+ id));
        model.addAttribute("customer",customersEntity);
        return "edit-customer";
    }

    @PostMapping("/update-customer/{id}")
    public String updateCustomer(@ModelAttribute("customer")CustomersEntity updatedCustomer, @PathVariable("id")String id){
        CustomersEntity customersEntity = customersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"+ id));
        CustomerService.update(updatedCustomer, customersEntity);
        customersRepository.save(customersEntity);
        return"index";
    }
// DELETE THIS LATER (adds the user and password to the db )
//    private void update(CustomersEntity updatedCustomer, CustomersEntity customersEntity) {
//        customerService.update(updatedCustomer, customersEntity);
//    }

    @GetMapping("/search-customer.html")
    public String searchCustomer(){
        return "search-customer";
    }

    @PostMapping("/search-results")
    public String getSearchResults(@ModelAttribute("customerName") String customerName,Model model){
//        List<CustomersEntity> foundCustomers = customersRepository.findAll()
//                .stream()
//                .filter(customersEntity -> customersEntity.getContactName().contains(customerName))
//                .collect(Collectors.toList());  FUNCTIONAL WAY

        List<CustomersEntity> foundCustomers = new ArrayList<>();
        for(CustomersEntity customersEntity: customersRepository.findAll()){
            if(customersEntity.getContactName().contains(customerName)){
                foundCustomers.add(customersEntity);
            }
        }
        model.addAttribute("searchResults",foundCustomers);
        return "search-results";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage(){
        return "access-denied";
    }

    @GetMapping("/login")
    public String getloginPage(){
        return "login";
    }



}
