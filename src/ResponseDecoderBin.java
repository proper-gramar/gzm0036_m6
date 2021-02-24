import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;

public class ResponseDecoderBin implements ResponseDecoder, ResBinConst{
    private String encoding;  // Character encoding

    public ResponseDecoderBin(){
        encoding = DEFAULT_ENCODING;

    }

    public Response decode(InputStream wire) throws IOException {

        DataInputStream src = new DataInputStream(wire);
        byte TML = src.readByte();
        byte ID = src.readByte();
        byte eC = src.readByte();
        byte result = src.readByte();
        byte checksum = 00;

        return new Response(TML,ID,eC,result,checksum);
    }


}
