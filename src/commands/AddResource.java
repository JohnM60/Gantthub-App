package commands;

import container.ResourceContainer;
import entities.Resource;

public class AddResource implements Runnable {

    private Resource resource;

    public AddResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        ResourceContainer.getInstance().add(resource);
    }

}
