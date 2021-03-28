package guru.springframework.controller.v1;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.api.v1.model.VendorListDTO;
import guru.springframework.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/api/v1/vendors/")
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO listVendors(){
        return new VendorListDTO(vendorService.listallvendors());
    }

    @GetMapping("/api/v1/vendors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }

    @PostMapping("/api/v1/vendors/")
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.createVendor(vendorDTO);
    }

    @PutMapping("/api/v1/vendors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateExistingVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.updateVendor(id,vendorDTO);
    }

    @PatchMapping("/api/v1/vendors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.patchVendor(id,vendorDTO);
    }

    @DeleteMapping("/api/v1/vendors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id){
        vendorService.deleteVendor(id);
    }
}
