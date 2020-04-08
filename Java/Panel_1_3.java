import javax.swing.*;
import java.awt.*;
 
public class Panel_1_3 extends JPanel{

    Slider      dist = new Slider("Distance between sensors"," 000 "    , 10,  330      , "mm"); 
    ButtonOnOff shbu = new ButtonOnOff("Show buffer","");

    public Panel_1_3() {
        setLayout(new GridLayout(0, 1));
        add(dist);
        add(shbu);
    }
     
    public void update() {
        dist.update();
        shbu.update();
   }


}