package data.structure.hability;

/**
 * Une SlaughterHouseSender est un {@link Actionnable} qui peut envoyer des {@link Slaughtable} 
 * à l'{@link Abattoire}
 */
public interface SlaughterHouseSender extends AbleToActOnInHabitant{
    boolean isReadyToSendToSlaughterHouse();
    void removeAll();
}
