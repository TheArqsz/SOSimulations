package paging.frames;

public class Frame extends BaseFrame {

    /**
     * The default constructor. Everything is set to 0
     */
    public Frame() {

        setId(0);
        setAgeOfFrame(0);
        setAmountOfUses(0);
    }

    /**
     * The constructor.
     * @param id value of id to be set to specific frame
     * @param ageOfFrame value of age to be set to specific Frame
     */
    public Frame(int id, int ageOfFrame) {

        setId(id);
        setAgeOfFrame(ageOfFrame);
        setAmountOfUses(0);
    }

    /**
     * The constructor.
     * @param id value of id to be set to specific frame
     * @param ageOfFrame value of age to be set to specific frame
     * @param amountOfUses value of amount of uses to be set to specific frame
     */
    public Frame(int id, int ageOfFrame, int amountOfUses) {

        setId(id);
        setAgeOfFrame(ageOfFrame);
        setAmountOfUses(amountOfUses);
    }
}
