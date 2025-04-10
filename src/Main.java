import Controller.DocumentController;
import dataBase.QualityDocumentRepository;
import View.DocumentRegistrationView;

public class Main {
    public static void main(String[] args) {
        // Crear vista y repositorio
        DocumentRegistrationView registrationView = new DocumentRegistrationView();
        QualityDocumentRepository repository = new QualityDocumentRepository();

        // Crear controlador
        DocumentController controller = new DocumentController(registrationView, repository);

        // Mostrar vista principal
        registrationView.setVisible(true);
    }
}
