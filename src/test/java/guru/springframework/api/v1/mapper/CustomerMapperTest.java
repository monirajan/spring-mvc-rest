package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    public static final Long ID = 2L;
    public static final String FIRSTNAME = "Monisha";
    public static final String LASTNAME = "Rajan";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        CustomerDTO customerDTO = customerMapper.customerTocustomerDTO(customer);
        assertEquals(customerDTO.getId(),ID);
        assertEquals(customerDTO.getFirstName(),FIRSTNAME);
        assertEquals(customerDTO.getLastName(),LASTNAME);
    }


}