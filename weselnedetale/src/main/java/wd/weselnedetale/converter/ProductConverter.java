package wd.weselnedetale.converter;

import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.model.fx.ProductFx;

public class ProductConverter {
    public static ProductFx convertToProductFx(Product product){
    	ProductFx productFx = new ProductFx();
        productFx.setId(product.getId());
        productFx.setName(product.getName());
        productFx.setPaperProduct(product.isPaperProduct());
        productFx.setPrinted(product.isPrinted());
        productFx.setWidth(product.getWidth());
        productFx.setHeight(product.getHeight());
        productFx.setPrice(product.getPrice());
        return productFx;
    }
    public static Product convertToProduct(ProductFx productFx){
    	Product product = new Product();
    	product.setId(productFx.getId());
    	product.setName(productFx.getName());
    	product.setPaperProduct(productFx.isPaperProduct());
    	product.setPrinted(productFx.isPrinted());
    	product.setWidth(productFx.getWidth());
    	product.setHeight(productFx.getHeight());
    	product.setPrice(productFx.getPrice());
        return product;
    }
}
