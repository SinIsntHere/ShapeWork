import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Abstract Model class
abstract class Model {
    protected int state;
    protected View view;

    public Model(int state, View view) {
        this.state = state;
        this.view = view;
    }

    public abstract void Update(int s);
}

// Concrete Model class
class ConcreteModel extends Model {

    public ConcreteModel(int state, View view) {
        super(state, view);
    }

    public void Update(int s) {
        this.state = s;
        System.out.println("Model state updated to: " + s);
    }
}

// Abstract View class
class View {
    private List<Model> models;

    public View() {
        this.models = new ArrayList<>();
    }

    public void Attach(Model m) {
        models.add(m);
    }

    public void Detach(Model m) {
        models.remove(m);
    }

    public void Notify(int s) {
        for (Model model : models) {
            model.Update(s);
        }
    }

    public List<Model> getModels() {
        return models;
    }
}

// Concrete View class
class ConcreteView extends View {
    private int state = 0;

    public int GetState() {
        return state;
    }

    public void SetState(int s) {
        state = s;
        System.out.println("View state set to: " + s);
        Notify(s);
    }
}

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        int max = 100;
        int min = 0;

        View v = new ConcreteView();
        Model m1 = new ConcreteModel(352, v);
        v.Attach(m1);
        for (int i = 0; i < 10; i++) {
            int temp = r.nextInt((max - min) + 1) + min;
            Model m = new ConcreteModel(temp, v);
            v.Attach(m);
        }
        List<Model> models = v.getModels();
        System.out.println("Size of models " + models.size());
        v.Detach(m1);

        System.out.println("Size of models after deletion " + models.size());

        v.Notify(max);
        ConcreteView v2 = (ConcreteView) v;
        System.out.println("Current State is " + v2.GetState());
    }
}
