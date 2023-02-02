import javax.swing.*;
import java.util.*;

public class NotePadMenu {

    private JMenu menuName;
    private ArrayList<JMenuItem> menuItems;

    /**
     * Constructor method for the NotePadMenu method
     * 
     * @param menuName The name of the menu in the menu bar (Jmenu)
     * @param menuItems All off the items in the menu, use %separator% to put a separator (ArrayList<JMenuItem>)
     */
    NotePadMenu(JMenu menuName, ArrayList<JMenuItem> menuItems){
        this.menuName = menuName;
        this.menuItems = menuItems;
    }

    public void addItems(){
        for(JMenuItem item : menuItems){
            if(item.getText().equals("%separator%")){
                menuName.addSeparator();
            }
            else{
                menuName.add(item);
            }
        }
    }

    public void addToBar(JMenuBar menuBar){
        menuBar.add(this.menuName);
    }
    public JMenuItem getMenuItem(String itemName) throws RuntimeException{
        for(JMenuItem item : menuItems){
            if(item.getText().equals(itemName)){
                return item;
            }
        }
        throw new RuntimeException("Error happened");
    }
}
