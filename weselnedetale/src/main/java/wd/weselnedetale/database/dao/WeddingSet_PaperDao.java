package wd.weselnedetale.database.dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.database.model.WeddingSet_Paper;
import wd.weselnedetale.utils.exception.ApplicationException;

public class WeddingSet_PaperDao extends CommonDao{

	public void deleteByPaper(Paper paper) {
		try {
			Dao<WeddingSet_Paper, Integer> dao = getDao(WeddingSet_Paper.class);
			DeleteBuilder<WeddingSet_Paper, Integer> deleteBuilder = dao.deleteBuilder();
			deleteBuilder.where().eq(WeddingSet_Paper.PAPER_ID, paper.getId());
			deleteBuilder.delete();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<WeddingSet_Paper> getByWeddingSet(WeddingSet weddingSet) {
		try {
			Dao<WeddingSet_Paper, Integer> dao = getDao(WeddingSet_Paper.class);
			return dao.queryForEq(WeddingSet_Paper.WEDDINGSET_ID, weddingSet.getId());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	


}
