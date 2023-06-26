package com.webapp.demo;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webapp.demo.Component.Inventory;
import com.webapp.demo.Component.Product;
import com.webapp.demo.Repository.Barcodescannerdao;
import com.webapp.demo.Repository.Inventorydao;
import com.webapp.demo.Repository.Locationdao;
import com.webapp.demo.Repository.Productdao;
import com.webapp.demo.Repository.Purchaseorderdao;
import com.webapp.demo.Repository.Saledao;
import com.webapp.demo.Repository.Shipmentdao;

@Controller
public class AllController {

@Autowired	
Barcodescannerdao barcode;

@Autowired
Inventorydao inventory;

@Autowired
Locationdao location;

@Autowired
Productdao product;

@Autowired
Purchaseorderdao purchase;

@Autowired
Saledao sale;

@Autowired
Shipmentdao shipment;

@ResponseBody	
@RequestMapping("/")
public String summa() {
	return "varata maame durr";
}

@RequestMapping("/product")
public void product(Product p,Inventory i) {
	product.save(p);
	i.setId(p.getId());
	i.setLocation(p.getLocation());
	i.setProduct_id(p.getId());
	i.setQuantity(p.getQuantity());
  
	Calendar calendar=Calendar.getInstance();
	Date date=calendar.getTime();
	DateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
	i.setTimestamp(date);
	inventory.save(i);
	
}

@RequestMapping("delete/{id}")
public void delete(@PathVariable("id") int id) {
	Product product2=product.findById(id).orElse(null);
	product.deleteById(id);
	System.out.print(product2);
	
	inventory.deletebyLocation(product2.getLocation());
}


}
