package guru.springframework.controller.v1;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.api.v1.model.CustomerListDTO;
import guru.springframework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers()
    {
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id)
    {
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerById(id),HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO)
    {
        return new ResponseEntity<CustomerDTO>(
                customerService.createCustomer(customerDTO),HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<CustomerDTO> updateCustomerById(@PathVariable Long id, @RequestBody CustomerDTO customerDTO)
    {
        return new ResponseEntity<CustomerDTO>(
                customerService.updateCustomer(id,customerDTO),HttpStatus.OK
        );
    }

    @PatchMapping({"/{id}"})
    public ResponseEntity<CustomerDTO> patchCustomerById(@PathVariable Long id, @RequestBody CustomerDTO customerDTO)
    {
        return new ResponseEntity<CustomerDTO>(
                customerService.patchCustomer(id,customerDTO),HttpStatus.OK
        );
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id)
    {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
