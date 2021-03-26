package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springframework.repositories.CategoryRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private CategoryRepository categoryRespository;
    private CustomerRepository customerRepository;

    public DataLoader(CategoryRepository categoryRespository, CustomerRepository customerRepository) {
        this.categoryRespository = categoryRespository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRespository.save(fruits);
        categoryRespository.save(dried);
        categoryRespository.save(fresh);
        categoryRespository.save(exotic);
        categoryRespository.save(nuts);

        System.out.println("Data Loaded = " + categoryRespository.count() );

        Customer customer1 = new Customer();
        customer1.setFirstName("Hema");

        Customer customer2 = new Customer();
        customer2.setFirstName("Yuva");

        Customer customer3 = new Customer();
        customer3.setFirstName("Rajan");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        System.out.println("Customer Loaded = " + customerRepository.count() );

    }
}
