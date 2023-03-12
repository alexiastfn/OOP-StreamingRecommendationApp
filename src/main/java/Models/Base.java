package Models;

abstract public class Base {
    private Integer id;
    private String name;

    public Base(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Base(Base base){
        this.id = base.id;
        this.name = base.name;
    }

    public Integer getId() {
        return id;
    }

    public Base setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Base setName(String name) {
        this.name = name;
        return this;
    }

   // public abstract Object clone();

    public abstract Base clone();
}
