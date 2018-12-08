package wd.weselnedetale.database.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import wd.weselnedetale.database.model.BaseModel;
import wd.weselnedetale.database.utils.DbManager;
import wd.weselnedetale.utils.FxmlUtils;
import wd.weselnedetale.utils.exception.ApplicationException;

public class CommonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    protected final ConnectionSource connectionSource;

    public CommonDao() {
        this.connectionSource = DbManager.getConnectionSource();
    }

    public <T extends BaseModel, I> void createOrUpdate(BaseModel baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.create.update"));
        } finally {
            this.closeDbConnection();
        }
    }
    
    public <T extends BaseModel, I> void update(BaseModel baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.update((T) baseModel);
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.update"));
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.refresh"));
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void delete(BaseModel baseModel) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.delete"));
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.delete"));
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> T findById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.not.found"));
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.not.found.all"));
        } finally {
            this.closeDbConnection();
        }
    }


    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls) throws ApplicationException {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.get.dao"));
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> QueryBuilder<T, I> getQueryBuilder(Class<T> cls) throws ApplicationException {
        Dao<T, I> dao = getDao(cls);
        return dao.queryBuilder();
    }

    private void closeDbConnection() throws ApplicationException {
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.get.dao"));
        }
    }
}
