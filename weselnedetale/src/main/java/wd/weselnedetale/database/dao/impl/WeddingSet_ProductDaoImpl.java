package wd.weselnedetale.database.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import wd.weselnedetale.database.dao.WeddingSetDao;
import wd.weselnedetale.database.dao.WeddingSet_ProductDao;
import wd.weselnedetale.database.dao.common.CommonDaoImpl;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.database.model.WeddingSet_Product;
import wd.weselnedetale.database.utils.DbConnectionManager;
import wd.weselnedetale.utils.exception.ApplicationException;
@Repository
public class WeddingSet_ProductDaoImpl extends CommonDaoImpl<WeddingSet_Product, Integer> implements WeddingSet_ProductDao{

	private WeddingSetDao weddingSetDao;
	
	@Autowired
	public WeddingSet_ProductDaoImpl(WeddingSetDao weddingSetDao, DbConnectionManager dbConnectionManager) {
		super(dbConnectionManager);
		this.weddingSetDao = weddingSetDao;
	}
	
	@Override
	public void deleteByProduct(Product product) {
		try {
			Dao<WeddingSet_Product, Integer> dao = getDao(WeddingSet_Product.class);
			DeleteBuilder<WeddingSet_Product, Integer> deleteBuilder = dao.deleteBuilder();
			deleteBuilder.where().eq(WeddingSet_Product.PRODUCT_ID, product.getId());
			deleteBuilder.delete();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void synchronizeWith(WeddingSet ws) {
		addRequiredProducts(ws);
		removeNotRequiredProducts(ws);
	}
	
	@Override
	public List<WeddingSet_Product> getByWeddingSet(WeddingSet weddingSet) {
		try {
			Dao<WeddingSet_Product, Integer> dao = getDao(WeddingSet_Product.class);
			return dao.queryForEq(WeddingSet_Product.WEDDINGSET_ID, weddingSet.getId());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private void addRequiredProducts(WeddingSet ws) {
		ws.getProducts().forEach(p -> {
			weddingSetDao.initProductCollectionIfNeeded(ws);
			WeddingSet_Product weddingset_product = new WeddingSet_Product();
			weddingset_product.setWeddingSet(ws);
			weddingset_product.setProduct(p);
			try {
				ws.getWeddingSet_product().add(weddingset_product);
			}
			catch(IllegalStateException e) {
				//TODO because key is unique. We dont need duplicated many to many rows. log this!
			}
		});
	}
	
	private void removeNotRequiredProducts(WeddingSet ws) {
		List<WeddingSet_Product> weddingSetProducts = getByWeddingSet(ws);
		
		weddingSetProducts.forEach(wp -> {
			if(!ws.getProducts().stream().filter(p -> p.equals(wp.getProduct())).findFirst().isPresent()) {
				try {
					deleteById(WeddingSet_Product.class, wp.getId());
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
}
