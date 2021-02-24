public class Response {
    public byte ID;
    public byte errorCode;
    public byte result;

    public Response(byte TML,byte idIn, byte errorIn, byte resultIn, byte checksum){
        TML = 7;
        this.ID = idIn;
        this.errorCode = errorIn;
        this.result = resultIn;
    }
}
