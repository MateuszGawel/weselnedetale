	package wd.weselnedetale.database.dao;

import wd.weselnedetale.database.model.BaseModel;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.utils.exception.ApplicationException;

public class ProductDao extends CommonDao{

	@Override
	public void createOrUpdate(BaseModel product) {
		try {
			super.createOrUpdate(product);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(BaseModel product) {
		try {
			super.delete(product);
			new WeddingSet_ProductDao().deleteByProduct((Product) product);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
