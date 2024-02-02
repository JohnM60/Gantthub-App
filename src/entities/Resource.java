package entities;

public class Resource {

    //region Private Properties
    /**
     * name of resource
     */
    String title;

    /**
     * unique id
     */
    int id;

    /**
     * cost of procurement i.e. how much money do we need to pay to get this
     */
    int cost;

    /**
     * hourly cost of using this resource (e.x. gas, staffing, etc.)
     */
    int useCost;

    /**
     * description of the resource
     */
    String description;
    //endregion


    //region Constructor

    public Resource(String title, int id, int cost, int useCost, String description) {
        this.title = title;
        this.id = id;
        this.cost = cost;
        this.useCost = useCost;
        this.description = description;
    }
    //endregion

    //region Getter

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public int getHourlyRuntimeCost() {
        return useCost;
    }

    public String getDescription() {
        return description;
    }

    //endregion
    public static void main(String[] args) {
        //test getTitle with a resource
        Resource r = new Resource("a", 2, 3, 4, "b");
        if (!r.getTitle().equals("a")) {
            System.out.println("Error in getTitle(), incorrect resource title or none returned");
        }
        //test getId
        if (r.getId() != 2) {
            System.out.println("Error in getId(), incorrect id or no id returned");
        }
        //test getCost
        if (r.getCost() != 3) {
            System.out.println("Error in getCost(), incorrect cost or no cost returned");
        }
        //test getHourlyRuntimeCost
        if (r.getHourlyRuntimeCost() != 4) {
            System.out.println("Error in getHourlyRuntimeCost(), incorrect cost or no cost returned");
        }
        //test getDescription
        if (!r.getDescription().equals("b")) {
            System.out.println("Error in getDescription(), incorrect description or no description returned");
        }
        System.out.println("Testing complete");
    }
}
