package guru.springframework.services;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    CategoryService categoryService;

    public static final Long ID = 2L;
    public static final String NAME = "Jimmy";

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    public void getAllCategories() throws Exception {
        List<Category> categoryList = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categoryList);
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
        assertEquals(categoryDTOS.size(), 3);
    }

    @Test
    public void getCategoryByName() throws Exception{

        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDTO result = categoryService.getCategoryByName(NAME);
        assertEquals(result.getId(),ID);
        assertEquals(result.getName(),NAME);

    }

}