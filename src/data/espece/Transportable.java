package data.espece;


import data.espece.faune.Animal;
import data.espece.faune.NoNeedToSendToAProductifPlace;
import data.structure.Refuge;
import gui.gestionnaire.keys.Structures;

public interface Transportable extends DomesticSpecie {
    public Structures getHomeLabel();
    public Structures getProductifPlaceLabel() throws NoNeedToSendToAProductifPlace;
    public boolean haveToBeTranposted() throws NoNeedToSendToAProductifPlace;
}
