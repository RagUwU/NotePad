import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.text.Document;

import java.awt.event.*;
import java.util.*;
import java.io.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.function.UnaryOperator;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.undo.*;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
 
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.undo.*;
import java.awt.Toolkit;
import java.awt.datatransfer.*; 


public class TextDocument extends JScrollPane{
    private JEditorPane editorPane;
    private String filePath; 
    private String text = "";
    private Clipboard cBoard;
    // Undo Redo
    private UndoManager undo = new UndoManager();
    private DefaultStyledDocument dsd;
    // Constructors
    public TextDocument(){
        load();
    }
    public TextDocument(File file){
        this.filePath = file.getAbsolutePath();
        String text = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while(line != null){
                text+=line;
                line = reader.readLine();
            }
            reader.close();
            }
            catch (IOException e){
                JOptionPane.showMessageDialog(null, e);
            }
        load();
        this.editorPane.setText(text);
        this.text = text;
    }

    // Other methods
    public boolean getHasChanged(){
        if(this.text.equals(editorPane.getText()))return false;;
        return true;
    }
    public String getFileName(){
        if(filePath == null) return "Untitled*";
        else if(getHasChanged()) return filePath.substring(filePath.lastIndexOf("\\")+1).replace(".txt", "") + "*";
        return filePath.substring(filePath.lastIndexOf("\\")+1).replace(".txt", "");
        
    }
    public String getFilePath(){
        return filePath;
    }
    public void copy(){
        String selection = editorPane.getSelectedText(); 
        if (selection == null) 
            return; 
            StringSelection clipString =new StringSelection(selection); 
            cBoard.setContents(clipString,clipString); 
    }
    public void cut(){
        String selection = editorPane.getSelectedText(); 
        if (selection == null) 
             return; 
             StringSelection clipString = new StringSelection(selection); 
             cBoard.setContents(clipString, clipString); 
             editorPane.replaceSelection(""); 
    }
    public void paste(){
        Transferable clipData = cBoard.getContents(this); 
        try { 
            String clipString = (String)clipData.getTransferData(DataFlavor.stringFlavor); 
            editorPane.replaceSelection(clipString);
        }   catch(Exception ex) 
        { 
            System.err.println("Not Working"); 
        } 
    }
    public void save(){
        if(filePath!=null){
            try{
                PrintWriter sortie = new PrintWriter(new FileWriter(filePath));
                sortie.println(editorPane.getText());
                sortie.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
                this.text = this.editorPane.getText();
                setTabTitle();
        }
        else{
            saveAs();
        }
    }
    public void saveAs(){
        File file = saveDialog();
        if(file != null){
            // Set Folder path and add a .txt if there's not already one
            String folderPath = file.getAbsolutePath();
            String ending = folderPath.substring(folderPath.length()-4);
            if(!ending.toLowerCase().equals(".txt")){
                folderPath += ".txt";
            }
            // Save path
            filePath = folderPath;
            // Changed saved text
            this.text = this.editorPane.getText();
            // Change tab name
            setTabTitle();
            // Write into a file
            try{
                PrintWriter sortie = new PrintWriter(new FileWriter(folderPath));
                sortie.println(editorPane.getText());
                sortie.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
        }
    }
    public boolean close(){
        if(this.getFileName() == null && !this.getHasChanged()) return true;
        
        else{
            if(this.getHasChanged()){
                
                int reply = JOptionPane.showConfirmDialog(null, "The file you're trying to close is not saved.\nDo you want to save " + this.getFileName().replace("*", ".txt") + "?", "Save Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
                if(reply == JOptionPane.CANCEL_OPTION) return false;
                else if(reply == JOptionPane.YES_OPTION){
                    this.save();
                    return true;
                }
                else if(reply == JOptionPane.NO_OPTION) return true;            
            }
            else{
                return true;
            }
        } 
        return true;
    }
    public void undo(){
        try {
            undo.undo();
        } catch (CannotUndoException ex) {
            System.out.println("Unable to undo: " + ex);
        }
    }
    public void redo(){
        try {
            undo.redo();
        } catch (CannotRedoException ex) {
            System.out.println("Unable to redo: " + ex);
        }
    }
    public boolean selectNext(){
        return true;
    }
    public boolean replaceNext(){
        return true;
    }
    public boolean replaceAll(){
        return true;
    }
    public static File openDialog(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Open File");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);

        if(result == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile();
        }
        return null;
    }
    
    public void setTabTitle()
    {
        JTabbedPane tabbedPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(JTabbedPane.class, this);
        if(tabbedPane != null){
            for(int tabIndex = 0; tabIndex < tabbedPane.getTabCount(); tabIndex++)
            {
                if (SwingUtilities.isDescendingFrom(this, tabbedPane.getComponentAt(tabIndex)))
                {
                    tabbedPane.setTitleAt(tabIndex, getFileName());
                    break;
                }
            }
        }
    }

    // Private methods

    private void load(){
        addComponents();
        addListeners();
    }

    private void addComponents(){
        editorPane = new JEditorPane();
        dsd = new DefaultStyledDocument();
        editorPane.setDocument(dsd);
        this.setViewportView(editorPane);
        cBoard = getToolkit().getSystemClipboard();
    }

    private void addListeners(){
        dsd.addUndoableEditListener(e -> 
        {
            setTabTitle();
            undo.addEdit(e.getEdit());
        }
        );
    }

    private File saveDialog(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Destination Folder");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);

        if(result == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile();
        }
        return null;
    }
}