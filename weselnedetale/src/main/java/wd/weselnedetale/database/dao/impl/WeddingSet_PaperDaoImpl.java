package wd.weselnedetale.database.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import wd.weselnedetale.database.dao.WeddingSetDao;
import wd.weselnedetale.database.dao.WeddingSet_PaperDao;
import wd.weselnedetale.database.dao.common.CommonDaoImpl;
import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.database.model.WeddingSet_Paper;
import wd.weselnedetale.database.utils.DbConnectionManager;
import wd.weselnedetale.utils.exception.ApplicationException;

@Repository
public class WeddingSet_PaperDaoImpl extends CommonDaoImpl<WeddingSet_Paper, Integer> implements WeddingSet_PaperDao{

	private WeddingSetDao weddingSetDao;
	
	@Autowired
	public WeddingSet_PaperDaoImpl(WeddingSetDao weddingSetDao, DbConnectionManager dbConnectionManager) {
		super(dbConnectionManager);
		this.weddingSetDao = weddingSetDao;
	}

	@Override
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
	
	@Override
	public void synchronizeWith(WeddingSet ws) {
		addRequiredPapers(ws);
		removeNotRequiredPapers(ws);
	}
	
	@Override
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

	private void addRequiredPapers(WeddingSet ws) {
		ws.getPapers().forEach(p -> {
			weddingSetDao.initPaperCollectionIfNeeded(ws);
			
			WeddingSet_Paper weddingset_paper = new WeddingSet_Paper();
			weddingset_paper.setWeddingSet(ws);
			weddingset_paper.setPaper(p);
			try {
				ws.getWeddingSet_paper().add(weddingset_paper);
			}
			catch(IllegalStateException e) {
				//TODO because key is unique. We dont need duplicated many to many rows. log this!
			} 
		});
	}

	private void removeNotRequiredPapers(WeddingSet weddingSet) {
		List<WeddingSet_Paper> weddingSetPapers = getByWeddingSet(weddingSet);
		
		weddingSetPapers.forEach(wp -> {
			if(!weddingSet.getPapers().stream().filter(p -> p.equals(wp.getPaper())).findFirst().isPresent()) {
				try {
					deleteById(WeddingSet_Paper.class, wp.getId());
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
