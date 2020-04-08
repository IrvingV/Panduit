import javax.swing.*;
import java.awt.*;
 
public class Panel_2_1 extends JPanel{
    DisplayVar   var11 = new DisplayVar("start positie"                 ," ####", "mm");
    DisplayVar   var12 = new DisplayVar("start positie gecorrigeerd"    ," ####", "mm");
    DisplayVar   var13 = new DisplayVar("stop  positie"                 ," ####", "mm");
    DisplayVar   var14 = new DisplayVar("Stop  positie gecorrigeerd"    ," ####", "mm");
    DisplayVar   var15 = new DisplayVar("tie-wrap positie 1"            ," ####", "mm");
    DisplayVar   var16 = new DisplayVar("tie-wrap positie 2"            ," ####", "mm");
    DisplayVar   var17 = new DisplayVar("tie-wrap positie 3"            ," ####", "mm");
    DisplayVar   var18 = new DisplayVar("tie-wrap positie 4"            ," ####", "mm");


    public Panel_2_1() {
        setLayout(new GridLayout(0, 1));
        add(var11);
        add(var12);
        add(var13);
        add(var14);
        add(var15);
        add(var16);
        add(var17);
        add(var18);
    }
     
    public void update() {
        var11.update();
        var12.update();
        var13.update();
        var14.update();
        var15.update();
        var16.update();
        var17.update();
        var18.update();
   }


}