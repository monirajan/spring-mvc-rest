package guru.springframework.services;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    CustomerService customerService;

    public static final Long ID = 1L;
    public static final String FIRSTNAME = "Monisha";
    public static final String LASTNAME = "Rajan";

    @Mock
    CustomerRepository customerRepository;


    @Before
    public void run()
    {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() {
        List<Customer> customerList = Arrays.asList(new Customer(),new Customer());
        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
        assertEquals(customerDTOList.size(),2);
    }

    @Test
    public void getCustomerByName() {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        when(customerRepository.findByFirstName(anyString())).thenReturn(customer);
        CustomerDTO customerDTO = customerService.getCustomerByName(FIRSTNAME);

        assertEquals(customerDTO.getFirstName(), FIRSTNAME);
        assertEquals(customerDTO.getLastName(),LASTNAME);
        assertEquals(customerDTO.getId(),ID);
    }
}