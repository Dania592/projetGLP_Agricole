package data.structure.hability;

/**
 * Une SlaughterHouseSender est un {@link Actionnable} qui peut envoyer des {@link Slaughtable} 
 * à l'{@link Abatoire}
 */
public interface SlaughterHouseSender extends AbleToActOnInHabitant{
    boolean isReadyToSendToSlaughterHouse();
    void removeAll();
}
