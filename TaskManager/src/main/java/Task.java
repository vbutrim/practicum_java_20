/**
 * @author butrim
 */
public abstract class Task {
    private final int id;
    private final String name;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        throw new RuntimeException();
    }

    public String getName() {
        return name;
    }

    public abstract Status getStatus();

    public abstract Type getType();

    @Override
    public abstract String toString();
}
