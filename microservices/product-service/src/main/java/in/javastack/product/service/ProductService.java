package in.javastack.product.service;

import in.javastack.product.dto.ProductRequest;
import in.javastack.product.dto.ProductResponse;
import in.javastack.product.model.Product;
import in.javastack.product.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepo.save(product);
        log.info("Product created successfully !");
    }

    public List<ProductResponse> getAllProducts(){
        return productRepo.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(),
                        product.getDescription(), product.getPrice()))
                .toList();
    }
}
