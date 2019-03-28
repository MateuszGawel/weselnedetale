package wd.weselnedetale.database.utils;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import wd.weselnedetale.database.model.Order;
import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.database.model.Position;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.database.model.WeddingSet_Paper;
import wd.weselnedetale.database.model.WeddingSet_Product;

@Service
public class DbConnectionManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbConnectionManager.class);

	private static final String JDBC_DRIVER_HD = "jdbc:h2:./wdDB";
	private static final String USER = "admin";
	private static final String PASS = "admin";
	
	private ConnectionSource connectionSource;

	public DbConnectionManager() {
		initDatabase();
	}

	private void initDatabase() {
		createConnectionSource();
		dropTable();
		createTable();
		closeConnectionSource();
	}

	private void createConnectionSource() {
		try {
			connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD, USER, PASS);
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
	}

	public ConnectionSource getConnectionSource() {
		if (connectionSource == null) {
			createConnectionSource();
		}
		return connectionSource;
	}

	public void closeConnectionSource() {
		if (connectionSource != null) {
			try {
				connectionSource.close();
			} catch (IOException e) {
				LOGGER.warn(e.getMessage());
			}
		}
	}

	private void createTable() {
		try {
			TableUtils.createTableIfNotExists(connectionSource, WeddingSet.class);
			TableUtils.createTableIfNotExists(connectionSource, Product.class);
			TableUtils.createTableIfNotExists(connectionSource, WeddingSet_Product.class);
			TableUtils.createTableIfNotExists(connectionSource, Position.class);
			TableUtils.createTableIfNotExists(connectionSource, Paper.class);
			TableUtils.createTableIfNotExists(connectionSource, Product.class);
			TableUtils.createTableIfNotExists(connectionSource, WeddingSet_Paper.class);
			TableUtils.createTableIfNotExists(connectionSource, Order.class);
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
	}

	private void dropTable() {
		try {
			TableUtils.dropTable(connectionSource, WeddingSet.class, true);
			TableUtils.dropTable(connectionSource, Product.class, true);
			TableUtils.dropTable(connectionSource, WeddingSet_Product.class, true);
			TableUtils.dropTable(connectionSource, Position.class, true);
			TableUtils.dropTable(connectionSource, Paper.class, true);
			TableUtils.dropTable(connectionSource, Product.class, true);
			TableUtils.dropTable(connectionSource, WeddingSet_Paper.class, true);
			TableUtils.dropTable(connectionSource, Order.class, true);
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
	}
}
