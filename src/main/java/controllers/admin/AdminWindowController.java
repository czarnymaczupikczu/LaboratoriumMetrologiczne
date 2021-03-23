package controllers.admin;

import controllers.LoginWindowController;
import controllers.MainWindowController;
import dbModels.YearModel;
import dbModels.instrument.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.FxmlTools;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import static dbModels.YearModel.YEAR;
import static dbModels.instrument.NameModel.INSTRUMENT_NAME;
import static dbModels.instrument.ProducerModel.PRODUCER_NAME;
import static dbModels.instrument.RangeModel.RANGE_NAME;
import static dbModels.instrument.TypeModel.TYPE_NAME;
import static dbModels.instrument.UnitModel.UNIT_NAME;

public class AdminWindowController {
    public AdminWindowController(){
        System.out.println("Konstruktor klasy AdminWindowController");
    }

    private MainWindowController mainController;
    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }

    private CommonWindowController commonWindowController;
    private UserWindowController userWindowController;


    private final String COMMON_WINDOW="/fxml/admin/CommonWindow.fxml";
    private final String USERS_WINDOW="/fxml/admin/UsersWindow.fxml";

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera AdminWindowController ");
    }


    @FXML
    private VBox adminWindowVBox;


    @FXML
    void nameEdit() {
        this.commonWindowController= FxmlTools.openVBoxWindow(COMMON_WINDOW);
        this.commonWindowController.getCommonDataModel().setCls(NameModel.class);
        this.commonWindowController.getCommonDataModel().init();
        this.commonWindowController.setLabel("Nazwy przyrządów");
        this.commonWindowController.getCommonDataModel().setColumnNameEdit(INSTRUMENT_NAME);
        this.commonWindowController.getCommonDataModel().setColumnNameDelete("name");
    }

    @FXML
    void producerEdit() {
        this.commonWindowController= FxmlTools.openVBoxWindow(COMMON_WINDOW);
        this.commonWindowController.getCommonDataModel().setCls(ProducerModel.class);
        this.commonWindowController.getCommonDataModel().init();
        this.commonWindowController.setLabel("Producenci przyrządów");
        this.commonWindowController.getCommonDataModel().setColumnNameEdit(PRODUCER_NAME);
        this.commonWindowController.getCommonDataModel().setColumnNameDelete("producer");
    }

    @FXML
    void rangeEdit() {
        this.commonWindowController= FxmlTools.openVBoxWindow(COMMON_WINDOW);
        this.commonWindowController.getCommonDataModel().setCls(RangeModel.class);
        this.commonWindowController.getCommonDataModel().init();
        this.commonWindowController.setLabel("Zakresy przyrządów");
        this.commonWindowController.getCommonDataModel().setColumnNameEdit(RANGE_NAME);
        this.commonWindowController.getCommonDataModel().setColumnNameDelete("range");
    }

    @FXML
    void typeEdit() {
        this.commonWindowController= FxmlTools.openVBoxWindow(COMMON_WINDOW);
        this.commonWindowController.getCommonDataModel().setCls(TypeModel.class);
        this.commonWindowController.getCommonDataModel().init();
        this.commonWindowController.setLabel("Typy przyrządów");
        this.commonWindowController.getCommonDataModel().setColumnNameEdit(TYPE_NAME);
        this.commonWindowController.getCommonDataModel().setColumnNameDelete("type");
    }

    @FXML
    void unitEdit() {
        this.commonWindowController= FxmlTools.openVBoxWindow(COMMON_WINDOW);
        this.commonWindowController.getCommonDataModel().setCls(UnitModel.class);
        this.commonWindowController.getCommonDataModel().init();
        this.commonWindowController.setLabel("Jednostki");
        this.commonWindowController.getCommonDataModel().setColumnNameEdit(UNIT_NAME);
        this.commonWindowController.getCommonDataModel().setColumnNameDelete("rangeName");
    }

    @FXML
    void userEdit() {
        this.userWindowController=FxmlTools.openVBoxWindow(USERS_WINDOW);
        this.userWindowController.getUserDataModel().init();
    }

    @FXML
    void yearEdit() {
        this.commonWindowController= FxmlTools.openVBoxWindow(COMMON_WINDOW);
        this.commonWindowController.getCommonDataModel().setCls(YearModel.class);
        this.commonWindowController.getCommonDataModel().init();
        this.commonWindowController.setLabel("Lata");
        this.commonWindowController.getCommonDataModel().setColumnNameEdit(YEAR);
        this.commonWindowController.getCommonDataModel().setColumnNameDelete("cardNumber");
    }

    @FXML
    private void copyDataBase()  {
        //File source = new File("C:/db/baza1.db");
        File source = new File("baza.db");
        Format formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = formatter.format(new Date());
        fileName+=".db";
        //File dest = new File("C:/KOPIE CM/KOPIE/Bazy danych/"+fileName);
        File dest = new File("C:/KOPIE CM/"+fileName);
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            CommonTools.displayAlert(e.getMessage());
        } catch (IOException e) {
            CommonTools.displayAlert(e.getMessage());
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                CommonTools.displayAlert(e.getMessage());
            }

        }
    }
}
