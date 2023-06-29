# Easy Shop Backend
## Appplication Overview
This is a Spring Boot applictaion that implements java and a SQL database. It's a online store created for a company called EasyShop. The store allows user to have register or log into accounts and complete multiple search functions on a web server API.
### Search Functions
- By category
    - Electronics
    - Fashion
    - Home & Kitchen
- By Minimum or Maximum price
    - Custom Sliders
- Custom Search
    - Color
    - Price range
    - Category

## Usage Instructions
### Getting Started
In order for a user to start the application they are required to clone both the web application and backend application from the Github repository to their preferred IDE.  
From there the user will need to load the EasyShopBackend project and run the main program located in the following path EasyShopBackend/src/main/java/org/yearup/EasyShopApplication.
- Main Program Class Name
    - EasyShopApplication
      <br/><br/>
      ![image](https://github.com/ashepard03/EasyShopBackend/assets/129906864/0630b3e4-1a40-40e2-b4a8-74b03b240d18)

Once the main program is running, the user will need to load the web application files where they will find the main file required to open the API in an online web browser.
- Main File Name
    - index.html
      <br/><br/>
      ![image](https://github.com/ashepard03/EasyShopBackend/assets/129906864/b94f72e7-6634-44ff-8f5c-10d2b145bf44)

## Aplication Code Implemetations
### Categories Controller
The CategoriesController was missing the proper method implementations and REST mapping annotations to make it work.  
- Methods Implemented
  - Get a Category By Id
  - Get Products By Id
    - CRUD Operation Methods (Admin Role Only)
      - Add a Category
      - Update a Category
      - Delete a Category
#### MySqlCategoryDao
The MySqlCategoryDao was also missing the proper method implementations to retrieve the data from the easyshop database and convert it to the necessary java object or variable.
### Bug 1: Search/Filter Functionality
A known bug in the oriinal version was the search fucntion/filter returning incorrect results to the user . In order to fix the known bug, updating the search method in the MySqlProductDao class was required.  

The updated method belows adds the missing a queury statment for the maximum price and I set the additional parameter placeholders.
```
 String sql = """
                SELECT * FROM products
                WHERE (category_id = ? OR ? = -1)
                AND (price <= ? OR ? = -1)
                AND (price >= ? OR ? = -1)
                AND (color = ? OR ? = '')""";

        categoryId = categoryId == null ? -1 : categoryId;
        minPrice = minPrice == null ? new BigDecimal("-1") : minPrice;
        maxPrice = maxPrice == null ? new BigDecimal("-1") : maxPrice;
        color = color == null ? "" : color;

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            statement.setInt(2, categoryId);
            statement.setBigDecimal(3, maxPrice);
            statement.setBigDecimal(4, maxPrice);
            statement.setBigDecimal(5, minPrice);
            statement.setBigDecimal(6, minPrice);
            statement.setString(7, color);
            statement.setString(8, color);

            ResultSet row = statement.executeQuery();
```
### Bug 2: Product Duplication
The second known bug caused a new product to be added when an user with the admin role tried to update an existing product rather just updating the select product's field. This bug needed to be fix in the update method of the Product Controller class.
```
// originaly method line created a new product
productDao.create(product);

// updated method line to UPDATE new product
productDao.update(id, produt);
```
## Acknowledgements
I want to thank Javier, Anne, Mohammed, Raquan and Cristi for thier assistance in this final capstone.
