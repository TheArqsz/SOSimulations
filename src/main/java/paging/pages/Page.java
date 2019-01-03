package paging.pages;

public class Page extends BasePage {

    /**
     * The default constructor. Everything is set to 0
     */
    public Page() {

        setId(0);
        setAgeOfPage(0);
        setAmountOfUses(0);
    }

    /**
     * The constructor.
     * @param id value of id to be set to specific page
     * @param ageOfPage value of age to be set to specific page
     */
    public Page(int id, int ageOfPage) {

        setId(id);
        setAgeOfPage(ageOfPage);
        setAmountOfUses(0);
    }

    /**
     * The constructor.
     * @param id value of id to be set to specific page
     * @param ageOfPage value of age to be set to specific page
     * @param amountOfUses value of amount of uses to be set to specific page
     */
    public Page(int id, int ageOfPage, int amountOfUses) {

        setId(id);
        setAgeOfPage(ageOfPage);
        setAmountOfUses(amountOfUses);
    }
}
