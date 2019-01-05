package wd.weselnedetale.database.dao.common;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;

import wd.weselnedetale.database.model.BaseModel;
import wd.weselnedetale.database.utils.DbConnectionManager;
import wd.weselnedetale.utils.FxmlUtils;
import wd.weselnedetale.utils.exception.ApplicationException;

@Repository
public abstract class CommonDaoImpl<T extends BaseModel, I> implements CommonDao<T>{
	
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDaoImpl.class);
    
    private DbConnectionManager dbConnectionManager;
    
    @Autowired    
    public CommonDaoImpl(DbConnectionManager dbConnectionManager) {
		this.dbConnectionManager = dbConnectionManager;
	}

	@Override
    public void createOrUpdate(T baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate(baseModel);
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.create.update"));
        } finally {
        	dbConnectionManager.closeConnectionSource();
        }
    }
    
    @Override
    public void update(T baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.update(baseModel);
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.update"));
        } finally {
        	dbConnectionManager.closeConnectionSource();
        }
    }

    @Override
    public void refresh(T baseModel) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh(baseModel);
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.refresh"));
        } finally {
        	dbConnectionManager.closeConnectionSource();
        }
    }

    @Override
    public void delete(T baseModel) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete(baseModel);
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.delete"));
        } finally {
        	dbConnectionManager.closeConnectionSource();
        }
    }

    @Override
    public void deleteById(Class<T> cls, Object id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.delete"));
        } finally {
        	dbConnectionManager.closeConnectionSource();
        }
    }

    @Override
    public T findById(Class<T> cls, Object id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.not.found"));
        } finally {
        	dbConnectionManager.closeConnectionSource();
        }
    }

    @Override
    public List<T> queryForAll(Class<T> cls) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.not.found.all"));
        } finally {
        	dbConnectionManager.closeConnectionSource();
        }
    }

    protected Dao<T, I> getDao(Class<T> cls) throws ApplicationException {
        try {
            return DaoManager.createDao(dbConnectionManager.getConnectionSource(), cls);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.get.dao"));
        } finally {
        	dbConnectionManager.closeConnectionSource();
        }
    }

    protected QueryBuilder<T, I> getQueryBuilder(Class<T> cls) throws ApplicationException {
        Dao<T, I> dao = getDao(cls);
        return dao.queryBuilder();
    }

}
