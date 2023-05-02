package data.structure.hability;

/**
 * Un HealablePlace est un {@link Actionnable} qui peut être soigné
 */

public interface Hydratable extends AbleToActOnInHabitant{
    boolean isNeedToBeHydrated(); 
}