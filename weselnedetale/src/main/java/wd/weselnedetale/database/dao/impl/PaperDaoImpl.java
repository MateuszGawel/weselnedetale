package wd.weselnedetale.database.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wd.weselnedetale.database.dao.PaperDao;
import wd.weselnedetale.database.dao.WeddingSet_PaperDao;
import wd.weselnedetale.database.dao.common.CommonDaoImpl;
import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.database.utils.DbConnectionManager;
import wd.weselnedetale.utils.exception.ApplicationException;

@Repository
public class PaperDaoImpl extends CommonDaoImpl<Paper, Integer> implements PaperDao {
	
	private WeddingSet_PaperDao weddingSetPaperDao;
	
	@Autowired
	public PaperDaoImpl(WeddingSet_PaperDao weddingSetPaperDao, DbConnectionManager dbConnectionManager) {
		super(dbConnectionManager);
		this.weddingSetPaperDao = weddingSetPaperDao;
	}
	
	@Override
	public void createOrUpdate(Paper paper) {
		try {
			super.createOrUpdate(paper);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Paper paper) {
		try {
			super.delete(paper);
			weddingSetPaperDao.deleteByPaper((Paper) paper);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
