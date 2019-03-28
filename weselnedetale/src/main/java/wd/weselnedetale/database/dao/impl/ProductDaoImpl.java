package wd.weselnedetale.database.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wd.weselnedetale.database.dao.ProductDao;
import wd.weselnedetale.database.dao.WeddingSet_ProductDao;
import wd.weselnedetale.database.dao.common.CommonDaoImpl;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.database.utils.DbConnectionManager;
import wd.weselnedetale.utils.exception.ApplicationException;

@Repository
public class ProductDaoImpl extends CommonDaoImpl<Product, Integer> implements ProductDao{

	private WeddingSet_ProductDao weddingSetProductDao;
	
	@Autowired
	public ProductDaoImpl(WeddingSet_ProductDao weddingSetProductDao, DbConnectionManager dbConnectionManager) {
		super(dbConnectionManager);
		this.weddingSetProductDao = weddingSetProductDao;
	}
	
	@Override
	public void createOrUpdate(Product product) {
		try {
			super.createOrUpdate(product);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Product product) {
		try {
			super.delete(product);
			weddingSetProductDao.deleteByProduct((Product) product);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
