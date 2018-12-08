package wd.weselnedetale.database.dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.database.model.WeddingSet_Product;
import wd.weselnedetale.utils.exception.ApplicationException;

public class WeddingSet_ProductDao extends CommonDao{

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

}
