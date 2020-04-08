import javax.swing.*;
import java.awt.*;
 
public class Panel_1_4 extends JPanel{

    Slider      prgt = new Slider("program sample time"     ," 00.0 "   , 5 , 55        , "ms");
    LevelInt    mest = new LevelInt("time program executed" ," 00000 "  , 0 , 5000      , "us");

    public Panel_1_4() {        
        setLayout(new GridLayout(0, 1));
        add(prgt);
        add(mest);
    }
     
    public void update() {
        prgt.update();
        mest.update();
   }

}