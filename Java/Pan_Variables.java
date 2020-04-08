import javax.swing.*;
import java.awt.*;
 
public class Pan_Variables extends JPanel{
    Panel_2_1 tab1 = new Panel_2_1();
    Panel_2_2 tab2 = new Panel_2_2();

    
    
    public Pan_Variables() {        
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("general",  tab1);
        tabbedPane.addTab("timers",  tab2);

        
        setLayout(new GridLayout(0,1));
        add(tabbedPane);
    }
     
    public void update() {
        tab1.update();
        tab2.update();
    
    
        }
}