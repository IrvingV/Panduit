import javax.swing.*;       // JPanel
import java.awt.*;          // Color, Layou
import java.util.*;        // Hashtable
import java.text.DecimalFormat;

public class LevelInt extends JPanel{
    public Integer valInt=0;
    public Double valDouble=0.0;
    public DecimalFormat df;
    
    private Integer i_Max=100, i_Min=10;
    private Integer delta, i_HH, i_H, i_L, i_LL;
    private Hashtable labelTable = new Hashtable();
  
    private JSlider jsld_val; 
    JLabel jlbl_val  ;  
    
    
    public LevelInt(String s, String format, Integer min, Integer max, String dim) {
        df = new DecimalFormat(format);
        
        i_Min = min;
        i_Max = max;
        
        delta = (i_Max-i_Min);
        i_HH = 1000;
        i_H  = 800;
        i_L  = 400;
        i_LL = 200;
        

        JLabel jlbl_hdr;
        jlbl_hdr = new JLabel(s);
        jlbl_hdr.setFont(new Font("LucidaConsole", Font.PLAIN, 12));
        
        jlbl_val = new JLabel();
        jlbl_val.setFont(new Font("LucidaConsole", Font.BOLD, 12));
        jlbl_val.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JLabel jlbl_dim;
        jlbl_dim = new JLabel(dim);
        jlbl_dim.setFont(new Font("LucidaConsole", Font.PLAIN, 12));

        jsld_val = new JSlider(JSlider.HORIZONTAL, i_Min, i_Max, i_Min);

        //Create the label table        
//         labelTable.put( new Integer( i_HH ), new JLabel("HH") );
//         labelTable.put( new Integer( i_H  ), new JLabel("H") );
//         labelTable.put( new Integer( i_L  ), new JLabel("L") );
//         labelTable.put( new Integer( i_LL ), new JLabel("LL") );
//         jsld_val.setLabelTable( labelTable );
//         jsld_val.setPaintLabels(true);
        
        JPanel panelNorth = new JPanel(new FlowLayout());
        panelNorth.add(jlbl_hdr );
        panelNorth.add(jlbl_val);
        panelNorth.add(jlbl_dim);
        
        setLayout(new BorderLayout());
        add(panelNorth, BorderLayout.NORTH);
        add(jsld_val, BorderLayout.CENTER);
        update();
    }
    
    public void setSliderMax(int m) {
        jsld_val.setMaximum(m);
    }
    
    public void  update() {
        jsld_val.setValue(valInt);
        jlbl_val.setText(df.format(valDouble));

        boolean yellow=false;
        boolean red=false;

        //         delta = (i_Max-i_Min);
        //         i_HH = i_Min + delta * (HH/100);
        //         i_H  = i_Min + delta * (H /100);
        //         i_L  = i_Min + delta * (L /100);
        //         i_LL = i_Min + delta * (LL/100);
        
        if ( valInt > i_H  || valInt < i_L ) yellow=true;
        if ( valInt > i_HH || valInt < i_LL ) {
            red=true;
            yellow =false;
        }
        if (yellow)  jsld_val.setBackground(Color.yellow);
        if (red) jsld_val.setBackground(Color.red);
        if (!yellow && !red)  jsld_val.setBackground(Color.green);
    }        
}

