package com.sportyshoes.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sportyshoes.model.ProductCategory;
import com.sportyshoes.model.PurchaseItem;
import com.sportyshoes.model.User;
import com.sportyshoes.repository.UserRepository;
import com.sportyshoes.service.ProductCategoryService;
import com.sportyshoes.service.ProductService;
import com.sportyshoes.service.PurchaseItemService;
import com.sportyshoes.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import com.sportyshoes.model.Product;

@Controller
public class AdminController {

	// PRODUCT CATEGORY SECTION FOR ADMIN:
	// ===================================

	@Autowired
	ProductCategoryService productCategoryService;

	@GetMapping("/admin")
	public String admin() {
		return "adminhome";
	}

	@GetMapping("/admin/categories")
	public String getCategory(Model model) {
		model.addAttribute("categories", productCategoryService.getAllCategories());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String addCategoryGet(Model model) {
		model.addAttribute("productCategory", new ProductCategory());
		return "addProductCategory";
	}

	@PostMapping("/admin/categories/add")
	public String addCategoryPost(@ModelAttribute("productCategory") ProductCategory productCategory,
			HttpServletRequest request) throws ServletException {

		productCategoryService.addProductCategory(productCategory);
		return "redirect:/admin/categories";

	}

	@GetMapping(path = "/admin/category/edit/{id}")
	public ModelAndView showEditCategoryPage(@PathVariable("id") Integer id, Model model) {

		Optional<ProductCategory> productCategory = productCategoryService.getCategoryById(id);
		model.addAttribute("productCategory", productCategory);
		return new ModelAndView("updateProductCategory");
	}

	@PostMapping(path = "/admin/category/update/{id}")
	public ModelAndView updateCategory(@ModelAttribute("productCategory") ProductCategory category) {
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/categories");

		productCategoryService.updateCategory(category);

		return modelAndView;
	}

	@GetMapping("/admin/category/delete/{id}")
	public String deleteCategory(@PathVariable("id") Integer id) {
		productCategoryService.deleteCategory(id);
		return "redirect:/admin/categories";
	}

	// ====================================
	// PRODUCT SECTION FOR ADMIN:
	// ====================================

	@Autowired
	ProductService productService;

	@GetMapping("/admin/products")
	public String Products(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@GetMapping("/admin/product/add")
	public String addProductGet(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("productCategory", productCategoryService.getAllCategories());
		return "addProduct";
	}

	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	@PostMapping("admin/product/add")
	public String addProductPost(@ModelAttribute("product") Product product, HttpServletRequest request,
			@RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imgName)
			throws ServletException, IOException {

//		Product product = new Product();
//		product.setProductId(productDTO.getProductId());
//		product.setProductName(productDTO.getProductName());
//		product.setProductCategory(productCategoryService.getCategoryById(productDTO.getCategoryId()).get());
//		product.setSize(productDTO.getSize());
//		product.setPrice(productDTO.getPrice());
//		product.setDescription(productDTO.getDescription());

		// Manage images of products

		String imageUUID;
		if (!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID); // Create file name and path
			Files.write(fileNameAndPath, file.getBytes());
		} else {
			imageUUID = imgName;
		}

		product.setImageName(imageUUID);

		productService.saveProduct(product);
		return "redirect:/admin/products";
	}

	@GetMapping(path = "/admin/product/update/{id}")
	public ModelAndView updateProductGet(@PathVariable Long id, Model model) {

		Product product = productService.getProductById(id);

//		ProductDTO productDTO = new ProductDTO();
//		
//		productDTO.setProductId(product.getProductId());
//		productDTO.setProductName(product.getProductName());
//		productDTO.setCategoryId(product.getProductCategory().getId());
//		productDTO.setSize(product.getSize());
//		productDTO.setPrice(product.getPrice());
//		productDTO.setDescription(product.getDescription());
//		productDTO.setImageName(product.getImageName());
		model.addAttribute("categories", productCategoryService.getAllCategories());
		model.addAttribute("product", product);
//		return "redirect:/admin/products";
//		return "addProduct";
		return new ModelAndView("updateProduct");
	}

//@PostMapping(path="/admin/product/update/{id}")
//	public String updateProductPost(@PathVariable Long id, @ModelAttribute("productDTO") ProductDTO productDTO) {
//	    Product product = productService.getProductById(id).get();
//
//	    // Update the product with the new information from the productDTO
//	    product.setProductName(productDTO.getProductName());
//	    product.setSize(productDTO.getSize());
//	    product.setPrice(productDTO.getPrice());
//	    product.setDescription(productDTO.getDescription());
//	    product.setImageName(productDTO.getImageName());
//
//	    // Update the product category
//	    ProductCategory category = productCategoryService.getCategoryById(productDTO.getCategoryId()).get();
//	    product.setProductCategory(category);
//
//	    // Save the updated product
////	    productService.saveProduct(product);
//	    productService.updateProduct(product);
//
//	    // Redirect to the product listing page
//	    return "redirect:/admin/products";
//	}
	@PostMapping(path = "/admin/product/update/{id}")
	public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/products");

		productService.updateProduct(product);

		return modelAndView;
	}

	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProductById(id);
		return "redirect:/admin/products";
	}

	// ============================================================
	// | ***********User Section**************** |
	// ============================================================

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PurchaseItemService purchaseItemService;

	@GetMapping(path = "/admin/userlist")
	public ModelAndView listOfRegisteredUsers() {
		List<User> customerList = userService.getUserList().stream()
				.filter(user -> user.getDiscriminatorValue().equals("Customer")).collect(Collectors.toList());

		ModelAndView modelAndView = new ModelAndView("registeredUserList");

		modelAndView.addObject("userList", customerList);

		return modelAndView;
	}

	@PostMapping(path = "/admin/users/search")
	public ModelAndView searchUsersList(ModelMap map, jakarta.servlet.http.HttpServletRequest request,
			@RequestParam(value = "searchId", required = false) String searchId) {

		/*
		 * ResponseEntity<List<Todo>> responseEntity = restTemplate.exchange(BASE_URL,
		 * HttpMethod.GET,null,new ParameterizedTypeReference<List<Todo>>() { });
		 */

		List<User> userList = userRepository.findByfirstNameContainingIgnoreCase(searchId).stream()
				.filter(user -> user.getDiscriminatorValue().equals("Customer")).collect(Collectors.toList());

		ModelAndView modelAndView = new ModelAndView("registeredUserList");

		modelAndView.addObject("userList", userList);

		return modelAndView;
	}

	@GetMapping(path = "/admin/purchaseReport")
	public ModelAndView purchaseReport() {

		List<PurchaseItem> purchaseItemList = purchaseItemService.getAllPurchaseItemList();

		ModelAndView modelAndView = new ModelAndView("purchaseReport");

		modelAndView.addObject("purchaseItemList", purchaseItemList);

		return modelAndView;
	}

}
