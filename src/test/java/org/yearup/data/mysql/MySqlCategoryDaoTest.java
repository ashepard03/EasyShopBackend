package org.yearup.data.mysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MySqlCategoryDaoTest extends BaseDaoTestClass {
    private MySqlCategoryDao dao;

    @BeforeEach
    public void setup()
    {
        dao = new MySqlCategoryDao(dataSource);
    }

    @Test
    public void getById_shouldReturn_theCorrectProduct()
    {
        // arrange
        int categoryId = 3;
        Category expected = new Category()
        {{
            setCategoryId(3);
            setName("Home & Kitchen");
            setDescription("Find everything you need to decorate and equip your home.");

        }};

        // act
        var actual = dao.getById(categoryId);

        // assert
        assertEquals(expected.getName(), actual.getName(), "Checking the name matches the expected categoryid name");
    }
}
