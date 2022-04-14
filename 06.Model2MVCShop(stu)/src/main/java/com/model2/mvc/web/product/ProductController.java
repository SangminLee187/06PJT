package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

//==> ȸ������ Controller
@Controller
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method ���� ����
		
	public ProductController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml ���� �Ұ�
	//==> �Ʒ��� �ΰ��� �ּ��� Ǯ�� �ǹ̸� Ȯ�� �Ұ�
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping("/addProduct.do")
	public String addProduct(@ModelAttribute("product")Product product) throws Exception{
		System.out.println("/addProduct.do");
		
		productService.addProduct(product);
		
		return "forward:/product/readProduct.jsp"; 
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct(@RequestParam("prodNo")int prodNo, Model model) throws Exception{
		
		System.out.println("/getProduct.do");
		
		Product prod = productService.getProduct(prodNo);
		
		model.addAttribute("prod", prod);
		String menu = (String) model.getAttribute("menu");
		
System.out.println("menu : "+menu);			//menu�� Ȯ��

			if(menu==null || menu.equals("")) {
				menu = "other";
				return "forward:/product/readProduct.jsp";	
			}


			if(menu.equals("manage")) {
				return "forward:/product/updateProduct.jsp";
			}
		model.addAttribute("menu", menu);
		model.addAttribute("prodNo", prodNo);
		return "forward:/product/getProduct.jsp?menu="+menu;	
	}

//	public String listProduct(@ModelAttribute("product")Product product) throws Exception{
//		System.out.println("/addProduct.do");
//		
//		productService.addProduct(product);
//		
//		return "forward:/product/readProduct.jsp"; 
//	}
//	public String updateProduct(@ModelAttribute("product")Product product) throws Exception{
//		System.out.println("/addProduct.do");
//		
//		productService.addProduct(product);
//		
//		return "forward:/product/readProduct.jsp"; 
//	}
//	public String updateProductView(@ModelAttribute("product")Product product) throws Exception{
//		System.out.println("/addProduct.do");
//		
//		productService.addProduct(product);
//		
//		return "forward:/product/readProduct.jsp"; 
//	}
	
}