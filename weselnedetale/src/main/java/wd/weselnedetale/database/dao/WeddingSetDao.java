package wd.weselnedetale.database.dao;

import wd.weselnedetale.database.dao.common.CommonDao;
import wd.weselnedetale.database.model.WeddingSet;

public interface WeddingSetDao extends CommonDao<WeddingSet>{

	void initPaperCollectionIfNeeded(WeddingSet ws);

	void initProductCollectionIfNeeded(WeddingSet ws);

	void createOrUpdate(WeddingSet weddingSet);

}
