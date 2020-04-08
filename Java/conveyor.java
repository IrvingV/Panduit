
import java.awt.*;
import javax.swing.*;

public class conveyor extends JApplet
{
    
         MainInit mn;
   
    public void init()
    {
         mn      = new MainInit();    
    }


    public void start() {
        mn.MainLoop();
    }


    public void stop()
    {
            }

   

    public void destroy()
    {
    }


    public String getAppletInfo()
    {
        // provide information about the applet
        return "Title:   \nAuthor:   \nA simple applet example description. ";
    }



    public String[][] getParameterInfo()
    {
        // provide parameter information about the applet
        String paramInfo[][] = {
                 {"firstParameter",    "1-10",    "description of first parameter"},
                 {"status", "boolean", "description of second parameter"},
                 {"images",   "url",     "description of third parameter"}
        };
        return paramInfo;
    }
}
