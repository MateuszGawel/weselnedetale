	package wd.weselnedetale.database.dao;

import wd.weselnedetale.database.model.BaseModel;
import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.utils.exception.ApplicationException;

public class PaperDao extends CommonDao{

	@Override
	public void createOrUpdate(BaseModel paper) {
		try {
			super.createOrUpdate(paper);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(BaseModel paper) {
		try {
			super.delete(paper);
			new WeddingSet_PaperDao().deleteByPaper((Paper) paper);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
