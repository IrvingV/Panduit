import javax.swing.*;       // JPanel
import java.awt.*;          // Color, Layout
import java.text.DecimalFormat;


public class DisplayVar extends JPanel{
    public double val=0;
//     public JPanel  panel;
    public DecimalFormat df;
    
    
    
        
    private JLabel jlbl_val;
    
    
    public DisplayVar(String s, String format, String dim) {
        df = new DecimalFormat(format);
    
        
        
                
        
        JLabel jlbl_hdr;
        jlbl_hdr = new JLabel(s);
        jlbl_hdr.setFont(new Font("LucidaConsole", Font.PLAIN, 12));
        jlbl_hdr.setBounds(10,0,150,20);
        
        
        jlbl_val = new JLabel();
        jlbl_val.setFont(new Font("LucidaConsole", Font.BOLD, 12));
        jlbl_val.setBorder(BorderFactory.createLineBorder(Color.black));
        jlbl_val.setBounds(150,0,100,20);
        
        JLabel jlbl_dim;
        jlbl_dim = new JLabel(dim);
        jlbl_dim.setFont(new Font("LucidaConsole", Font.PLAIN, 12));
        jlbl_dim.setBounds(260,0,60,20);
        
//         panel = new JPanel();
        setLayout(null);
        add(jlbl_hdr);
        add(jlbl_val);
        add(jlbl_dim);

        update();
    }
    
    public void update() {
        jlbl_val.setText(df.format(val));
    }        
}
