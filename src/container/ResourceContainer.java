package container;

import entities.Resource;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

/**
 * A container for all the resources associated with a project.
 * Implements Singleton pattern
 */
public class ResourceContainer {

    private static ResourceContainer instance;

    Map<Integer, Resource> resources;
    private ResourceContainer()
    {
        resources = new Hashtable<>();
    }

    public static ResourceContainer getInstance()
    {
        if( null == instance )
        {
            instance = new ResourceContainer();
        }
        return instance;
    }

    public Resource getResourceById(int id)
    {
        return resources.get(id);
    }


    public Resource removeResourceById(int id)
    {
        return resources.remove(id);
    }

    public void add(Resource res)
    {
        resources.put(res.getId(), res);
    }

    public ArrayList<Resource> getAllProjectResource()
    {
        ArrayList<Resource> list = new ArrayList<>();

        for ( Map.Entry<Integer, Resource> entry : resources.entrySet() )
        {
            list.add(entry.getValue());
        }

        return list;
    }

    public static void main(String[] args) {
        //test getInstance() with no instances
        ResourceContainer r = new ResourceContainer();
        if (getInstance() == null) {
            System.out.println("Error in getInstance(), resource container is not initialized");
        }
        //test getInstance() with an instance initialized
        if (getInstance() == null) {
            System.out.print("Error in getInstance(), resource container is not initialized");
        }
        //test getResourceById with no resources
        if (r.getResourceById(0) != null) {
            System.out.println("Error in getResourceById(), resource should be null in an empty container");
        }
        //test getResourceById with a resource
        r.add(new Resource("a", 1, 1, 1, "a"));
        if (!r.getResourceById(1).getTitle().equals("a")) {
            System.out.println("Error in getResourceById(), incorrect resource or no resource found");
        }
        //test getResourceById with an incorrect id
        try {
            r.getResourceById(2).getTitle();
            System.out.println("Error in getResourceById(), exception expected with an incorrect ID");
        } catch (NullPointerException e) {
        } catch (Exception e) {
            System.out.println("Error in getResourceById(), different exception occurred with an incorrect ID");
        }
        //test getResourceById with multiple resources
        r.add(new Resource("b", 2, 1, 1, "a"));
        if (!r.getResourceById(2).getTitle().equals("b")) {
            System.out.println("Error in getResourceById(), incorrect resource found");
        }
        //test removeResourceById with multiple resources
        r.removeResourceById(2);
        if (r.getResourceById(2) != null) {
            System.out.println("Error in removeResourceById(), resource not removed");
        }
        //test removeResourceById with an incorrect id
        r.removeResourceById(9);
        if (r.getResourceById(9) != null) {
            System.out.println("Error in removeResourceById(), resource not removed");
        }
        //test removeResourceById with no resources
        r.removeResourceById(1);
        r.removeResourceById(0);
        if (r.getResourceById(0) != null) {
            System.out.println("Error in removeResourceById(), all resources should be removed");
        }
        //test add with no resources
        r.add(new Resource("c", 2, 3, 4, "c"));
        if (!r.getResourceById(2).getTitle().equals("c")) {
            System.out.println("Error in add(), resource not added to the container");
        }
        //test add with a resource
        r.add(new Resource("d", 3, 4, 2, "d"));
        if (!r.getResourceById(3).getTitle().equals("d")) {
            System.out.println("Error in add(), incorrect resource or no resource added");
        }
        System.out.println("Testing complete");
    }
}
