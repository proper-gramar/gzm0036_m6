
public class Request {

    public long ID;            // Item identification number
    public byte symbol;

    public byte numOne;
    public byte numTwo;
    public byte numThree;
    public byte numZero;
    public byte TML;
    public byte checkSum;
    public byte x;


    public Request(long pID, byte pX, byte pNumThree, byte pNumTwo, byte pNumOne, byte pNumZero)  {
        this.ID = pID;
        this.x = pX;
        this.numZero = pNumZero;
        this.numOne = pNumOne;
        this.numTwo = pNumTwo;
        this.numThree = pNumThree;
    }

    public String toString() {
        final String EOLN = java.lang.System.getProperty("line.separator");
        System.out.println();
        String value = "Request #" + ID + EOLN;
        value += "x = " + x + EOLN +
                "a3 = " + numThree + EOLN +
                "a2 = " + numTwo + EOLN +
                "a1 = " + numOne + EOLN +
                "a0 = " + numZero + EOLN;


        return value;

    }
}
