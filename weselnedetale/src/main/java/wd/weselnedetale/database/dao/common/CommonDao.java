package wd.weselnedetale.database.dao.common;

import java.util.List;

import wd.weselnedetale.database.model.BaseModel;
import wd.weselnedetale.utils.exception.ApplicationException;

public interface CommonDao<T extends BaseModel> {

	List<T> queryForAll(Class<T> cls) throws ApplicationException;

	T findById(Class<T> cls, Object id) throws ApplicationException;

	void deleteById(Class<T> cls, Object id) throws ApplicationException;

	void delete(T baseModel) throws ApplicationException;

	void refresh(T baseModel) throws ApplicationException;

	void update(T baseModel) throws ApplicationException;

	void createOrUpdate(T baseModel) throws ApplicationException;

}
