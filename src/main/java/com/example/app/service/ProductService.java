                                                            package com.example.app.service;

                                                            import com.example.app.model.Product;
                                                            import com.example.app.repo.ProductRecordRepo;
                                                            import com.example.app.repo.ProductRepo;
                                                            import org.slf4j.LoggerFactory;
                                                            import org.slf4j.Logger;
                                                            import org.springframework.beans.factory.annotation.Autowired;
                                                            import org.springframework.stereotype.Service;
                                                            import org.springframework.transaction.annotation.Transactional;

                                                            import java.util.ArrayList;
                                                            import java.util.Arrays;
                                                            import java.util.List;
//                                                            import java.util.logging.Logger;

                                                            @Service
                                                            public class ProductService {
                                                                private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

                                                                @Autowired
                                                                ProductRepo repo;
                                                                @Autowired
                                                                ProductRecordService productRecordService;
                                                              public List<Product> getProducts(){
                                                                  logger.info("Fetching all products...");

                                                                  return repo.findAll();

                                                              }

                                                                public Product getProductById(int prodId) {
                                                                    logger.info("Fetching product with ID: {}", prodId);

                                                                    return repo.findById(prodId).orElseGet(() -> {
                                                                        logger.warn("Product with ID {} not found", prodId);
                                                                        return new Product();
                                                                    });
                                                                }

                                                            @Transactional
                                                             public void addProduct(Product prod){
                                                                logger.info("Adding product: {}", prod);

                                                                repo.save(prod);

                                                                  //simulate exception to test transaction rollback
                                                                  if(prod.getPrice()<0){
                                                                      logger.error("Price can't be negative: {}", prod.getPrice());
                                                                      throw new IllegalArgumentException("Price can't be -ve");
                                                                  }

                                                                productRecordService.logAction(prod.getProdId(), "Added",prod.getProdName(),prod.getPrice());
                                                                logger.info("Product added successfully: {}", prod);

                                                            }

                                                            @Transactional
                                                                public void updateProduct(Product prod) {
                                                                logger.info("Updating product with ID: {}", prod.getProdId());

                                                                Product existingProd= repo.findById(prod.getProdId())
                                                                                  .orElseThrow(()-> {
                                                                                      logger.error("Product not found for update: {}", prod.getProdId());
                                                                                      return new RuntimeException("Product not Found");
                                                                                  });

                                                                  existingProd.setProdName(prod.getProdName());
                                                                  existingProd.setPrice(prod.getPrice());
                                                                  repo.save(existingProd);
                                                                logger.info("Product updated successfully: {}", existingProd);

                                                                  //to test rollback
                                                                  if(prod.getProdName().equals("Error")){
                                                                      logger.error("Simulated exception during update for product: {}", prod.getProdId());
                                                                      throw new RuntimeException("Simulated exception during update");
                                                                  }
                                                                productRecordService.logAction(prod.getProdId(), "Updated", prod.getProdName(), prod.getPrice());

                                                            }

//                                                            @Transactional
                                                                public void deleteProduct(int prodId) {
                                                                    logger.info("Deleting product with ID: {}", prodId);

                                                                    Product prod= repo.findById(prodId)
                                                                                  .orElseThrow(()->{
                                                                        logger.error("Product not found for deletion: {}", prodId);
                                                                        return new RuntimeException("Product not Found");
                                                                    });
                                                                    repo.deleteById(prodId);
                                                                    logger.info("Product deleted successfully: {}", prodId);

//                                                                    if(prodId==0)
//                                                                        throw new RuntimeException("Simulated exception during delete");
                                                                    productRecordService.logAction(prodId, "Deleted", prod.getProdName(), prod.getPrice());

                                                                }
                                                            }
