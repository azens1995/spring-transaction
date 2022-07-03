package repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.SUPPORTS)
    public void addProduct(String name) { // In REQUIRED or Default propagation, same transaction is used
        var sql = "INSERT INTO public.product(name) VALUES(?)";
        jdbcTemplate.update(sql, name);
    }
}
