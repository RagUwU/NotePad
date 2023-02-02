// import javax.swing.*;
// import javax.swing.filechooser.*;

// import org.omg.PortableInterceptor.INACTIVE;

// import java.awt.event.*;
// import java.util.*;
// import java.io.*;
// /* 
//     TODO:
//     Make the whole Structure
//     Make it work 
//     Add file handling
//     Add Event handling
//     Add Font Editor
//     Add Memory of previous window (Opened files, theme, which one was active)
//     Add Light/Dark Theme


//     Add a saved or not saved system

//     Settings get most recent foler

//     Création d’un document. 10pts Done
//     Sauvegarde d’un document. 10pts Done
//     Ouverture d’un document. 10pts Done
//     Édition d’un document. 5pts Done
//     Un système d’onglet pour ouvrir plusieurs fichiers à la fois. 15pts Done
//     Un menu pour l’exécution des fonctions. 15pts Done
//     Une fenêtre de recherche de texte. 10pts 
//     Une fenêtre de remplacement de texte. 10ps 
//     Une fenêtre d’information sur le logiciel (À propos). 5pts 
//     Un système de Undo/Redo. 10pts

// */
// public class NotePad extends JFrame {

//     // Variables Pane
//     JTabbedPane tabbedPane;
//     ArrayList<EditorPaneFile> editorPaneFiles;
//     // Variables Menu
//     JMenuBar menuBar;
//     NotePadMenu menuFile;
//     ArrayList<JMenuItem> menuFileItems;
//     NotePadMenu menuEdit;
//     ArrayList<JMenuItem> menuEditItems;
//     NotePadMenu menuTools;
//     ArrayList<JMenuItem> menuToolsItems;
//     NotePadMenu menuHelp;
//     ArrayList<JMenuItem> menuHelpItems;
//     // Settings variables
//     String previousDirectory;

//     public NotePad(String title, int width, int height){
//         // Window initialisation 
//         this.setTitle(title);
//         this.setSize(width, height);
//         this.setLocationRelativeTo(null);
//         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         initData();

//         addComponents();
//         createMenu();

//         addListeners();

//         this.setVisible(true);
//     }

//     public void addComponents(){
//         tabbedPane = new JTabbedPane();
//         editorPaneFiles = new ArrayList<EditorPaneFile>();
//         // If there's no editorPanes from the memory, it will create a new one called untitled
//         if(editorPaneFiles.size() == 0){
//             editorPaneFiles.add(new EditorPaneFile("Untitled*"));
//         }

//         for(EditorPaneFile editorPane : editorPaneFiles){
//             editorPane.addToTabbedPane(tabbedPane);
//         }

//         this.add(tabbedPane);
//     }

//     public void createMenu(){
//         // Menu Bar
//         menuBar = new JMenuBar();
//         // Menu File
//         menuFileItems = new ArrayList<JMenuItem>();
//             menuFileItems.add(new JMenuItem("New"));
//             menuFileItems.add(new JMenuItem("Open"));
//             menuFileItems.add(new JMenuItem("Open Folder"));
//             // menuFileItems.add(new JMenuItem("Rename"));
//             // menuFileItems.add(new JMenuItem("Delete"));
//             menuFileItems.add(new JMenuItem("%separator%"));
//             menuFileItems.add(new JMenuItem("Save"));
//             menuFileItems.add(new JMenuItem("Save As"));
//             menuFileItems.add(new JMenuItem("%separator%"));
//             menuFileItems.add(new JMenuItem("Close"));
//             menuFileItems.add(new JMenuItem("Close All"));
//             menuFileItems.add(new JMenuItem("Quit"));
//         menuFile = new NotePadMenu(new JMenu("File"), menuFileItems);
//         menuFile.addItems();
//         menuFile.addToBar(menuBar);
//         // Menu Edit
//         menuEditItems = new ArrayList<JMenuItem>();
//             menuEditItems.add(new JMenuItem("Undo"));
//             menuEditItems.add(new JMenuItem("Redo"));
//             menuEditItems.add(new JMenuItem("%separator%"));
//             menuEditItems.add(new JMenuItem("Copy"));
//             menuEditItems.add(new JMenuItem("Cut"));
//             menuEditItems.add(new JMenuItem("Paste"));
//             menuEditItems.add(new JMenuItem("%separator%"));
//             menuEditItems.add(new JMenuItem("Font"));
//         menuEdit = new NotePadMenu(new JMenu("Edit"), menuEditItems);
//         menuEdit.addItems();
//         menuEdit.addToBar(menuBar);
//         // Menu Tools
//         menuToolsItems = new ArrayList<JMenuItem>();
//             menuToolsItems.add(new JMenuItem("Search"));
//             menuToolsItems.add(new JMenuItem("Search and replace"));
//             menuToolsItems.add(new JMenuItem("%separator%"));
//             menuToolsItems.add(new JMenuItem("Theme"));
//         menuTools = new NotePadMenu(new JMenu("Tools"), menuToolsItems);
//         menuTools.addItems();
//         menuTools.addToBar(menuBar);
//         // Menu help
//         menuHelpItems = new ArrayList<JMenuItem>();
//             menuHelpItems.add(new JMenuItem("About"));
//         menuHelp = new NotePadMenu(new JMenu("Help"), menuHelpItems);
//         menuHelp.addItems();
//         menuHelp.addToBar(menuBar);

