package wd.weselnedetale.converter;

import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.model.fx.WeddingSetFx;

public class WeddingSetConverter {
	
    public static WeddingSetFx convertToWeddingSetFx(WeddingSet weddingSet){
    	WeddingSetFx weddingSetFx = new WeddingSetFx();
        weddingSetFx.setId(weddingSet.getId());
        weddingSetFx.setName(weddingSet.getName());
        weddingSetFx.setId(weddingSet.getId());
        weddingSet.getWeddingSet_product().forEach(p -> {
        	Product product = p.getProduct();
        	weddingSetFx.addProduct(ProductConverter.convertToProductFx(product));
        });
        weddingSet.getWeddingSet_paper().forEach(p -> {
        	Paper paper = p.getPaper();
        	weddingSetFx.addPaper(PaperConverter.convertToPaperFx(paper));
        });
        return weddingSetFx;
    }
    
    public static WeddingSet convertToWeddingSet(WeddingSetFx weddingSetFx){
    	WeddingSet weddingSet = new WeddingSet();
    	weddingSet.setName(weddingSetFx.getName());
    	weddingSet.setId(weddingSetFx.getId());
    	
		weddingSetFx.getPaperFxObservableList().forEach(p -> {
			weddingSet.addPaper(PaperConverter.convertToPaper(p));
		});
		
		weddingSetFx.getProductFxObservableList().forEach(p -> {
			weddingSet.addProduct(ProductConverter.convertToProduct(p));
		});
    	
        return weddingSet;
    }
}
