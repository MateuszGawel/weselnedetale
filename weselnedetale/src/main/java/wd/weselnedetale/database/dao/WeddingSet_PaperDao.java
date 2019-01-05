package wd.weselnedetale.database.dao;

import java.util.List;

import wd.weselnedetale.database.dao.common.CommonDao;
import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.database.model.WeddingSet_Paper;

public interface WeddingSet_PaperDao extends CommonDao<WeddingSet_Paper>{

	void deleteByPaper(Paper paper);

	/**
	 * @param removes all not necessary relations
	 */
	void synchronizeWith(WeddingSet ws);

	List<WeddingSet_Paper> getByWeddingSet(WeddingSet weddingSet);

}
