import Controller.DocumentController;
import Model.QualityDocument;
import dataBase.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.DocumentRegistrationView;

import javax.swing.*;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class DocumentControllerTest {

    private DocumentRegistrationView mockView;
    private DocumentRepository mockRepo;
    private DocumentController controller;

    // Componentes necesarios para evitar NullPointerException
    private JTextField mockTitleField;
    private JTextField mockAuthorField;
    private JComboBox<String> mockCategoryCombo;
    private JTextField mockFilePathField;

    @BeforeEach
    public void setUp() {
        mockView = mock(DocumentRegistrationView.class);
        mockRepo = mock(DocumentRepository.class);

        // Simulamos botones
        when(mockView.getSaveButton()).thenReturn(new JButton());
        when(mockView.getBrowseButton()).thenReturn(new JButton());

        // Simulamos campos de texto
        mockTitleField = mock(JTextField.class);
        mockAuthorField = mock(JTextField.class);
        mockCategoryCombo = mock(JComboBox.class);
        mockFilePathField = mock(JTextField.class);

        // Simulamos getters de campos para que clearForm() no lance error
        when(mockView.getTitleField()).thenReturn(mockTitleField);
        when(mockView.getAuthorField()).thenReturn(mockAuthorField);
        when(mockView.getCategoryCombo()).thenReturn(mockCategoryCombo);
        when(mockView.getFilePathField()).thenReturn(mockFilePathField);

        // Simulamos datos de entrada del usuario
        when(mockView.getTitleInput()).thenReturn("Test Title");
        when(mockView.getAuthorInput()).thenReturn("Test Author");
        when(mockView.getCategoryInput()).thenReturn("Test Category");
        when(mockView.getFilePath()).thenReturn("/test/path");
        when(mockView.getDateInput()).thenReturn(LocalDate.now());

        controller = new DocumentController(mockView);
        controller.setRepository(mockRepo);
    }

    @Test
    public void testSaveDocument() {
        controller.callSaveDocument();

        // Verificamos que se haya guardado el documento una vez
        verify(mockRepo, times(1)).saveDocument(any(QualityDocument.class));

        // Verificamos que se haya limpiado el formulario
        verify(mockTitleField).setText("");
        verify(mockAuthorField).setText("");
        verify(mockCategoryCombo).setSelectedIndex(0);
        verify(mockFilePathField).setText("");
    }
}
