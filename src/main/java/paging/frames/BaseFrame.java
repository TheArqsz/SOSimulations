package paging.frames;

public abstract class BaseFrame {

    protected int id;

    protected int ageOfFrame;

    protected int amountOfUses;


    /**
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
}
