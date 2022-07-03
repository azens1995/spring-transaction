package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addTenProduct() { // Transaction is created here
        for (int i = 0; i<10; i++) {
            productRepository.addProduct("Product-" + i);
//            if (i == 5) {
//                throw new RuntimeException(":(");
//            }
        }
    } // commits the transaction

}
