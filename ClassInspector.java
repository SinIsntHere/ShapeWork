import java.lang.reflect.Method;

public class ClassInspector {

    public static void printMethods(Class<?> clazz) {
        System.out.println("Methods in class: " + clazz.getSimpleName());
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Examine Model class
        printMethods(Model.class);

        // Examine GUIView class
        printMethods(View.class);

        // Examine GUIController class
        printMethods(Controller.class);
    }
}
