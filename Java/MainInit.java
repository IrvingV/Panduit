import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainInit{
//     Graphics pixel;
    boolean initia              =   true,
            xExecuteOverflow;
    JFrame  grf,
            pr,
            vr;
    
    PAN_Grafiek     panGraphic;  

    Pan_Parameters  par;

    Pan_Variables   var;

int      dStartPosPrev,
            dStopPosPrev,
//             dPos,
            dCollisionArea = 40,
            dDifference;
            
int      dStartPos,
            dStopPos,
            dStartPosCorr,
            dStopPosCorr;
            
int[]    dTieWrapPos,
            dTieWrapPosPrev;

    Double  x           = 0.0,
            dx          = 0.0,
            dt          = 0.0,
            t_cum       = 0.0,
            dpx         = 0.0,
            sp          = 0.0,
            t_PlacePrd  = 0.0,
            time        = 0.0,
//            dPixelsPerMm,
            tempdouble;
    
    Integer       temp_Integer; 
            
    int     iXSpan  = 2050,
            iXMin   = -200,
            iYSpan  =  200,
            iYMin   =  -50,
          
            t5sec,
            iPos,
            i,
            j,
            dx_int      = 0,
            dpx_int     = 0,
            sp_int      = 0,
            vp=0,
            d1,
            d2,
            wd_dx;
    
    long    t0          = System.nanoTime(),
            t1,
            t2;
    
    Long    t4          = System.nanoTime();
    
    public MainInit(){
        
        panGraphic  = new PAN_Grafiek( 0,0, 1010, 240);
        panGraphic.setAxis("Tie-Wrap", iXSpan, iXMin, "mm", iYSpan, iYMin, "mm" );  

        par = new Pan_Parameters();

        pr  = new JFrame("Parameters");
        pr  . setBounds(  0,   270, 360, 280);
        pr  . setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pr  . add(par);
        pr  . setVisible(true);

        
        var = new Pan_Variables();
        vr  = new JFrame("Variabelen");
        vr  . setBounds(  360, 270, 360, 280);
        vr  . setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vr  . add(var);
        vr  . setVisible(true);
        
       dTieWrapPos      = new int[5]; 
       dTieWrapPosPrev  = new int[5]; 
    }
    
    private void Init() {

        panGraphic.setColor(Color.gray);

        panGraphic.plotXaxis();
        panGraphic.plotYaxis();

        panGraphic.setColor(Color.yellow);

        // ************************
        // *** Vertical grids   ***
        // *** x-axis numbering ***
        // ************************
    
        for (int i=0; i<2000; i=i+150) {
            for (int j=0; j<15; j++) {
                panGraphic.plotLine(i, 110-j*6, i, 110-j*6);
            }
            temp_Integer    =   i; 
            panGraphic.plotString(temp_Integer.toString(),i-20,-15);
        } 
        
        // ************************
        // *** Horizontal grids ***
        // ************************
        
        for (int i=5; i<2000; i=i+15) { 
            panGraphic.plotLine(i, 90, i, 90);
            panGraphic.plotLine(i, 40, i, 40);
        }

        // **************
        // *** Border ***
        // **************
        
        panGraphic.plotBorder(2);
        panGraphic.plotBorder(5);

//         panGraphic.plotString("cycle"          , 100,  10); panGraphic.plotString("ms", 255, 10); panGraphic.plotString("mm", 355, 10);
//         panGraphic.plotString("product"        , 100,  30); panGraphic.plotString("ms", 255, 30); panGraphic.plotString("mm", 355, 30);
//         panGraphic.plotString("gap"            , 100,  50); panGraphic.plotString("ms", 255, 50); panGraphic.plotString("mm", 355, 50);
//         panGraphic.plotString("between sensors", 100,  70); panGraphic.plotString("ms", 255, 70); panGraphic.plotString("mm", 355, 70);
//         
//         panGraphic.plotString("time/div"       , 100,   90);       panGraphic.plotString("ms/div", 250, 90);
//         panGraphic.plotString("Samples/div"    , 400,   0);  
//         panGraphic.plotString("dx per sample"  , 0,    -50);       panGraphic.plotString("mm",     250,  -50);
    }
        
    public void MainLoop() {
        
        while (true){
            t1=System.nanoTime();
            if ((t1 > t0 + (long)par.tab4.prgt.val.doubleValue()*1000000) && panGraphic.xObjectBuilded) {
                
                // *****************************
                // *** cycletime measurement ***
                // *****************************
                
                t2=System.nanoTime();
                t0=t1;
                
                
                
                
                // ********************
                // *** place arrows ***
                // ********************

                dStartPos   = par.tab1.par11.val.intValue();
                if ( dStartPos!=dStartPosPrev) {
                    panGraphic.setColor(Color.black);
                    panGraphic.plotArrowLeft((int)dStartPosPrev,-30);
                    panGraphic.setColor(Color.blue);
                    panGraphic.plotArrowLeft( dStartPos ,-30);
                    dStartPosPrev = dStartPos;
                }
                    
                dStopPos   = par.tab1.par12.val.intValue();
                if ( dStopPos!=dStopPosPrev) {
                    panGraphic.setColor(Color.black);
                    panGraphic.plotArrowRight((int)dStopPosPrev,-30);
                    panGraphic.setColor(Color.blue);
                    panGraphic.plotArrowRight(dStopPos,-30);
                    dStopPosPrev = dStopPos;
                }
               
                
                

                // ******************************
                // *** controleer op collison ***
                // ******************************
                
//                 dCollisionArea = par.tab1.par12.val.intValue();
                
                dStartPosCorr=dStartPos;
                if ( dStartPos % 150 >= 150 - dCollisionArea ) {
                    dStartPosCorr = (dStartPos/150) * 150 + 150 + dCollisionArea;
                }
                if (  dStartPos % 150 < dCollisionArea ){
                    dStartPosCorr = (dStartPos / 150) * 150 + dCollisionArea;
                }
                
                dStopPosCorr=dStopPos;
                if ( dStopPos % 150 >= 150 - dCollisionArea ) {
                    dStopPosCorr = (dStopPos/150) * 150 + 150 - dCollisionArea;
                }
                if (  dStopPos % 150 < dCollisionArea ){
                    dStopPosCorr = (dStopPos/150) * 150       - dCollisionArea;
                }

                
                
                // *******************************
                // *** bepaal tie-wrap psities ***
                // *******************************
                
                for (int i=1; i<=4; i++) { 
                    dTieWrapPos[i]   = 0;
                }
                
                dDifference =dStopPosCorr-dStartPosCorr;
                
                if (dDifference <50 ) {
                    dTieWrapPos[1] = dStartPosCorr;
                }
                else {
                    if (dDifference < 450) {
                        dTieWrapPos[1] = dStartPosCorr;
                        dTieWrapPos[2] = dStopPosCorr;
                    }
                    else {
                        if (dDifference < 900) {
                            dTieWrapPos[1] = dStartPosCorr;
                            dTieWrapPos[2] = dStartPosCorr + 300;
                            dTieWrapPos[3] = dStopPosCorr;
                        }
                        else {
                            if (dDifference < 1350) {
                                dTieWrapPos[1] = dStartPosCorr;
                                dTieWrapPos[2] = dStartPosCorr + 300;
                                dTieWrapPos[3] = dStopPosCorr -300;
                                dTieWrapPos[4] = dStopPosCorr;
                            }
                            else {
                                dTieWrapPos[1] = dStartPosCorr;
                                dTieWrapPos[2] = dStartPosCorr + 450;
                                dTieWrapPos[3] = dStopPosCorr -450;
                                dTieWrapPos[4] = dStopPosCorr;
                            }
                        }
                    }
                }

                
                
                
                // **********************
                // *** plot resultaat ***
                // **********************
                
                for (int i=1; i<=4; i++) { 
                    if ( dTieWrapPos[i]!=dTieWrapPosPrev[i]) {
                        panGraphic.setColor(Color.black);
                        panGraphic.plotSensor((int) dTieWrapPosPrev[i],10);
                        panGraphic.setColor(Color.blue);
                        panGraphic.plotSensor((int) dTieWrapPos[i],10);
                        dTieWrapPosPrev[i] = dTieWrapPos[i];
                    }
                }

                
                
                
                // ***********************
                // *** display waarden ***
                // ***********************
                
                var.tab1.var11.val = dStartPos;
                var.tab1.var12.val = dStartPosCorr;
                var.tab1.var13.val = dStopPos;
                var.tab1.var14.val = dStopPosCorr;
                var.tab1.var15.val = dTieWrapPos[1];
                var.tab1.var16.val = dTieWrapPos[2];
                var.tab1.var17.val = dTieWrapPos[3];
                var.tab1.var18.val = dTieWrapPos[4];

                
                
                
                // ****************************
                // *** display cyclustijden ***
                // ****************************
                
                var.tab2.t2.val= panGraphic.t/1000.0;
                if (var.tab2.t2min.val > var.tab2.t2.val) var.tab2.t2min.val = var.tab2.t2.val ;    
                if (var.tab2.t2max.val < var.tab2.t2.val) var.tab2.t2max.val = var.tab2.t2.val ;    
                t5sec=t5sec + par.tab4.prgt.val.intValue();
                if (t5sec >5000){
                    var.tab2.t1min.val = var.tab2.t1.val;
                    var.tab2.t1max.val = var.tab2.t1.val;
                    var.tab2.t2min.val = var.tab2.t2.val;
                    var.tab2.t2max.val = var.tab2.t2.val;
                    t5sec=0;
                }
                    var.tab2.dt.val = dt;
                    var.tab2.dx.val = dx;

                par.tab4.mest.setSliderMax(par.tab4.prgt.val.intValue()*1000); // set level detection
                 
                par.update();
                var.update();
//                  
//                 var.tab1.toff.val=-t4.doubleValue()/1000000;
// 
//                 t_PlacePrd = (60/(double)par.tab1.sprd.val)*1000;
//                 time=time+par.tab4.prgt.val;
//                 if (time>t_PlacePrd) {
//                     time = time - t_PlacePrd;
//                     cnvs.lprd=par.tab1.lprd.val.intValue();
//                     cnvs.placeProduct =true;
//                     shi.write(0,par.tab1.lprd.val.intValue()/reso,true);
//                 }
//                     var.tab2.dtp.val = t_PlacePrd;
// 
                t4=System.nanoTime()-t2;
                tempdouble=t4.doubleValue()/1000.0;
                par.tab4.mest.valInt = tempdouble.intValue();
                par.tab4.mest.valDouble = t4.doubleValue()/1000;
                xExecuteOverflow = (t4.doubleValue()/1000)>(par.tab4.prgt.val.doubleValue()*1000);

//                 canvas.paint(canvas.graf);

                panGraphic.setColor(Color.white);
//                 panGraphic.plotLine(10,10,90,90);
//                 panGraphic.plotRectangle(5,5,100,100);

                panGraphic.watchdog();
// canvas.repaint();

//                 panGraphic.plotLine(50,50,0, 0);
//                 panGraphic.setColor(Color.black);     
//                 panGraphic.plotString("helpie",i,50);           
//                 i=i+10;
//                 if (i>100) { i=0;}
//                 panGraphic.setColor(Color.white);                           
//                 panGraphic.plotString("helpie",i,50);



                if (initia) {
                    Init();
                    initia=false;
                }
                panGraphic.refreshcanvas();
//                 panGraphic.setAxis( -750, -120);
            }
        }
    }


}

