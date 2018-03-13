package mantle.util.controllers;

import java.io.File;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import mantle.collection.*;
import mantle.util.Localization;

import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;

import fi.jyu.mit.fxgui.ListChooser;
import mantle.util.fileHelper;
import mantle.util.preferences.PreferenceLoader;


public class MantleController implements Initializable {

    private boolean editingInProcess = false;
    private boolean additionInProcess = false;
    private Localization locale = new Localization("en", "US");
    private ResourceBundle messages = locale.getLocale();

    private String $(String key) {
        return messages.getString(key);
    }

    @FXML
    private MenuBar menubar;
    @FXML
    private Button editorbuttonSave, editorbuttonNew, editorbuttonEdit, editorbuttonImport;
    @FXML
    private TextField searchBox, _editName, _editAuthor, _editPath, _editCategory, _editFilesize, _editTags;
    @FXML
    private Text _assetName, _assetAuthor, _assetPath, _assetCollection, _assetFilesize, _assetType, _assetTags;
    private TableView<Asset> assetTable;
    @FXML
    TableColumn<Asset, String> columnType, columnName, columnPath;
    @FXML
    private ImageView assetImage;
    private String CollectionName = "Sample";
    private Collection collection = new Collection();
    @FXML
    private ListChooser<Asset> chooserAssets;
    private Categories assetCategories = PreferenceLoader.getCategories();
    @FXML
    private ComboBox<String> editCategoryCombo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String macMenu = System.getProperty("apple.laf.useScreenMenuBar");
        /*if (macMenu != null && macMenu.equals("true")) {
            BProot.getChildren().remove(menubar);
        }*/
        init();
    }

    /**
     * Menu buttons
     */
    @FXML
    public void menuActionNew(ActionEvent event) {
        eventHandler.OpenNewWindow(event, "splash", "Mantle - New", false);
    }

    @FXML
    public void menuActionOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Mantle (.mnt)", "*.mnt"),
                new FileChooser.ExtensionFilter($("filechooserAllFiles"), "*.*"));
        fileChooser.setTitle($("filechooserOpenCollection"));
        fileChooser.showOpenDialog(menubar.getScene().getWindow());
    }

    @FXML
    public void menuActionSave() {
        eventHandler.saveData("Uh oh");
    }

    @FXML
    public void menuActionSaveAs() {
        eventHandler.error($("errorNotInUse"));
    }

    @FXML
    public void menuActionAbout(ActionEvent event) {
        eventHandler.OpenNewWindow(event, "about", "Mantle - " + $("about"), false);
    }

    @FXML
    public void menuActionClose() {
        eventHandler.error($("errorNotInUse"));
    }

    @FXML
    public void menuActionQuit() {
        Platform.exit();
    }

    @FXML
    public void onSearchEnter(ActionEvent ae) {
        String searchInput = searchBox.getText();
        if (searchInput.isEmpty()) {
            eventHandler.error($("errorInputSomething"));
        } else {
            Object[] errorMsgArg = {searchInput};
            MessageFormat formatter = new MessageFormat("");
            formatter.setLocale(locale.getCurrentLocale());
            formatter.applyPattern(messages.getString("errorSearchInput"));
            String output = formatter.format(errorMsgArg);
            eventHandler.error(output);
        }

    }

    public void newAssetVisibilityToggles() {
        editorbuttonEdit.setVisible(!editorbuttonEdit.isVisible());
        editorbuttonNew.setVisible(!editorbuttonNew.isVisible());
        editorbuttonSave.setVisible(!editorbuttonSave.isVisible());
    }

    @FXML
    private void importButtonAction(ActionEvent event) {
        if (!editingInProcess) {
            String filepath;
            String fileSize;
            File importedFile = importFile();
            if (importedFile != null) {
                clearEditor();
                filepath = fileHelper.getPath(importedFile);
                fileSize = fileHelper.getSize(importedFile);
                _editPath.setText(filepath);
                _editFilesize.setText(fileSize);
                setImage(assetImage, filepath);
            }
            newAssetVisibilityToggles();
            toggleEditorVisibility();
            editingInProcess = true;
        } else {
            eventHandler.error($("errorFinishBeforeAddingNew"));
        }
    }

    @FXML
    public void editorImportAction(ActionEvent actionEvent) {
        File file = importFile();
        String filepath;
        String fileSize;
        if (file != null) {
            filepath = fileHelper.getPath(file);
            fileSize = fileHelper.getSize(file);
            _editPath.setText(filepath);
            _assetFilesize.setText(fileSize);
            setImage(assetImage, filepath);

        }
    }

    private File importFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter($("filechooserAllFiles"), "*.*"));
        fileChooser.setTitle($("filechooserImportTitle"));
        return fileChooser.showOpenDialog(menubar.getScene().getWindow());
    }

    public void setImage(ImageView imgview, String path) {
        if (path != null && !path.isEmpty()) {
            Image image = new Image(path);
            imgview.setImage(image);
            imgview.fitWidthProperty();
        } else {
            imgview.setImage(null);
        }
    }

    @FXML
    private void newButtonAction(ActionEvent event) {
        clearEditor();
        editingInProcess = false;
        additionInProcess = true;
        toggleEditorVisibility();
        newAssetVisibilityToggles();
    }

    @FXML
    private void saveButtonAction(ActionEvent event) throws HandleException {
        if (additionInProcess && !editingInProcess) {
            if (newAsset()) {
                toggleEditorVisibility();
                editorbuttonEdit.setVisible(!editorbuttonEdit.isVisible());
                editorbuttonNew.setVisible(!editorbuttonNew.isVisible());
                editorbuttonSave.setVisible(!editorbuttonSave.isVisible());
            }
        } else if (!additionInProcess && editingInProcess) {
            Asset asset = chooserAssets.getSelectedObject();
            updateAsset(asset);
            toggleEditorVisibility();
            editorbuttonEdit.setVisible(!editorbuttonEdit.isVisible());
            editorbuttonNew.setVisible(!editorbuttonNew.isVisible());
            editorbuttonSave.setVisible(!editorbuttonSave.isVisible());
            showAsset();
        }

    }

    @FXML
    private void editButtonAction(ActionEvent event) {
        toggleEditorVisibility();
        editingInProcess = true;
        additionInProcess = false;
        editorbuttonEdit.setVisible(!editorbuttonEdit.isVisible());
        editorbuttonNew.setVisible(!editorbuttonNew.isVisible());
        editorbuttonSave.setVisible(!editorbuttonSave.isVisible());
    }

    @FXML
    public void cancelButtonAction(ActionEvent actionEvent) {
    }

    @FXML
    private void mantleBaseDragDetected(DragEvent event) {
        System.out.print("Test");
    }

    @FXML
    private void mantleBaseDragDropped(DragEvent event) {
        System.out.print("Test");
        System.out.print(DnD.mouseDragDropped(event));
    }

    @FXML
    public void mantleBaseDragEntered(DragEvent dragEvent) {
    }

    protected void search(int idNum) {
        chooserAssets.clear();
        int index = 0;
        for (int i = 0; i < collection.getAssetCount(); i++) {
            Asset asset = collection.getAsset(i);
            if (asset.getId() == idNum) index = i;
            chooserAssets.add(asset.getName(), asset);
        }
        chooserAssets.getSelectionModel().select(index);
    }

    protected void showAsset() {
        Asset asset = chooserAssets.getSelectedObject();
        Tag taglist = asset.getTags(0);
        if (asset == null) {
            return;
        }
        _assetName.setText(asset.getName());
        _assetAuthor.setText(asset.getAuthor());
        _assetCollection.setText(asset.getCategory().toString());
        _assetPath.setText(asset.getPath());
        _assetFilesize.setText(asset.getSize());
        _assetTags.setText(taglist.toString());
        _assetType.setText(asset.getCategory().toString());
        setImage(assetImage, asset.getPath());
    }

    protected void updateAsset(Asset asset) throws HandleException {
        //String errorStyle = "-fx-border-color: red ; -fx-border-width: 2px ;";
        Category assetCat;
        String assetName = _editName.getText(),
                assetPath = _editPath.getText(),
                assetAuthor = _editAuthor.getText(),
                assetType = _assetCollection.getText(),
                assetSize = _assetFilesize.getText();
        asset.setFilename(assetName);
        asset.setFilepath(assetPath);
        asset.setAuthor(assetAuthor);
        asset.setSize(assetSize);
        Tag tags = new Tag(_editTags.getText());
        tags.setAssetID(asset.getId());
        tags.register();
        asset.setTag(tags);
        for (int i = 0; i < assetCategories.getCategoryArray().length; i++) {
            if (assetCategories.getCategoryArray()[i].toString() == editCategoryCombo.getValue()) {
                assetCat = assetCategories.getCategoryArray()[i];
            } else {
                assetCat = assetCategories.getCategoryArray()[0];
            }
        }
    }

    protected boolean newAsset() throws HandleException {
        boolean success = false;
        String errorStyle = "-fx-border-color: red ; -fx-border-width: 2px ;";
        Category assetCat = null;
        String assetName = _editName.getText(),
                assetPath = _editPath.getText(),
                assetAuthor = _editAuthor.getText(),
                assetType = _assetCollection.getText(),
                assetSize = _assetFilesize.getText();
        if (assetName == null || assetName.isEmpty()) {
            _editName.setStyle(errorStyle);
        }
        if (assetPath == null || assetPath.isEmpty()) {
            _editPath.setStyle(errorStyle);
        }
        if (assetAuthor == null || assetAuthor.isEmpty()) {
            _editAuthor.setStyle(errorStyle);
        }
        if (assetName != null && !assetName.isEmpty()) {
            Tag newTag = new Tag(_editTags.getText());
            for (int i = 0; i < assetCategories.getCategoryArray().length; i++) {
                if (assetCategories.getCategoryArray()[i].toString() == editCategoryCombo.getValue()) {
                    assetCat = assetCategories.getCategoryArray()[i];
                    break;
                } else {
                    assetCat = assetCategories.getCategoryArray()[0];
                }
            }
            Asset newAsset = new Asset(assetName, assetPath, assetAuthor, assetCat, newTag, assetSize);
            newTag.setAssetID(newAsset.getId());
            newAsset.register();
            newTag.register();
            try {
                collection.add(newAsset);
            } catch (HandleException e) {
                eventHandler.error("Problems with creating a new asset " + e.getMessage());
                success = false;
            }
            search(newAsset.getId());
            success = true;
        } else {
            eventHandler.error($("errorGiveAssetName"));
            success = false;
        }
        return success;
    }

    public void clearEditor() {
        _editName.setText("");
        _editAuthor.setText("");
        _editPath.setText("");
        _editCategory.setText("");
        _editFilesize.setText("");
        _assetFilesize.setText("");
        _editTags.setText("");
        assetImage.setImage(null);
    }

    public void toggleEditorVisibility() {
        _assetName.setVisible(!_assetName.isVisible());
        _editName.setVisible(!_editName.isVisible());
        _assetPath.setVisible(!_assetPath.isVisible());
        _editPath.setVisible(!_editPath.isVisible());
        editorbuttonImport.setVisible(!editorbuttonImport.isVisible());
        _assetAuthor.setVisible(!_assetAuthor.isVisible());
        _editAuthor.setVisible(!_editAuthor.isVisible());
        _assetCollection.setVisible(!_assetCollection.isVisible());
        _editCategory.setVisible(!_editCategory.isVisible());
        _assetType.setVisible(!_assetType.isVisible());
        editCategoryCombo.setVisible(!editCategoryCombo.isVisible());
        _assetTags.setVisible(!_assetTags.isVisible());
        _editTags.setVisible(!_editTags.isVisible());
    }

    protected void init() {
        chooserAssets.clear();
        chooserAssets.addSelectionListener(e -> showAsset());
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("filename"));
        columnPath.setCellValueFactory(new PropertyValueFactory<>("filepath"));
        System.out.println(assetCategories.getCategoryArray()[1]);
        for (int i = 0; i < assetCategories.getCategoryArray().length; i++) {
            editCategoryCombo.getItems().add(assetCategories.getCategoryArray()[i].toString());
        }
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
