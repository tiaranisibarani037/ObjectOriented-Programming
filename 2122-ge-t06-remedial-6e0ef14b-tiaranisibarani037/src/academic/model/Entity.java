package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 */

public abstract class Entity implements EntityInterface {
    protected String id;
    protected String name;

    // constructor
    public Entity() {
    }

    public Entity(String _id, String _name) {
        this.id = _id;
        this.name = _name;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

