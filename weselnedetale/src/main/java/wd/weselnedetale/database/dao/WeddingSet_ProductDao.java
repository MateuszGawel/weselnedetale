package wd.weselnedetale.database.dao;

import java.util.List;

import wd.weselnedetale.database.dao.common.CommonDao;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.database.model.WeddingSet_Product;

public interface WeddingSet_ProductDao extends CommonDao<WeddingSet_Product>{

	void deleteByProduct(Product product);

	/**
	 * @param removes all not necessary relations
	 */
	void synchronizeWith(WeddingSet ws);

	List<WeddingSet_Product> getByWeddingSet(WeddingSet weddingSet);
}
