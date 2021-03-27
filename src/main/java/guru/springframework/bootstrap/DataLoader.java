package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springframework.repositories.CategoryRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private CategoryRepository categoryRespository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public DataLoader(CategoryRepository categoryRespository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRespository = categoryRespository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategory();
        System.out.println("Data Loaded = " + categoryRespository.count() );

        loadCustomer();
        System.out.println("Customer Loaded = " + customerRepository.count() );

        loadVendors();
        System.out.println("Vendors Loaded = " + customerRepository.count() );

    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Amazon");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Flipkart");

        Vendor vendor3 = new Vendor();
        vendor3.setName("Big Basket");

        Vendor vendor4 = new Vendor();
        vendor4.setName("Dunzo");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);
        vendorRepository.save(vendor4);
    }

    private void loadCustomer() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Hema");

        Customer customer2 = new Customer();
        customer2.setFirstName("Yuva");

        Customer customer3 = new Customer();
        customer3.setFirstName("Rajan");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
    }

    private void loadCategory() {
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
    }
}
