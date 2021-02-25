public class Response {
    public byte ID;
    public byte TML;
    public byte errorCode;
    public byte result;
    public byte checksum = 00;

    public Response(byte tmlIn,byte idIn, byte errorIn, byte resultIn, byte checksum){
        this.TML = tmlIn;
        this.ID = idIn;
        this.errorCode = errorIn;
        this.result = resultIn;
    }

//    public String toString() {
//        final String EOLN = java.lang.System.getProperty("line.separator");
//        String value = "Request #" + ID + EOLN;
//        value += "TML = 4, "  + EOLN +
//                "error code = " + errorCode + EOLN +
//                ", result = " + result + EOLN +
//                ", checksum = " + checksum + EOLN;
//
//        return value;
//    }


}
