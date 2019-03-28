package wd.weselnedetale.database.dao.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.j256.ormlite.dao.Dao;

import wd.weselnedetale.database.dao.WeddingSetDao;
import wd.weselnedetale.database.dao.common.CommonDaoImpl;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.database.utils.DbConnectionManager;
import wd.weselnedetale.utils.exception.ApplicationException;

@Repository
public class WeddingSetDaoImpl extends CommonDaoImpl<WeddingSet, Integer> implements WeddingSetDao {

	@Autowired
	public WeddingSetDaoImpl(DbConnectionManager dbConnectionManager) {
		super(dbConnectionManager);
	}

	@Override
	public void createOrUpdate(WeddingSet weddingSet) {
		try {
			super.createOrUpdate(weddingSet);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initProductCollectionIfNeeded(WeddingSet ws) {
		if (ws.getWeddingSet_product() == null) {
			try {
				Dao<WeddingSet, Integer> weddingSetDao = getDao(WeddingSet.class);
				ws.setWeddingSet_product(weddingSetDao.getEmptyForeignCollection("WEDDINGSET_PRODUCT_ID"));
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void initPaperCollectionIfNeeded(WeddingSet ws) {
		if (ws.getWeddingSet_paper() == null) {
			try {
				Dao<WeddingSet, Integer> weddingSetDao = getDao(WeddingSet.class);
				ws.setWeddingSet_paper(weddingSetDao.getEmptyForeignCollection("WEDDINGSET_PAPER_ID"));
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void update(WeddingSet weddingSet) {
		try {
			super.update(weddingSet);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(WeddingSet weddingSet) {
		try {
			super.delete(weddingSet);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
