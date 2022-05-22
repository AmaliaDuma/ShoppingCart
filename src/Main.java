import controller.Controller;
import model.catalog.Catalog;
import view.Console;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(new Catalog());
        Console console = new Console(controller);
        console.run();
    }
}
