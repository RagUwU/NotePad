import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
 * Class extended from the JTabbedPane class
 * Use to contain all the different textDocument
 * Contains multiple methods.
 */
public class DocumentContainer extends JTabbedPane{
    public DocumentContainer(){
        createNewDocument();
    }
    public void createNewDocument(){
        this.add("Untitled*",new TextDocument());
        this.setSelectedIndex(this.getTabCount()-1);
    }
    public void openDocument(){
        File file = TextDocument.openDialog();
        if(file != null){
            this.add(file.getName().replace(".txt", ""), new TextDocument(file));
            this.setSelectedIndex(this.getTabCount()-1);
        } 
    }
    public void saveCurrentDocument(){ 
        TextDocument textDocument = (TextDocument)this.getSelectedComponent();
        textDocument.save();
    }
    public void saveAsCurrentDocument(){
        TextDocument textDocument = (TextDocument)this.getSelectedComponent();
        textDocument.saveAs();
    }
    public boolean closeCurrentDocument(){
        TextDocument textDocument = (TextDocument)this.getSelectedComponent();

        if(textDocument.close()){
        this.remove(textDocument);
            if(this.getComponentCount() == 0){
                createNewDocument();
                // Ask if wanna exit the program      
                int reply = JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "Exit", JOptionPane.YES_NO_OPTION);
                    if(reply == JOptionPane.YES_OPTION){
                        JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
                        frame.dispose();
                    }
            }
            if(this.getSelectedIndex()==0) this.setSelectedIndex(0);
            else this.setSelectedIndex(this.getSelectedIndex()-1);
        }
        
        return true;
    }
    public boolean closeAllDocument(){
        int currentElementCount = this.getComponentCount();
        loop:{
            for(int i = 0; i< currentElementCount; i++){
                this.setSelectedIndex(0);
                if(!closeCurrentDocument()) break loop;
            }
        }
        return true;
    }
    public void undoCurrentDocument(){
        TextDocument textDocument = (TextDocument)this.getSelectedComponent();
        textDocument.undo();
    }
    public void redoCurrentDocument(){
        TextDocument textDocument = (TextDocument)this.getSelectedComponent();
        textDocument.redo();
    }
    public void copyInCurrentDocument(){
        TextDocument textDocument = (TextDocument)this.getSelectedComponent();
        textDocument.copy();
    }
    public void cutInCurrentDocument(){
        TextDocument textDocument = (TextDocument)this.getSelectedComponent();
        textDocument.cut();
    }
    public void pasteInCurrentDocument(){
        TextDocument textDocument = (TextDocument)this.getSelectedComponent();
        textDocument.paste();
    }
    public void selectNext(String toSearch){

    }
    public void replaceNext(String toSearch, String toReplace){

    }
    public void replaceAll(String toSearch, String toReplace){
        
    }
}
