import java.applet.Applet;
import java.awt.Graphics;

//No need to extend JApplet, since we don't add any components;
//we just paint.
public class Simple extends Applet {

    StringBuffer buffer;
   
         MainInit mn;
   
 
    public void init() {
        mn      = new MainInit();  
        buffer = new StringBuffer();
        addItem("initializing... ");
    }

    public void start() {
        addItem("starting... ");
  
        mn.MainLoop();

    }

    public void stop() {
        addItem("stopping... ");
    }

    public void destroy() {
        addItem("preparing for unloading...");
    }

    private void addItem(String newWord) {
        System.out.println(newWord);
        buffer.append(newWord);
        repaint();
    }

    public void paint(Graphics g) {
	//Draw a Rectangle around the applet's display area.
         g.drawRect(0, 0, 
		   getWidth() - 1,
		   getHeight() - 1);
         
	//Draw the current string inside the rectangle.
        g.drawString(buffer.toString(), 5, 15);
    }
}