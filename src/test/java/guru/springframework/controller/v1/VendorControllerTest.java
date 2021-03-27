package guru.springframework.controller.v1;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest extends AbstractRestControllerTest{

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc; //provided by Spring Context

    VendorDTO vendorDTO_1;
    VendorDTO vendorDTO_2;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
        vendorDTO_1 = new VendorDTO(1l,"Vendor 1", "api/v1/vendors" + "/1");
        vendorDTO_2 = new VendorDTO(2l, "Vendor 2","api/v1/vendors" + "/2");
    }


    @Test
    public void listVendors() throws Exception {

        List<VendorDTO> vendorDTOList = Arrays.asList(vendorDTO_1, vendorDTO_2);

        when(vendorService.listallvendors()).thenReturn(vendorDTOList);

        mockMvc.perform(get("/api/v1/vendors/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorDTOList", hasSize(2)));
    }

    @Test
    public void getVendorById() throws Exception {

        given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO_1);

        verify(vendorService).getVendorById(anyLong());

        mockMvc.perform(get("/api/v1/vendors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void createNewVendor() {
    }

    @Test
    public void updateExistingVendor() {
    }

    @Test
    public void patchVendor() {
    }

    @Test
    public void deleteVendor() {
    }
}