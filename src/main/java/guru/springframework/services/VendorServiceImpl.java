package guru.springframework.services;

import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService{

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> listallvendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                        VendorDTO vendorDTO = vendorMapper.vendorTovendorDTO(vendor);
                        vendorDTO.setVendorUrl("/api/v1/vendors/" + vendor.getId());
                        return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorTovendorDTO)
                .map(vendorDTO -> {
                    //set API URL
                    vendorDTO.setVendorUrl("/api/v1/customer/" + id);
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new); //todo implement better exception handling

    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        return saveandreturnDTO(vendor);
    }

    private VendorDTO saveandreturnDTO(Vendor vendor) {

        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO vendorDTO = vendorMapper.vendorTovendorDTO(savedVendor);
        vendorDTO.setVendorUrl("/api/v1/vendors/" + savedVendor.getId());
        return vendorDTO;
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendor.setId(id);
        return saveandreturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if(vendorDTO.getName()!=null)
                vendor.setName(vendorDTO.getName());

            VendorDTO returnDTO = vendorMapper.vendorTovendorDTO(vendor);
            returnDTO.setVendorUrl("/api/v1/vendors/" + vendor.getId());
            return returnDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}
