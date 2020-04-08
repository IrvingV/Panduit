import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

class PAN_Grafiek extends JPanel {

    public      boolean     xObjectBuilded  =  false,
                            xDrawInit;
    public      double      t;
    
    private     CanvasPanel canvas; 
    private     Graphics2D  graphic;
    private     Image       canvasImage;
    private     JFrame      frame;
        
    private     int         wd_dx   =   8,
                            iB, iH,
                            iXminPx, iXmaxPx,
                            iXminIn, iXmaxIn,
                            iYminPx, iYmaxPx,
                            iYminIn, iYmaxIn;
    
    private     Integer     iTemp;
    private     double      t0,
                            dXPixelsPerUnit,
                            dYPixelsPerUnit;
    
    
    public PAN_Grafiek( int iSchermPosX,
                        int iSchermPosY,
                        int iBreedte,
                        int iHoogte ){
        iB=iBreedte;
        iH=iHoogte;

        frame = new JFrame();

        canvas = new CanvasPanel();
        canvas.setPreferredSize(new Dimension(iBreedte,iHoogte));

        frame.setContentPane(canvas);
        frame.setBounds(iSchermPosX,iSchermPosY,iBreedte,iHoogte);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
        canvasImage = canvas.createImage(iBreedte,iHoogte);
            
        graphic = (Graphics2D)canvasImage.getGraphics();
        graphic.setColor(Color.black);
        graphic.fillRect(0,0,iBreedte,iHoogte);

        frame.setVisible(true);
        
        xObjectBuilded  =   true;
        xDrawInit       =   true;
    }

    public void setAxis(    String title,
                            int SpanX,
                            int MinX,
                            String DimX,
                            int SpanY,
                            int MinY,
                            String DimY ) {
        frame.setTitle(title);
        
        dXPixelsPerUnit = (double) iB/SpanX;
        iXminIn = MinX;
        iXminPx = (int) ((double) MinX * dXPixelsPerUnit);
        iXmaxIn = MinX + SpanX;
        iXmaxPx = (int) ((double) (MinX + SpanX) * dXPixelsPerUnit);
        
        dYPixelsPerUnit = (double) iH/SpanY;
        iYminIn = MinY;
        iYminPx = (int) ((double) MinY * dYPixelsPerUnit);
        iYmaxIn = MinY + SpanY;
        iYmaxPx = (int) ((double) (MinY + SpanY) * dYPixelsPerUnit);
    }
    public void setColor(Color c) {
        graphic.setColor(c);
    }
    public void plotXaxis() {
        plotLine( iXminIn+20,       0, iXmaxIn-20,       0 );
    }
    public void plotYaxis() {
        plotLine(       0, iYminIn+20,       0, iYmaxIn-20 );
    }

        public void plotGrid() {
        }
    
    public void plotRectangle(int x, int y, int b, int h ) {
        int xx = (int) ( (double) (x * dXPixelsPerUnit) );
        int bb = (int) ( (double) (b * dXPixelsPerUnit) );
        
        int yy = (int) ( (double) (y * dYPixelsPerUnit) );
        int hh = (int) ( (double) (h * dYPixelsPerUnit) );
        
        graphic.drawRect( xx-iXminPx, iH-hh+iYminPx-yy, bb, hh );
    }

    public void plotPixel(int x, int y ) {
        int xx = (int) ( (double) (x * dXPixelsPerUnit) );
        
        int yy = (int) ( (double) (y * dYPixelsPerUnit) );
        
        graphic.drawLine( xx-iXminPx, iH+iYminPx-yy, xx-iXminPx, iH+iYminPx-yy );
    }
    
    public void plotLine(int x1, int y1, int x2, int y2 ) {
        int xx1 = (int) ( (double) (x1 * dXPixelsPerUnit) );
        int xx2 = (int) ( (double) (x2 * dXPixelsPerUnit) );

        int yy1 = (int) ( (double) (y1 * dYPixelsPerUnit) );
        int yy2 = (int) ( (double) (y2 * dYPixelsPerUnit) );
        
        graphic.drawLine(xx1-iXminPx, iH+iYminPx-yy1, xx2-iXminPx, iH+iYminPx-yy2);
    }

    public void plotString(String s, int x, int y) {
        int xx = (int) ( (double) (x * dXPixelsPerUnit) );
        
        int yy = (int) ( (double) (y * dYPixelsPerUnit) );
        
        graphic.drawString(s, (int) xx-iXminPx, iH+iYminPx-yy);
    }
    public void plotBorder(int d) {
        graphic.drawRect( d, d, iB-2*d, iH-2*d);
    }
    public void plotArrowLeft(int x, int y) {
        plotLine(x-16,  y+4,    x, y);
        plotLine(x-16,  y-4,    x, y);
        plotLine(x-32,    y, x-16, y);
    }
    public void plotArrowRight(int x, int y) {
        plotLine(x+16,  y+4,    x, y);
        plotLine(x+16,  y-4,    x, y);
        plotLine(x+32,    y, x+16, y);
    }
    public void plotSensor(int x, int y) {
        plotRectangle   (x-6,    y, 12,   10);
        plotLine        (  x, y, x, y-3);
    }
//     private int plotInteger(int newn, int prevn, int x, int y) {
//         if (newn!=prevn) {
//             setColor(Color.black);
//             String s=String.format("% 5d", prevn);
//             plotString(s, x, iXmax/2-y);
//             setColor(Color.white);
//             s=String.format("% 5d", newn);
//             plotString(s, x, iXmax/2-y);
//         }
//         return(newn);
//     }
//     private double plotDouble(double newn, String format, double prevn, int x, int y) {
//         if (newn!=prevn) {
// //             graf.setColor(Color.black);
//             DecimalFormat df = new DecimalFormat(format);
// //             graf.drawString(df.format(prevn), x, h-y);
// //             graf.setColor(Color.white);
// //             graf.drawString(df.format(newn), x, h-y);
//         }
//         return(newn);
//     }
//     private void drawString(String s, int x, int y) {
// //         graf.drawString(s, x, h/2-y);
//     }
//     private void fillRect(int x, int y, int l, int b){ 
//         for(int i=1; i<=b; i++) {
//             plotLine(x,y+i,x+l,y+i);
//         }
//     }
//     private double drwDouble(double var, double db,String format,int x, int y){
//         double db_ret = plotDouble(var, format, db, x, y);
//         return(db_ret);
//     }
    public void watchdog(){
        graphic.setColor( Color.black );
        graphic.drawRect(wd_dx,8,5,5);
        wd_dx=wd_dx+5;
        if (wd_dx>30) {
            wd_dx=8;
        }
        graphic.setColor( Color.white );
        graphic.drawRect(wd_dx,8,5,5);
    }
    public void refreshcanvas() {
        canvas.repaint();
    }
    private class CanvasPanel extends JPanel{
        public void paint(Graphics g) {
            t0 = System.nanoTime();
            g.drawImage(canvasImage, 0, 0, null);
            t = System.nanoTime() - t0;
        }
    }
}

