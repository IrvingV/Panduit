import javax.swing.*;
import java.awt.*;
 
public class Panel_1_2 extends JPanel{

    Slider      tdiv = new Slider("time divison"            ," 000 "    ,  10, 1010      , "ms/div"); 
    ButtonOnOff reso = new ButtonOnOff("Resolution", "");
    ButtonOnOff paus = new ButtonOnOff("pause","");
//     ButtonOnOff rmm = new ButtonOnOff("resetminmax","");
//     ButtonOnOff sp1 = new ButtonOnOff("spare 1","");

    public Panel_1_2() {        
        setLayout(new GridLayout(0, 1));
        add(tdiv);
        add(reso);
        add(paus);
    }
     
    public void update() {
        tdiv.update();
        reso.update();
        paus.update();
   }


}