//         this.setJMenuBar(menuBar);
        
//         // menuItemOuvrir.addActionListener(new OuvrirFichierListener());
//         // menuItemOuvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
//     }

//     public void initData(){
//     }

//     public void addListeners(){
//         // MenuFile
//         menuFile.getMenuItem("New").addActionListener(new NewFileListener());
//         menuFile.getMenuItem("New").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
//         menuFile.getMenuItem("Open").addActionListener(new OpenFileListener());
//         menuFile.getMenuItem("Open").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
//         menuFile.getMenuItem("Open Folder").addActionListener(new OpenFolderListener());
//         menuFile.getMenuItem("Open Folder").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
//         menuFile.getMenuItem("Save").addActionListener(new SaveListener());
//         menuFile.getMenuItem("Save").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
//         menuFile.getMenuItem("Save As").addActionListener(new SaveAsListener());
//         menuFile.getMenuItem("Save As").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
//         menuFile.getMenuItem("Close").addActionListener(new CloseListener());
//         menuFile.getMenuItem("Close").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
//         menuFile.getMenuItem("Close All").addActionListener(new CloseAllListener());
//         menuFile.getMenuItem("Close All").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
//         menuFile.getMenuItem("Quit").addActionListener(new QuitListener());
//         menuFile.getMenuItem("Quit").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
//         // EditorPane
//     }

//     public void exitProgram(){
//         this.dispose();
//     }

//     // Menu File
//     private class NewFileListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent actionEvent) {
//             editorPaneFiles.add(new EditorPaneFile("Untitled*"));
//             editorPaneFiles.get(editorPaneFiles.size()-1).addToTabbedPane(tabbedPane);
//             editorPaneFiles.get(editorPaneFiles.size()-1).getEditorPane().addKeyListener(new KeyEventUpdate());
//             tabbedPane.setSelectedIndex(tabbedPane.getComponentCount()-1);
//         }
//     }
//     private class OpenFileListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent actionEvent) {
//             JFileChooser fileChooser = new JFileChooser();
//             fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//             fileChooser.setDialogTitle("Open File");
//             FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
//             fileChooser.setFileFilter(filter);
//             int result = fileChooser.showOpenDialog(null);

//             if(result == JFileChooser.APPROVE_OPTION){
//                 String filePath = fileChooser.getSelectedFile().getAbsolutePath();
//                 String text = "";
//                 try{
//                     BufferedReader reader = new BufferedReader(new FileReader(filePath));
//                     String line = reader.readLine();
//                     while(line != null){
//                         text+=line;
//                         line = reader.readLine();
//                     }
//                     reader.close();
//                   }
//                   catch (IOException e){
//                     JOptionPane.showMessageDialog(null, e);
//                   }
//                   editorPaneFiles.add(new EditorPaneFile(fileChooser.getSelectedFile().getName()));
//                   editorPaneFiles.get(editorPaneFiles.size()-1).addToTabbedPane(tabbedPane);
//                   editorPaneFiles.get(editorPaneFiles.size()-1).setText(text);
//                   tabbedPane.setSelectedIndex(tabbedPane.getComponentCount()-1);
//             }
//         }
        
//     }
//     private class OpenFolderListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent actionEvent) {
//             JFileChooser fileChooser = new JFileChooser();
//             fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//             fileChooser.setDialogTitle("Open Folder");
//             int result = fileChooser.showOpenDialog(null);

//             if(result == JFileChooser.APPROVE_OPTION){
//                 String folderPath = fileChooser.getSelectedFile().getAbsolutePath();
//             }
//         }
//     }
//     private class SaveListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent actionEvent) {
//             int index = tabbedPane.getSelectedIndex();
//             if(editorPaneFiles.get(index).getFilePath()!=null){
//                 try{
//                     PrintWriter sortie = new PrintWriter(new FileWriter(editorPaneFiles.get(index).getFilePath()));
//                     sortie.println(editorPaneFiles.get(index).getEditorPane().getText());
//                     sortie.close();
//                     }
//                     catch(IOException e){
//                         e.printStackTrace();
//                     }
//                 editorPaneFiles.get(index).updateLabelName(editorPaneFiles.get(index).getFileName(),tabbedPane);
//             }
//             else{
//                 JFileChooser fileChooser = new JFileChooser();
//                 fileChooser.setDialogTitle("Destination Folder");
//                 FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
//                 fileChooser.setFileFilter(filter);
//                 int result = fileChooser.showSaveDialog(null);

