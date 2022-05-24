module ahsan.quizapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens ahsan.quizapplication to javafx.fxml;
    exports ahsan.quizapplication;
    exports ahsan.quizapplication.Controllers;
    opens ahsan.quizapplication.Controllers to javafx.fxml;
}