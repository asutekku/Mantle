package mantle.controllers;

import java.io.File;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import mantle.collection.Asset;
import mantle.collection.Tag;
import mantle.collection.Collection;
import mantle.collection.HandleException;

import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import mantle.util.Localization;

public class MantleController implements Initializable {

    private boolean editingInProcess = false;
    private Localization locale = new Localization("en","US");
    private ResourceBundle messages = locale.getLocale();
    private String $(String key){
        return messages.getString(key);
    }

    @FXML
    private MenuBar menubar;
    @FXML
    private Button buttonSave, buttonPlus, editButton;
    @FXML
    private TextField searchBox, _editName, _editAuthor, _editPath, _editCollection, _editFilesize, _editType, _editTags;
    @FXML
    private Text _assetName, _assetAuthor, _assetPath, _assetCollection, _assetFilesize, _assetType, _assetTags;
    private TableView<Asset> assetTable;
    @FXML
    TableColumn<Asset, String> columnType, columnName, columnPath;

    private String CollectionName = "Sample";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String macMenu = System.getProperty("apple.laf.useScreenMenuBar");

        if (macMenu != null && macMenu.equals("true")) {
            //BProot.getChildren().remove(menubar);
        }
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("filename"));
        columnPath.setCellValueFactory(new PropertyValueFactory<>("filepath"));
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
        eventHandler.OpenNewWindow(event, "about", "Mantle - " + $("About"), false);
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
        editButton.setVisible(!editButton.isVisible());
        buttonPlus.setVisible(!buttonPlus.isVisible());
        buttonSave.setVisible(!buttonSave.isVisible());
    }

    @FXML
    private void importButtonAction(ActionEvent event) {
        if (!editingInProcess) {
            String filepath = "";
            String fileSize;
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter($("filechooserAllFiles"), "*.*"));
            fileChooser.setTitle($("filechooserImportTitle"));
            File file = fileChooser.showOpenDialog(menubar.getScene().getWindow());
            if (file != null) {
                clearEditor();
                filepath = file.toString();
                if (file.length() < 1000000) {
                    fileSize = Long.toString(file.length() / 1000) + " KB";
                } else {
                    fileSize = Long.toString(file.length() / 1000000) + " MB";
                }
                _editPath.setText(filepath);
                _editFilesize.setText(fileSize);
            }
            newAssetVisibilityToggles();
            toggleEditorVisibility();
        } else {
            eventHandler.error($("errorFinishBeforeAddingNew"));
        }
    }

    @FXML
    private void newButtonAction(ActionEvent event) {
        clearEditor();
        toggleEditorVisibility();
        newAssetVisibilityToggles();
    }

    @FXML
    private void saveButtonAction(ActionEvent event) throws HandleException {
        if (newAsset()) {
            toggleEditorVisibility();
            editButton.setVisible(!editButton.isVisible());
            buttonPlus.setVisible(!buttonPlus.isVisible());
            buttonSave.setVisible(!buttonSave.isVisible());
        }
        //newAsset();
    }

    @FXML
    private void editButtonAction(ActionEvent event) {
        _assetName.setVisible(!_assetName.isVisible());
        _editName.setVisible(!_editName.isVisible());
        _assetAuthor.setVisible(!_assetAuthor.isVisible());
        _editAuthor.setVisible(!_editAuthor.isVisible());
        _assetCollection.setVisible(!_assetCollection.isVisible());
        _editCollection.setVisible(!_editCollection.isVisible());
        _assetType.setVisible(!_assetType.isVisible());
        _editType.setVisible(!_editType.isVisible());
        _assetTags.setVisible(!_assetTags.isVisible());
        _editTags.setVisible(!_editTags.isVisible());
        buttonPlus.setVisible(!buttonPlus.isVisible());

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

    private Collection collection = new Collection();

    @FXML
    private ListChooser<Asset> chooserAssets;

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
        if (asset == null) return;
        _assetName.setText(asset.getName());
        _assetAuthor.setText(asset.getAuthor());
        _assetCollection.setText(asset.getCategory());
        _assetPath.setText(asset.getPath());
        _assetFilesize.setText(asset.getSize());
        _assetTags.setText(taglist.toString());
        _assetType.setText(asset.getType());
    }

    protected boolean newAsset() throws HandleException {
        boolean success = false;
        String assetName = _editName.getText(),
                assetPath = _editPath.getText(),
                assetAuthor = _editAuthor.getText(),
                assetCategory = _editCollection.getText(),
                assetType = _editType.getText(),
                assetSize = _editFilesize.getText();
        if (assetName != null && !assetName.isEmpty()) {
            Tag newTag = new Tag(_editTags.getText());
            Asset newAsset = new Asset(assetName, assetPath, assetAuthor, assetCategory, assetType, newTag, assetSize);
            newTag.setAssetID(newAsset.getId());
            newAsset.register();
            newTag.register();
            try {
                collection.add(newAsset);
                //assetTable.getItems().add(newAsset);
            } catch (HandleException e) {
                Dialogs.showMessageDialog("Problems with creating a new asset " + e.getMessage());
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
        _editCollection.setText("");
        _editFilesize.setText("");
        _editType.setText("");
        _editTags.setText("");
    }

    public void toggleEditorVisibility() {
        _assetName.setVisible(!_assetName.isVisible());
        _editName.setVisible(!_editName.isVisible());
        _assetAuthor.setVisible(!_assetAuthor.isVisible());
        _editAuthor.setVisible(!_editAuthor.isVisible());
        _assetPath.setVisible(!_assetPath.isVisible());
        _editPath.setVisible(!_editPath.isVisible());
        _assetCollection.setVisible(!_assetCollection.isVisible());
        _editCollection.setVisible(!_editCollection.isVisible());
        _assetFilesize.setVisible(!_assetFilesize.isVisible());
        _editFilesize.setVisible(!_editFilesize.isVisible());
        _assetType.setVisible(!_assetType.isVisible());
        _editType.setVisible(!_editType.isVisible());
        _assetTags.setVisible(!_assetTags.isVisible());
        _editTags.setVisible(!_editTags.isVisible());
    }

    protected void init() {
        chooserAssets.clear();
        chooserAssets.addSelectionListener(e -> showAsset());
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }


}