//                 if(result == JFileChooser.APPROVE_OPTION){
//                     // Set Folder path and add a .txt if there's not already one
//                     String folderPath = fileChooser.getSelectedFile().getAbsolutePath();
//                     String ending = folderPath.substring(folderPath.length()-4);
//                     if(!ending.toLowerCase().equals(".txt")){
//                         folderPath += ".txt";
//                     }
//                     // Change the name in the tabbedPane
//                     editorPaneFiles.get(index).updateLabelName(fileChooser.getSelectedFile().getName(),tabbedPane);
//                     // Save path
//                     editorPaneFiles.get(index).setFilePath(folderPath);
//                     // Write into a file
//                     try{
//                         PrintWriter sortie = new PrintWriter(new FileWriter(folderPath));
//                         sortie.println(editorPaneFiles.get(index).getEditorPane().getText());
//                         sortie.close();
//                         }
//                         catch(IOException e){
//                             e.printStackTrace();
//                         }
//                 }
//             }
//         }
//     }
//     private class SaveAsListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent actionEvent) {
//             int index = tabbedPane.getSelectedIndex();
//             JFileChooser fileChooser = new JFileChooser();
//                 fileChooser.setDialogTitle("Destination Folder");
//                 FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
//                 fileChooser.setFileFilter(filter);
//                 int result = fileChooser.showSaveDialog(null);

//                 if(result == JFileChooser.APPROVE_OPTION){
//                     // Set Folder path and add a .txt if there's not already one
//                     String folderPath = fileChooser.getSelectedFile().getAbsolutePath();
//                     String ending = folderPath.substring(folderPath.length()-4);
//                     if(!ending.toLowerCase().equals(".txt")){
//                         folderPath += ".txt";
//                     }
//                     // Change the name in the tabbedPane
//                     editorPaneFiles.get(index).updateLabelName(fileChooser.getSelectedFile().getName(), tabbedPane);

//                     // Save path
//                     editorPaneFiles.get(index).setFilePath(folderPath);
//                     // Write into a file
//                     try{
//                         PrintWriter sortie = new PrintWriter(new FileWriter(folderPath));
//                         sortie.println(editorPaneFiles.get(index).getEditorPane().getText());
//                         sortie.close();
//                         }
//                         catch(IOException e){
//                             e.printStackTrace();
//                         }
//                 }
//         }
//     }
//     private class CloseListener implements ActionListener{
//         @Override
//         public void actionPerformed(ActionEvent actionEvent) { 
//             if(editorPaneFiles.get(tabbedPane.getSelectedIndex()).getFileName() == "Untitled*" && editorPaneFiles.get(tabbedPane.getSelectedIndex()).getText().isEmpty()){
//                 closeTab();
//             }
//             else{
//                 if(!editorPaneFiles.get(tabbedPane.getSelectedIndex()).isSaved()){
//                     int reply = JOptionPane.showConfirmDialog(null, "The file you're trying to close is not saved.\nDo you want to save?", "Save Confirmation", JOptionPane.YES_NO_OPTION);
//                     if(reply == JOptionPane.YES_OPTION){
//                         new SaveListener().actionPerformed(actionEvent);
//                         closeTab();
//                     }
//                     else if(reply == JOptionPane.NO_OPTION){
//                         closeTab();
//                     }                 
//                 }
//                 else{
//                     closeTab();
//                 }
//             } 
//         }

//         public void closeTab(){
//             int index = tabbedPane.getSelectedIndex();
            
//     }
//     private class CloseAllListener extends CloseListener{
//         @Override
//         public void actionPerformed(ActionEvent actionEvent) {
//             int arraySize = editorPaneFiles.size();
//             loop : {
//                 for(int i=0 ; i< arraySize ;i++){
//                     if(editorPaneFiles.get(tabbedPane.getSelectedIndex()).getFileName() == "Untitled*" && editorPaneFiles.get(tabbedPane.getSelectedIndex()).getText().isEmpty()){
//                         closeTab();
//                     }
//                     else{
//                         if(!editorPaneFiles.get(tabbedPane.getSelectedIndex()).isSaved()){
//                             int reply = JOptionPane.showConfirmDialog(null, "The file you're trying to close is not saved.\nDo you want to save?", "Save Confirmation", JOptionPane.YES_NO_OPTION);
//                             if(reply == JOptionPane.YES_OPTION){
//                                 new SaveListener().actionPerformed(actionEvent);
//                                 closeTab();
//                             }
//                             else if(reply == JOptionPane.NO_OPTION){
//                                 closeTab();
//                             }
//                             else{
//                                 break loop;
//                             }                 
//                         }
//                         else{
//                             closeTab();
//                         }
//                     }            
//                 }
//             }
//         }
//     }
//     private class QuitListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent actionEvent) {
//             new CloseAllListener().actionPerformed(actionEvent);
//             if(editorPaneFiles.get(0).getFileName() == "Untitled*" && editorPaneFiles.get(0).getText().isEmpty()) exitProgram();
//         }
//     }

//     private class KeyEventUpdate implements KeyListener{
//         public void keyTyped(KeyEvent e){
//             int index = tabbedPane.getSelectedIndex();
//             editorPaneFiles.get(index).update(tabbedPane);
//         }
//         public void keyPressed(KeyEvent e) {
            
//         }
//         public void keyReleased(KeyEvent e) {

//         }
//     }
// }