import javax.swing.*;       // JPanel
import java.awt.*;          // Color
import java.awt.event.*;    // actionListener
import javax.swing.event.*; // ChangeListener, ChangeEvent
import java.text.DecimalFormat;

public class Slider extends JPanel{
    public Double val;
    public Double oldval=0.0;
    public DecimalFormat df;
    public String hdr; String frm;double mn; double mx; String dm;

    public boolean stopped=false;
    private int sliderval;
    private Double i_Max, i_Min, a1, b1, a2, b2;
    private boolean blockUpdate=false;
    
    private JLabel jlbl_hdr, jlbl_dim;
    private JFormattedTextField jtxf_val;

    private Integer i_LargeStep, i_SmallStep;
    private JSlider jsld_val;  

    public Slider(String s, String format, double min, double max, String dim) {
        hdr=s;
        df = new DecimalFormat(format);

        a1=(max-min)/1000;
        b1=min;
        a2=1000/(max-min);
        b2=-a2*min;

        val=a1*min+b1;
        
        i_Min = min;
        i_Max = max;
       
        jlbl_hdr =  new JLabel(s);
        jlbl_hdr.setFont(new Font("LucidaConsole", Font.PLAIN, 12));
        jlbl_hdr.setToolTipText("Integer: min = " + i_Min + ", max = " + i_Max);
        
        jsld_val = new JSlider(JSlider.HORIZONTAL, 0, 1000, val.intValue());
        jsld_val.addChangeListener(new ChangeListener() {
             public void stateChanged(ChangeEvent e) { 
                sliderval = jsld_val.getValue();
                val = a1 * sliderval + b1 ;
                update();     
            }
        }); 
        
        JButton btn_1 = new JButton("++");                 // button definitie
        btn_1.addActionListener( new ActionListener() {   // Listener
            public void actionPerformed(ActionEvent arg0) { // anonieme klasse
                sliderval=sliderval+100 ; if (sliderval>1000) sliderval=1000; 
                val = a1 * sliderval + b1 ; 
                update();     
            }
        });

        JButton btn_2 = new JButton("+");                 // button definitie
        btn_2.addActionListener( new ActionListener() {   // Listener
            public void actionPerformed(ActionEvent arg0) { // anonieme klasse
                sliderval=sliderval+10 ; if (sliderval>1000) sliderval=1000; 
                val = a1 * sliderval + b1 ;
                update();     
            }
        });
        
        JButton btn_3= new JButton("-");
        btn_3.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) { 
                sliderval=sliderval-10 ; if (sliderval<0) sliderval=0; 
                val = a1 * sliderval + b1 ;
                update();     
            }
        });
        JButton btn_4 = new JButton("--");
        btn_4.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) { 
                sliderval=sliderval-100 ; if (sliderval<0) sliderval=0; 
                val = a1 * sliderval + b1 ;
                update();     
            }
        });
        

        jtxf_val=new JFormattedTextField();
        jtxf_val.setFont(new Font("LucidaConsole", Font.BOLD, 12));
        jtxf_val.setBorder(BorderFactory.createLineBorder(Color.black));
        jtxf_val.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent evt) {
                JFormattedTextField fi = (JFormattedTextField) evt.getSource();
                fi.setBackground (Color.white);
                try {
                    Double temp = Double.parseDouble(fi.getText());
                    if ( temp <= i_Max && temp >= i_Min ) { 
                        val = temp;
                        Double d = a2 * val + b2 ;
                        sliderval = d.intValue() ;
                        stopped=true;
                    }
                    else {
                        fi.setBackground (Color.red);
                    }            
                }
                catch (NumberFormatException fout) {
                    fi.setBackground (Color.red);
                }
                update();     
           }   
        }); 
 
        
        jlbl_dim =  new JLabel(dim);
        jlbl_dim.setFont(new Font("LucidaConsole", Font.PLAIN, 12));

        JPanel panel_north1 = new JPanel(new FlowLayout());
        panel_north1.add(jlbl_hdr) ;
        panel_north1.add(jtxf_val);
        panel_north1.add(jlbl_dim);
        
        JPanel panel_north2 = new JPanel(new GridLayout(1,0));
        panel_north2.add(btn_4);
        panel_north2.add(btn_3);
        panel_north2.add(btn_2);
        panel_north2.add(btn_1);
        
        JPanel panel_north = new JPanel(new GridLayout(0,1));
        panel_north.add(panel_north1);
        panel_north.add(panel_north2);

        setLayout(new BorderLayout());
        add(panel_north,BorderLayout.NORTH);
        add(jsld_val,BorderLayout.CENTER);
    
    }
    
    public void update() {
        if (val!=oldval) {
            jsld_val.setValue(sliderval);
            jtxf_val.setText(df.format(val));
            oldval=val;
        }
    }        
}