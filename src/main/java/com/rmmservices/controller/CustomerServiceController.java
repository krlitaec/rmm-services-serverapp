package com.rmmservices.controller;

import com.rmmservices.exception.ResourceNotFoundException;
import com.rmmservices.model.*;
import com.rmmservices.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * RestController class to create/change the customer_service table
 *
 * @author Karla
 * @since 10-06-2019
 */
@RestController
public class CustomerServiceController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerServiceRepository customerServiceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SmartServiceRepository smartServiceRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/customerServiceList/{idCustomer}")
    public List<CustomerService> getCustomerService(@PathVariable Integer idCustomer) {
        return customerServiceRepository.findByCustomer(idCustomer);
    }

    @PostMapping("/createCustomerService/{userName}/{password}")
    public CustomerService createCustomerService(@Valid @RequestBody CustomerService service, @PathVariable String userName, @PathVariable String password) {
        Boolean validUser = userRepository.verifyUser(userName, password);
        if (validUser.equals(true)) {
            User userLogin = userRepository.getUserByLogin(userName, password);
            Customer customer = customerRepository.findByUser(userLogin.getIdUser());
            if (customer != null
                    && service.getIdSmartService() != null && service.getIdSmartService().getIdSmartService() != null
                    && service.getIdDevice() != null && service.getIdDevice().getIdDevice() != null ) {
                CustomerService existent = customerServiceRepository.findExistent(customer.getIdCustomer()
                        , service.getIdDevice().getIdDevice(), service.getIdSmartService().getIdSmartService());
                if (existent == null) {
                    service.setCreatedBy(userLogin);
                    service.setCreatedAt(Calendar.getInstance().getTime());
                    service.setUpdatedBy(userLogin);
                    service.setUpdatedAt(Calendar.getInstance().getTime());
                    service.setIdCustomer(customer);
                    SmartService smartService = smartServiceRepository.findById(service.getIdSmartService().getIdSmartService()).get();
                    service.setIdSmartService(smartService);
                    Device device = deviceRepository.findById(service.getIdDevice().getIdDevice()).get();
                    service.setIdDevice(device);
                    service = customerServiceRepository.save(service);
                } else {
                    service = null;
                }
            } else {
                service = null;
            }
        } else {
            service = null;
        }
        return service;
    }

    @DeleteMapping("/deleteCustomerService/{idCustomerService}/{userName}/{password}")
    public ResponseEntity<?> deleteCustomerService(@PathVariable Integer idCustomerService, @PathVariable String userName, @PathVariable String password) {
        Boolean validUser = userRepository.verifyUser(userName, password);
        if (validUser.equals(true)) {
            return customerServiceRepository.findById(idCustomerService)
                    .map(service -> {
                        customerServiceRepository.delete(service);
                        return ResponseEntity.ok().build();
                    }).orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + idCustomerService));
        } else {
            return null;
        }
    }

    @GetMapping("/calculateMonthlyCost/{idCustomer}")
    public List<Map<String, Object>> calculateMonthlyCost(@PathVariable Integer idCustomer) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal totalDevices = BigDecimal.ZERO;
        BigDecimal totalServices = BigDecimal.ZERO;
        BigDecimal deviceCost = BigDecimal.valueOf(4);
        List<CustomerService> services = customerServiceRepository.findByCustomer(idCustomer);
        List<Map<String, Object>> resultados = new ArrayList<>();
        List<Integer> devicesId = new ArrayList<>();
        for (CustomerService s: services) {
            Integer idDevice = s.getIdDevice().getIdDevice();
            if (!devicesId.contains(idDevice)) {
                devicesId.add(idDevice);
            }
        }
        totalDevices = BigDecimal.valueOf(devicesId.size()).multiply(deviceCost);
        Map<String, Object> mapTotalDevices = new HashMap();
        mapTotalDevices.put(""+devicesId.size()+" ($"+deviceCost+") Total Devices:", "$"+totalDevices);
        Map<String, Object> mapTotalServices = new HashMap();
        List<SmartService> smartServices = smartServiceRepository.findAll();
        for (SmartService ss: smartServices) {
            BigDecimal totalSs = BigDecimal.ZERO;
            Integer cantidad = 0;
            Integer idSmartService = ss.getIdSmartService();
            for (CustomerService s: services) {
                if (s.getIdSmartService().getIdSmartService().equals(idSmartService)) {
                    cantidad++;
                }
            }
            if (cantidad != 0) {
                totalSs = BigDecimal.valueOf(cantidad).multiply(ss.getMonthlyCost());
                mapTotalServices.put(""+cantidad+" ($"+ss.getMonthlyCost()+") "+ss.getName(), "$"+totalSs);
                totalServices = totalServices.add(totalSs);
            }
        }
        total = totalDevices.add(totalServices);
        //Total
        Map<String, Object> mapTotal = new HashMap();
        mapTotal.put("TOTAL:", "$"+total);
        resultados.add(mapTotal);
        //Explanation
        List<Map<String, Object>> totalExplanation = new ArrayList<>();
        totalExplanation.add(mapTotalDevices);
        totalExplanation.add(mapTotalServices);
        Map<String, Object> mapExplanation = new HashMap();
        mapExplanation.put("Explanation:", totalExplanation);
        resultados.add(mapExplanation);
        return resultados;
    }
}