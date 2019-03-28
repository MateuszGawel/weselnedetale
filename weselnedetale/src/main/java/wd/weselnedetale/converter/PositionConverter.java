package wd.weselnedetale.converter;

import wd.weselnedetale.database.model.Position;
import wd.weselnedetale.model.fx.PositionFx;

public class PositionConverter {
    public static PositionFx convertToPositionFx(Position position){
    	PositionFx positionFx = new PositionFx();
    	positionFx.setId(position.getId());
    	positionFx.setAmount(position.getAmount());
    	positionFx.setName(position.getName());
    	if(position.getPaper() != null) {
    		positionFx.setPaper(PaperConverter.convertToPaperFx(position.getPaper()));
    	}
    	positionFx.setProduct(ProductConverter.convertToProductFx(position.getProduct()));
    	positionFx.setWeddingSet(WeddingSetConverter.convertToWeddingSetFx(position.getWeddingSet()));
        return positionFx;
    }
    
    public static Position convertToPosition(PositionFx positionFx){
    	Position position = new Position();
    	position.setId(positionFx.getId());
    	position.setAmount(positionFx.getAmount());
    	position.setName(positionFx.getName());
    	if(positionFx.getPaper() != null) {
    		position.setPaper(PaperConverter.convertToPaper(positionFx.getPaper()));
    	}
    	position.setProduct(ProductConverter.convertToProduct(positionFx.getProduct()));
    	position.setWeddingSet(WeddingSetConverter.convertToWeddingSet(positionFx.getWeddingSet()));
        return position;
    }
}
