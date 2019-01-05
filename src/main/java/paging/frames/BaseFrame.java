package paging.frames;

/**
 * <p>Base abstract class for all frames</p>
 * @author Arkadiusz Maruszczak
 *
 */
public abstract class BaseFrame{

    /**
     * variable that stores id of frame
     */
    protected int id;

    /**
     * variable that stores age of frame
     */
    protected int ageOfFrame;

    /**
     * variable that stores amount of uses of frame
     */
    protected int amountOfUses;


    /**
     * <p>Gets id of the frame</p>
     * @return the id of the frame
     */
    public int getId() {
        return id;
    }

    /**
     * <p>Sets id for the frame</p>
     * @param id id to be set for the frame
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * <p>Gets age of the frame</p>
     * @return the age of the frame
     */
    public int getAgeOfFrame() {
        return ageOfFrame;
    }

    /**
     * <p>Sets age for the frame</p>
     * @param ageOfFrame age to be set for the frame
     */
    public void setAgeOfFrame(int ageOfFrame) {
        this.ageOfFrame = ageOfFrame;
    }

    /**
     * <p>Gets amount of uses of the frame</p>
     * @return the amount of uses of the frame
     */
    public int getAmountOfUses() {
        return amountOfUses;
    }

    /**
     * <p>Sets amount of uses for the frame</p>
     * @param amountOfUses amount of uses to be set for the frame
     */
    public void setAmountOfUses(int amountOfUses) {
        this.amountOfUses = amountOfUses;
    }

    /**
     * Compares two Frames.
     * @param object frame that is being compared
     * @return true if objects are equal or false if objects are not equal.
     */
    @Override
    public boolean equals(Object object){
        if(object instanceof BaseFrame){
            BaseFrame toCompare = (BaseFrame) object;
            return id == toCompare.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }


}
