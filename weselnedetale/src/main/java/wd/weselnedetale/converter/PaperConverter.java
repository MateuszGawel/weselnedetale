package wd.weselnedetale.converter;

import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.model.fx.PaperFx;

public class PaperConverter {
    public static PaperFx convertToPaperFx(Paper paper){
    	PaperFx paperFx = new PaperFx();
        paperFx.setId(paper.getId());
        paperFx.setName(paper.getName());
        paperFx.setWidth(paper.getWidth());
        paperFx.setHeight(paper.getHeight());
        paperFx.setPrice(paper.getPrice());
        return paperFx;
    }
    public static Paper convertToPaper(PaperFx paperFx){
    	Paper paper = new Paper();
    	paper.setId(paperFx.getId());
    	paper.setName(paperFx.getName());
    	paper.setWidth(paperFx.getWidth());
    	paper.setHeight(paperFx.getHeight());
    	paper.setPrice(paperFx.getPrice());
        return paper;
    }
}
