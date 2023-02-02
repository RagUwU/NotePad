// import java.util.*;

// import javax.swing.*;

// public class EditorPaneFile {
//     private String fileName;
//     private String filePath;
//     private JEditorPane editorPane = new JEditorPane();
//     private Boolean isSaved;
//     private String text;

//     public EditorPaneFile(String fileName){
//         this.fileName = fileName;
//         this.isSaved = false;
//         this.text = "";
//         editorPane.setFocusable(true);
//     }

//     public void addToTabbedPane(JTabbedPane tabbedPane){
//         String ending = fileName.substring(fileName.length()-4);
//         String updatedFileName = this.fileName;
//         // Substract the ending of the file name for the display, even if it's .TxT or .txt
//         if(ending.toLowerCase().equals(".txt")){
//             updatedFileName = fileName.replace(ending, "");
//         }
//         tabbedPane.add(updatedFileName, new JScrollPane(editorPane));
//     }
//     /**
//      * This fonction is used to update the title of the tab of the tabbedPaned
//      * @param fileName This param is nullable, if put null, it will udpate the name as a non-updated name ending with * (?String)
//      * @param tabbedPane The tabbedPane where the change will do effect (JTabbedPaned)
//      */
//     public void updateLabelName(String fileName, JTabbedPane tabbedPane){
//         Optional<String> optionalFileName = Optional.ofNullable(fileName);
//         String tabName = "";
//         if(optionalFileName.isPresent()){
//             tabName = fileName.replace("*", "");
//         }
//         else{
//             if(!this.fileName.endsWith("*")){
//                 tabName = this.fileName+"*";
//             }
//         }
//         this.fileName = tabName;
//         tabbedPane.setTabComponentAt(tabbedPane.getSelectedIndex(), new JLabel(tabName)); 
//     }


//     public void update(JTabbedPane tabbedPane){
//         if(!this.text.equals(editorPane.getText())){
//             if(!fileName.endsWith("*")) updateLabelName(null, tabbedPane);
//             this.text = editorPane.getText();
//         }
//     }

//     public boolean isSaved(){
//         return (!fileName.endsWith("*"));
//     }

//     public String getFileName(){
//         return fileName;
//     }
//     public String getFilePath(){
//         return filePath;
//     }
//     public JEditorPane getEditorPane(){
//         return editorPane;
//     }
//     public String getText(){
//         return editorPane.getText();
//     }
//     public void setText(String text){
//         editorPane.setText(text);
//     }
//     public void setFilePath(String filePath){
//         this.filePath = filePath;
//     }
//     public void setFileName(String fileName){
//         this.fileName = fileName;
//     }
// }