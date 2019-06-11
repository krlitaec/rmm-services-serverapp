package com.rmmservices;

import com.rmmservices.model.Customer;
import com.rmmservices.model.CustomerService;
import com.rmmservices.repository.CustomerRepository;
import com.rmmservices.repository.CustomerServiceRepository;
import com.rmmservices.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RmmServicesTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerServiceRepository customerServiceRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private String username;
    private String password;
    private Integer idCustomer;
    private Integer idUser;

    @Test
    public void verifyUser() {
        username = "admin";
        password = "123456";
        assertEquals(true, userRepository.verifyUser(username, password));
        password = "12345678";
        assertEquals(true, userRepository.verifyUser(username, password));
    }

    @Test
    public void getCustomerByUser() {
        idUser = 1;
        Customer customer = customerRepository.findByUser(idUser);
        assertNull(customer);
        idUser = 2;
        customer = customerRepository.findByUser(idUser);
        assertNotNull(customer);
    }

    @Test
    public void getCustomerServices() throws Exception {
        idCustomer = 1;
        List<CustomerService> services = customerServiceRepository.findByCustomer(idCustomer);
        assertNotNull(services);
        idCustomer = 2;
        services = customerServiceRepository.findByCustomer(idCustomer);
        assertNotNull(services);
    }
}