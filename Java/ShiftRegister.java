 

/**
 * Cyclic shiftregister with moveable pointer
 * 
 * @author Irving Verijdt
 * @version 08aug14
 */
public class ShiftRegister {
    public boolean[] reg;
   
    private int pntr=0;
    private int length;
    
    public ShiftRegister(int n)
    {
        length = n;
        reg  = new boolean[length];
    }
    
    public void shift(int n) {
        for(int p=0; p<n; p++) {
            pntr++;
            if (pntr>=length) pntr=pntr-length;
            reg[pntr]=false;
        }
    }
    
    public void write(int pos, int n, boolean b) {
        int x;
        for(int p=0; p<n; p++) {
            x=pntr-pos-p;
            if (x<0) x=x+length;
            reg[x]=b;
        }
    }
    
    public boolean read(int pos) {
        int n=pntr-pos;
        if (n<0) n=n+length;
        boolean result = reg[n];
        return(result);
    }
}
