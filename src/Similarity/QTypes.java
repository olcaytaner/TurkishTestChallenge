package Similarity;

public class QTypes {
    private Instances instances = new Instances();
    private String type;

    public Instances getInstances() {
        return instances;
    }

    public void setInstances(Instances instances) {
        this.instances = instances;
    }

    public QTypes() {
        this.instances = new Instances();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
