package paging.pages;

public abstract class BasePage {

    protected int id;

    protected int ageOfPage;

    protected int amountOfUses;


    /**
     * @return the id of the page
     */
    public int getId() {
        return id;
    }

    /**
     * <p>Sets id for the page</p>
     * @param id id to be set for the page
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the age of the page
     */
    public int getAgeOfPage() {
        return ageOfPage;
    }

    /**
     * <p>Sets age for the page</p>
     * @param ageOfPage age to be set for the page
     */
    public void setAgeOfPage(int ageOfPage) {
        this.ageOfPage = ageOfPage;
    }

    /**
     * @return the amount of uses of the page
     */
    public int getAmountOfUses() {
        return amountOfUses;
    }

    /**
     * <p>Sets amount of uses for the page</p>
     * @param amountOfUses amount of uses to be set for the page
     */
    public void setAmountOfUses(int amountOfUses) {
        this.amountOfUses = amountOfUses;
    }
}
