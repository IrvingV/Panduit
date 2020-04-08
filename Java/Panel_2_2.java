import javax.swing.*;
import java.awt.*;
 
public class Panel_2_2 extends JPanel{
    DisplayVar   t1    = new DisplayVar("actual mainprog time"," ####", "us");
    DisplayVar   t1min = new DisplayVar("min    mainprog time"," ####", "us");
    DisplayVar   t1max = new DisplayVar("max    mainprog time"," ####", "us");
    DisplayVar   t2    = new DisplayVar("actual grafprog time"," ####", "us");
    DisplayVar   t2min = new DisplayVar("min    grafprog time"," ####", "us");
    DisplayVar   t2max = new DisplayVar("max    grafprog time"," ####", "us");
    DisplayVar   vp    = new DisplayVar("pixelverplaatsing vp"," ####", "pixels");
    DisplayVar   dx    = new DisplayVar("dx                  "," ####", "mm");
    DisplayVar   dt    = new DisplayVar("dt                  "," ####", "ms");
    DisplayVar   dtp   = new DisplayVar("dt product          "," ####", "ms");

    public Panel_2_2() {
        setLayout(new GridLayout(0, 1));
        add(t1   );
        add(t1min);
        add(t1max);
        add(t2   );
        add(t2min);
        add(t2max);
        add(vp   );
        add(dx   );
        add(dt   );
        add(dtp  );
    }
    
    public void update() {
        t1   .update();
        t1min.update();
        t1max.update();
        t2   .update();
        t2min.update();
        t2max.update();
        vp   .update();
        dx   .update();
        dt   .update();
        dtp  .update();
    }
}