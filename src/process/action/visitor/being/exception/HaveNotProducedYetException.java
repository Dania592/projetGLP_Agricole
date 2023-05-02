package process.action.visitor.being.exception;

import data.espece.characteristic.Produceur;
import data.espece.flore.terrains.Terrain;
import data.structure.hability.ProductifPlace;

public class HaveNotProducedYetException extends Exception {
    public HaveNotProducedYetException(ProductifPlace productifPlace){
        super(productifPlace+ " haven't produced yet!");
    }

    public HaveNotProducedYetException(Produceur produceur){
        super(produceur+ " haven't produced yet!");
    }

    public HaveNotProducedYetException(Terrain terrain){
        super(terrain+ " haven't produced yet!");
    }
}
