import javax.swing.*;
import java.awt.*;
 
public class Pan_Parameters extends JPanel{
    Panel_1_1 tab1 = new Panel_1_1();
    Panel_1_2 tab2 = new Panel_1_2();
    Panel_1_3 tab3 = new Panel_1_3();
    Panel_1_4 tab4 = new Panel_1_4();
    
    public Pan_Parameters() {        
        JTabbedPane tabbedPane = new JTabbedPane();
          
        tabbedPane.addTab("Production", tab1);
        tabbedPane.addTab("Scoop",      tab2);
        tabbedPane.addTab("Sensors",    tab3);
        tabbedPane.addTab("Program",    tab4);
        setLayout(new GridLayout(0,1));
        add(tabbedPane);
    }
     
    public void update() {
        tab1.update();
        tab2.update();  
        tab3.update();
        tab4.update();
    }
}