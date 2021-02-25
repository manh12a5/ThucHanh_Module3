package controller;

import model.Category;
import model.Product;
import service.category.ICategoryService;
import service.category.CategoryService;
import service.product.IProductService;
import service.product.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {

    //Tạo đối tượng
    IProductService productService = new ProductService();
    ICategoryService manufacturerService = new CategoryService();

    //Các phương thức Get
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "":
                showAllProducts(request, response);
                break;
            case "create":
                showCreateProduct(request, response);
                break;
            case "edit":
                showEditProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
        }
    }

    private void showAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/view.jsp");
        List<Product> productList = productService.findAll();
        request.setAttribute("product", productList);
        requestDispatcher.forward(request, response);
    }

    private void showCreateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        List<Category> categories = manufacturerService.findAll();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        request.setAttribute("product", product);
        request.setAttribute("category", categories);
        requestDispatcher.forward(request, response);
    }

    private void showEditProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Category> categories = manufacturerService.findAll();
        Product product = productService.findById(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/edit.jsp");
        request.setAttribute("product", product);
        request.setAttribute("category", categories);
        requestDispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.delete(id);
        List<Product> products = productService.findAll();
        request.setAttribute("product", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/view.jsp");
        requestDispatcher.forward(request, response);
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search");
        List<Product> products = productService.findByName(name);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/view.jsp");
        request.setAttribute("product", products);
        requestDispatcher.forward(request, response);
    }

    //Các phương thức Post
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createNewProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
        }
    }

    private void createNewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        String color = request.getParameter("color");
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        int category = Integer.parseInt(request.getParameter("category"));
        Product product = new Product(name, price, amount, color, image, description, category);
        productService.insert(product);
        try {
            response.sendRedirect("/products");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        String color = request.getParameter("color");
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        int category = Integer.parseInt(request.getParameter("category"));
        Product product = new Product(id, name, price, amount, color, image, description, category);
        productService.edit(product);
        try {
            response.sendRedirect("/products");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
