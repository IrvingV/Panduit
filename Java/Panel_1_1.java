import javax.swing.*;
import java.awt.*;
 
public class Panel_1_1 extends JPanel{
    Slider      par11 = new Slider("Bocht 1 positie"         ," 000 "    ,   0 , 1400     , "mm"); 
    Slider      par12 = new Slider("Bocht 2 positie"         ," 000 "    , 400 , 1800     , "mm");
    Slider      par13 = new Slider("Collision area"          ," 000 "    ,   0 ,   50     , "mm");

    public Panel_1_1() {
        setLayout(new GridLayout(0, 1));
        add(par11);
        add(par12);
        add(par13);
    }
     
    public void update() {
        par11.update();
        par12.update();
        par13.update();
   }


}