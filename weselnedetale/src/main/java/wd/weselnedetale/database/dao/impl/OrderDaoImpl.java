package wd.weselnedetale.database.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wd.weselnedetale.database.dao.OrderDao;
import wd.weselnedetale.database.dao.common.CommonDaoImpl;
import wd.weselnedetale.database.model.Order;
import wd.weselnedetale.database.utils.DbConnectionManager;
import wd.weselnedetale.utils.exception.ApplicationException;

@Repository
public class OrderDaoImpl extends CommonDaoImpl<Order, Integer> implements OrderDao{

	@Autowired
	public OrderDaoImpl(DbConnectionManager dbConnectionManager) {
		super(dbConnectionManager);
	}

	@Override
	public void createOrUpdate(Order order) {
		try {
			super.createOrUpdate(order);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Order order) {
		try {
			super.delete(order);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
