package mantle.util.controllers;

import java.io.File;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import mantle.collection.*;

import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;

import mantle.util.fileHelper;
import mantle.util.preferences.PreferenceLoader;

public class MantleController implements Initializable {

    @FXML
    private MenuBar menubar;
    @FXML
    private Button editorbuttonSave, editorbuttonNew, editorbuttonEdit, editorbuttonImport;
    @FXML
    private TextField searchBox, _editName, _editAuthor, _editPath, _editType, _editTags;
    @FXML
    private Text _assetName, _assetAuthor, _assetPath, _assetCategory, _assetFilesize, _assetType, _assetTags;
    @FXML
    private ImageView assetImage;
    @FXML
    private ComboBox<String> editCategoryCombo;
    @FXML
    private ListView<Asset> assetList;
    public TextField[] editorFields;
    private Text[] assetFields;
    private String CollectionName = "Sample";
    private Collection collection = new Collection();
    private Categories assetCategories = PreferenceLoader.getCategories();
    private boolean editingInProcess = false;
    private boolean additionInProcess = false;
    private ResourceBundle messages = PreferenceLoader.getLanguageBundle();


    /**
     * Function called when the application starts
     * It was used to hide the Mac menubar
     * Now it just calls the init function.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String macMenu = System.getProperty("apple.laf.useScreenMenuBar");
        /*if (macMenu != null && macMenu.equals("true")) {
            BProot.getChildren().remove(menubar);
        }*/
        init();

    }

    /**
     * Little helper to get strings from localization file
     * Made this just because I wanted the code to be more readable
     *
     * @param key String key that points to resources/localization/lang_xx_XX.properties
     *            Language is defined in the main function for now
     * @return The string in the .properties file
     */
    private String $(String key) {
        return messages.getString(key);
    }

    /**
     * Creates a new collection
     * Right now just shows the splash screen
     *
     * @param event
     */
    @FXML
    public void menuActionNew(ActionEvent event) {
        eventHandler.OpenNewWindow(event, "splash", "Mantle - New", false);
    }

    /**
     * Opens a collection to the view
     * Not yet working, just shows the filechooser
     */
    @FXML
    public void menuActionOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Mantle (.mnt)", "*.mnt"),
                new FileChooser.ExtensionFilter($("filechooserAllFiles"), "*.*"));
        fileChooser.setTitle($("filechooserOpenCollection"));
        fileChooser.showOpenDialog(menubar.getScene().getWindow());
    }

    /**
     * Saves the collection with old name
     * Not yet working
     */
    @FXML
    public void menuActionSave() {
        eventHandler.saveData("Uh oh");
    }

    /**
     * Saves the collection with custom name
     * Not yet working
     */
    @FXML
    public void menuActionSaveAs() {
        eventHandler.error($("errorNotInUse"));
    }

    /**
     * Shows the about window
     *
     * @param event
     */
    @FXML
    public void menuActionAbout(ActionEvent event) {
        eventHandler.OpenNewWindow(event, "about", "Mantle - " + $("about"), false);
    }

    /**
     * Closes the collection
     * Not yet working
     */
    @FXML
    public void menuActionClose() {
        eventHandler.error($("errorNotInUse"));
    }

    /**
     * Exits the app
     */
    @FXML
    public void menuActionQuit() {
        Platform.exit();
    }

    /**
     * Functionality for the search button
     * Works also when pressing enter on the searh bar
     * <p>
     * Uses regex to check if the input matches a number
     *
     * @param ae
     */
    @FXML
    public void searchActionEvent(ActionEvent ae) {
        String searchInput = searchBox.getText();
        int idNum = 0;
        if (searchInput.isEmpty()) {
            eventHandler.error($("errorInputSomething"));
        } else if (searchInput.matches("\\d+")) {
            idNum = Integer.parseInt(searchInput);
            search(idNum);
        } else {
            Object[] errorMsgArg = {searchInput};
            MessageFormat formatter = new MessageFormat("");
            formatter.setLocale(PreferenceLoader.getLanguageBundle().getLocale());
            formatter.applyPattern(messages.getString("errorSearchInput"));
            String output = formatter.format(errorMsgArg);
            eventHandler.error(output);
        }
    }


    /**
     * Imports a new file to the editor
     * Creates a new asset and makes it editable
     * If asset is image, then shows the image in right pane
     *
     * @param actionEvent
     */
    @FXML
    public void editorImportAction(ActionEvent actionEvent) {
        File file = fileHelper.importFile(menubar);
        String filepath;
        String fileSize;
        if (file != null) {
            filepath = fileHelper.getPath(file);
            fileSize = fileHelper.getSize(file);
            _editPath.setText(filepath);
            _assetFilesize.setText(fileSize);
            fileHelper.setImage(assetImage, filepath);
        }
    }

    /**
     * The action for pressing NEW-button
     *
     * @param event
     */
    @FXML
    private void newButtonAction(ActionEvent event) {
        editingInProcess = false;
        additionInProcess = true;
        clearAssetDisplayViewEditor();
        toggleAssetDisplayViewEditorVisibility();
        toggleAssetDisplayViewEditorButtonVisibility();
        //Adds a new asset and selects it
        search(collection.newAsset());
    }

    /**
     * Updates the asset details
     * The same function is called when you
     * save a new asset or edit and save old one
     *
     * @param event
     * @throws HandleException
     */
    @FXML
    private void saveButtonAction(ActionEvent event) throws HandleException {
        Asset asset = assetList.getSelectionModel().getSelectedItem();
        updateAsset(asset);
        toggleAssetDisplayViewEditorVisibility();
        toggleAssetDisplayViewEditorButtonVisibility();
        updateAssetDisplayView();
        //Selects the saved asset
        search(asset.getId());
    }

    /**
     * Functionality when the EDIT-button is pressed
     *
     * @param event
     */
    @FXML
    private void editButtonAction(ActionEvent event) {
        toggleAssetDisplayViewEditorVisibility();
        toggleAssetDisplayViewEditorButtonVisibility();
        editingInProcess = true;
        additionInProcess = false;
    }

    /**
     * Right now, well it does nothing
     * Should cancel the editing process for the asset
     *
     * @param actionEvent
     */
    @FXML
    public void cancelButtonAction(ActionEvent actionEvent) {
    }

    /**
     * In the future, will be used to style the app
     * to show something is being dragged to it
     *
     * @param event
     */
    @FXML
    private void mantleBaseDragDetected(DragEvent event) {
        System.out.print("Test");
    }

    /**
     * In the future, should create a new asset when file is dragged to the app
     * Right now, it does not work
     *
     * @param event
     */
    @FXML
    private void mantleBaseDragDropped(DragEvent event) {
        System.out.print("Test");
        System.out.print(DnD.mouseDragDropped(event));
    }

    @FXML
    public void mantleBaseDragEntered(DragEvent dragEvent) {
    }

    /**
     * Selects the asset by ID
     *
     * @param idNum Asset ID
     */
    protected void search(int idNum) {
        assetList.getItems().clear();
        int index = 0;
        for (int i = 0; i < collection.getAssetCount(); i++) {
            Asset asset = collection.getAsset(i);
            if (asset.getId() == idNum) index = i;
            assetList.getItems().add(asset);
        }
        assetList.getSelectionModel().select(index);
    }

    /**
     * Updates the right pane to show the asset details
     * If asset edited is null, then it breaks from the function
     */
    protected void updateAssetDisplayView() {
        Asset asset = assetList.getSelectionModel().getSelectedItem();
        String taglist = collection.getAssetTags(asset.getId());
        if (asset == null) {
            return;
        }
        _assetName.setText(asset.getName());
        _assetAuthor.setText(asset.getAuthor());
        _assetCategory.setText(asset.getCategory().toString());
        _assetPath.setText(asset.getPath());
        _assetFilesize.setText(asset.getSize());
        _assetTags.setText(taglist);
        _assetType.setText(_editType.getText());
        fileHelper.setImage(assetImage, asset.getPath());
    }

    /**
     * Updates the asset being edited
     * Used both when creating asset and modifying asset
     * <p>
     * Get's the values to be set to the asset from editable fields
     * <p>
     * When creating, modifies new empty asset
     * When editing, modifies existing asset
     *
     * @param asset Asset to be modified
     * @throws HandleException
     */
    protected void updateAsset(Asset asset) throws HandleException {
        //String errorStyle = "-fx-border-color: red ; -fx-border-width: 2px ;";
        asset.setCategory(assetCategories, editCategoryCombo);
        asset.setFilename(_editName.getText());
        asset.setFilepath(_editPath.getText());
        asset.setAuthor(_editAuthor.getText());
        asset.setType(_editType.getText());
        asset.setSize(_assetFilesize.getText());
        collection.addTag(asset, _editTags.getText());
    }

    /**
     * Clears the editor from asset's details
     */
    public void clearAssetDisplayViewEditor() {
        for (TextField field : editorFields) {
            field.setText("");
        }
        _assetFilesize.setText("");
        assetImage.setImage(null);
    }

    /**
     * Toggles visibility of elements in the left pane
     * Uses loop to hide the all elements neatly
     */
    public void toggleAssetDisplayViewEditorVisibility() {
        for (TextField field : editorFields) {
            field.setVisible(!field.isVisible());
        }
        for (Text text : assetFields) {
            text.setVisible(!text.isVisible());
        }
        editorbuttonImport.setVisible(!editorbuttonImport.isVisible());
        editCategoryCombo.setVisible(!editCategoryCombo.isVisible());
    }

    /**
     * Toggles the visibility of editor buttons
     * Due some cases, this is taken out from toggleAssetDisplayViewEditorVisibility()
     */
    public void toggleAssetDisplayViewEditorButtonVisibility() {
        editorbuttonEdit.setVisible(!editorbuttonEdit.isVisible());
        editorbuttonNew.setVisible(!editorbuttonNew.isVisible());
        editorbuttonSave.setVisible(!editorbuttonSave.isVisible());
    }

    /**
     * Init function called from initialized for cleaness
     * Listener is modified from SO
     */
    protected void init() {
        assetList.getItems().clear();
        assetList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateAssetDisplayView());
        editorFields = new TextField[]{_editName, _editAuthor, _editPath, _editType, _editTags};
        assetFields = new Text[]{_assetName, _assetAuthor, _assetPath, _assetCategory, _assetType, _assetTags};
        for (int i = 0; i < assetCategories.getCategoryArray().length; i++) {
            editCategoryCombo.getItems().add(assetCategories.getCategoryArray()[i].toString());
        }
    }

    /**
     * Sets the collection used in the UI
     *
     * @param collection
     */
    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
