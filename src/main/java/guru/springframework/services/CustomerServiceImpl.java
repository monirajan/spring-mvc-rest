package guru.springframework.services;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerTocustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByName(String name) {
        return customerMapper.customerTocustomerDTO(customerRepository.findByFirstName(name));
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerTocustomerDTO)
                .map(customerDTO -> {
                    //set API URL
                    customerDTO.setCustomerUrl("/api/v1/customer/" + id);
                    return customerDTO;
                })
                .orElseThrow(ResourceNotFoundException::new); //todo implement better exception handling
         }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOTocustomer(customerDTO);
        return  saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOTocustomer(customerDTO);
        customer.setId(id);

        return  saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
           if(customerDTO.getFirstName()!=null) {
               customer.setFirstName(customerDTO.getFirstName());
           }
           if(customerDTO.getLastName()!=null) {
               customer.setLastName(customerDTO.getLastName());
           }

           CustomerDTO returnDTO = customerMapper.customerTocustomerDTO(customerRepository.save(customer));
           returnDTO.setCustomerUrl("/api/v1/customer/"+id);
           return returnDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerDTO saveAndReturnDTO(Customer customer)
    {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDTO = customerMapper.customerTocustomerDTO(savedCustomer);
        returnDTO.setCustomerUrl("/api/v1/customer/"+savedCustomer.getId());
        return returnDTO;
    }
}
