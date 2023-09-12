package com.mongodb.teste.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.mongodb.teste.demo.model.Product;
import com.mongodb.teste.demo.model.Supplier;
import com.mongodb.teste.demo.repo.ProductRepository;
import com.mongodb.teste.demo.service.interfaces.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String save(Product product) {

        return this.productRepository.save(product).getProductId();

    }

    @Override
    public List<Product> listAll() {

        return this.productRepository.findAll();

    }

    @Override
    public Page<Product> search(String name, Integer quantity, Double price, Pageable pageable) {

        Query query = new Query().with(pageable);

        List<Criteria> criterias = new ArrayList<>();

        if(name != null && !name.isEmpty()){
            criterias.add(Criteria.where("name").regex(name, "i"));
        }

        if(quantity != null){
            criterias.add(Criteria.where("quantity").gte(0));
        }

        if(price != null){
            criterias.add(Criteria.where("price").gte(0.0));
        }

        Page<Product> products = PageableExecutionUtils.getPage(
            mongoTemplate.find(query, Product.class),
             pageable, () -> this.mongoTemplate.count(query.skip(0).limit(0), Product.class));

        return products;

    }

    @Override
    public String delete(String id) {

        this.productRepository.deleteById(id);

        return "Removido [ " + id + " ]";

    }

    @Override
    public Product findByid(String id) {
        return this.productRepository.findById(id).get();
    }

    @Override
    public String attProduct(String id, Product novo) {

        Product productOld = this.productRepository.findById(id).get();

        attCampos(productOld, novo);

        return this.productRepository.save(productOld).getProductId();

    }

    private void attCampos(Product velho, Product novo){
        
        if(novo.getName() != null && !novo.getName().isEmpty() && !novo.equals("string")){
            velho.setName(novo.getName());
        }

        if(novo.getObs() != null && !novo.getObs().isEmpty() && !novo.equals("string")){
            velho.setObs(novo.getObs());
        }

        if(novo.getPrice() != null && novo.getPrice() > 0.0){
            velho.setPrice(novo.getPrice());
        }

        if(novo.getQuantity() != null && novo.getQuantity() > 0){
            velho.setQuantity(novo.getQuantity());
        }

    }

    @Override
    public String addSupplier(String id, Supplier supplier) {

        Product productOld = this.productRepository.findById(id).get();

        productOld.getSuppliers().add(supplier);

        return this.productRepository.save(productOld).getProductId();

    }

    @Override
    public String attSupplier(String id, Supplier supplier) {

        Query query = new Query(Criteria.where("productId").is(id).and("suppliers.ZIP_CODE").is(supplier.getZIP_CODE()));

        Update update = new Update()
            .set("suppliers.$.name", supplier.getName())
            .set("suppliers.$.address", supplier.getAddress())
            .set("suppliers.$.city", supplier.getCity())
            .set("suppliers.$.deliverDate", supplier.getDeliverDate());

        this.mongoTemplate.updateFirst(query, update, Product.class);

        return "Att !";

    }

    @Override
    public List<Product> listByPrice(Double minPrice, Double maxPrice) {
        return this.productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public String deleteSupplier(String id, String zip_code) {
        Product product = this.productRepository.findById(id).get();
        List<Supplier> suppliers = product.getSuppliers();
        suppliers.removeIf(supplier -> supplier.getZIP_CODE().equals(zip_code));
        return this.save(product);
    }
    
}
