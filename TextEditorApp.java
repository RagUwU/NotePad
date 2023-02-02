import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class TextEditorApp extends JFrame{
    
    // Variables documents
    private DocumentContainer documentContainer;
    // Variables Menu
    JMenuBar menuBar;
    MenuContainer menuFile;
    ArrayList<JMenuItem> menuFileItems;
    MenuContainer menuEdit;
    ArrayList<JMenuItem> menuEditItems;
    MenuContainer menuTools;
    ArrayList<JMenuItem> menuToolsItems;
    MenuContainer menuHelp;
    ArrayList<JMenuItem> menuHelpItems;

    public TextEditorApp(String title, int width, int height){
        // Window initialisation 
        this.setTitle(title);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initData();
        addComponents();
        addMenus();
        addListeners();

        this.setVisible(true);
    }
    private void initData(){

    }
    private void addComponents(){
        documentContainer = new DocumentContainer();
        this.add(documentContainer);
    }
    private void addMenus(){
        // Menu Bar
        menuBar = new JMenuBar();
        // Menu File
        menuFileItems = new ArrayList<JMenuItem>();
            menuFileItems.add(new JMenuItem("New"));
            menuFileItems.add(new JMenuItem("Open"));
            menuFileItems.add(new JMenuItem("%separator%"));
            menuFileItems.add(new JMenuItem("Save"));
            menuFileItems.add(new JMenuItem("Save As"));
            menuFileItems.add(new JMenuItem("%separator%"));
            menuFileItems.add(new JMenuItem("Close"));
            menuFileItems.add(new JMenuItem("Close All"));
            menuFileItems.add(new JMenuItem("Quit"));
        menuFile = new MenuContainer(new JMenu("File"), menuFileItems);
        menuFile.addItems();
        menuFile.addToBar(menuBar);
        // Menu Edit
        menuEditItems = new ArrayList<JMenuItem>();
            menuEditItems.add(new JMenuItem("Undo"));
            menuEditItems.add(new JMenuItem("Redo"));
            menuEditItems.add(new JMenuItem("%separator%"));
            menuEditItems.add(new JMenuItem("Copy"));
            menuEditItems.add(new JMenuItem("Cut"));
            menuEditItems.add(new JMenuItem("Paste"));
        menuEdit = new MenuContainer(new JMenu("Edit"), menuEditItems);
        menuEdit.addItems();
        menuEdit.addToBar(menuBar);
        // Menu Tools
        menuToolsItems = new ArrayList<JMenuItem>();
            menuToolsItems.add(new JMenuItem("Search"));
            menuToolsItems.add(new JMenuItem("Search and replace"));
            menuToolsItems.add(new JMenuItem("%separator%"));
            menuToolsItems.add(new JMenuItem("Theme"));
            menuToolsItems.add(new JMenuItem("Font"));
        menuTools = new MenuContainer(new JMenu("Tools"), menuToolsItems);
        menuTools.addItems();
        menuTools.addToBar(menuBar);
        // Menu help
        menuHelpItems = new ArrayList<JMenuItem>();
            menuHelpItems.add(new JMenuItem("About"));
        menuHelp = new MenuContainer(new JMenu("Help"), menuHelpItems);
        menuHelp.addItems();
        menuHelp.addToBar(menuBar);

        this.setJMenuBar(menuBar);
    }
    private void addListeners(){
        // Menu File
        menuFile.getMenuItem("New").addActionListener(e -> documentContainer.createNewDocument());
        menuFile.getMenuItem("New").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        menuFile.getMenuItem("Open").addActionListener(e -> documentContainer.openDocument());
        menuFile.getMenuItem("Open").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        menuFile.getMenuItem("Save").addActionListener(e -> documentContainer.saveCurrentDocument());
        menuFile.getMenuItem("Save").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        menuFile.getMenuItem("Save As").addActionListener(e -> documentContainer.saveAsCurrentDocument());
        menuFile.getMenuItem("Save As").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        menuFile.getMenuItem("Close").addActionListener(e -> documentContainer.closeCurrentDocument());
        menuFile.getMenuItem("Close").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
        menuFile.getMenuItem("Close All").addActionListener(e -> documentContainer.closeAllDocument());
        menuFile.getMenuItem("Close All").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        menuFile.getMenuItem("Quit").addActionListener(e -> {if(documentContainer.closeAllDocument()) this.dispose();});
        menuFile.getMenuItem("Quit").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        //  Menu Edit
        menuEdit.getMenuItem("Undo").addActionListener(e -> documentContainer.undoCurrentDocument());
        menuEdit.getMenuItem("Undo").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        menuEdit.getMenuItem("Redo").addActionListener(e -> documentContainer.redoCurrentDocument());
        menuEdit.getMenuItem("Redo").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        menuEdit.getMenuItem("Copy").addActionListener(e -> documentContainer.copyInCurrentDocument());
        menuEdit.getMenuItem("Copy").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        menuEdit.getMenuItem("Cut").addActionListener(e -> documentContainer.cutInCurrentDocument());
        menuEdit.getMenuItem("Cut").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        menuEdit.getMenuItem("Paste").addActionListener(e -> documentContainer.pasteInCurrentDocument());
        menuEdit.getMenuItem("Paste").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        //  Menu Tools
    }
}
