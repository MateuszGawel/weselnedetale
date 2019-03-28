package wd.weselnedetale.database.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wd.weselnedetale.database.dao.PositionDao;
import wd.weselnedetale.database.dao.common.CommonDaoImpl;
import wd.weselnedetale.database.model.Position;
import wd.weselnedetale.database.utils.DbConnectionManager;
import wd.weselnedetale.utils.exception.ApplicationException;

@Repository
public class PositionDaoImpl extends CommonDaoImpl<Position, Integer> implements PositionDao{

	@Autowired
	public PositionDaoImpl(DbConnectionManager dbConnectionManager) {
		super(dbConnectionManager);
	}

	@Override
	public void createOrUpdate(Position position) {
		try {
			super.createOrUpdate(position);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Position position) {
		try {
			super.delete(position);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
