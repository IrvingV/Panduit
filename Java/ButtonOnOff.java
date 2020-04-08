 

import javax.swing.*;       // JPanel
import java.awt.event.*;    // actionListener
import java.awt.*;          // Color, Layou

public class ButtonOnOff extends JPanel {
    public boolean status=false;
    public boolean ri_status=false;
    public boolean fe_status=false;

    private boolean old_status;
    private JButton btn;
    
    public ButtonOnOff(String line1, String line2) {
        String ss="<html><center>" + line1 + "<br>" + line2  + "</center></html>";
        btn= new JButton(ss);
        btn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) { 
                status=!status;
                update();
            }
        });
        add(btn );
        update();
    }
    
    public void update() {
        if (status) {
            btn.setBackground(Color.yellow);  
        } else {
            btn.setBackground(Color.lightGray);
        }
        ri_status = status && !old_status;
        fe_status = !status && old_status;
        old_status = status;
        
    }        
}
