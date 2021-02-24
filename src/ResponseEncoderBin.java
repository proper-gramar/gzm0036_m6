import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class ResponseEncoderBin implements ResponseEncoder, ResBinConst{
    private String encoding;  // Character encoding

    public ResponseEncoderBin() {
        encoding = DEFAULT_ENCODING;
    }

    public ResponseEncoderBin(String encoding) {
        this.encoding = encoding;
    }

    public byte[] encode(Response response) throws Exception {

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(buf);
        out.writeLong(4); //TML
        out.writeByte(response.ID);
        out.writeByte(response.errorCode);
        out.writeByte(69); //checksum

        out.flush();
        return buf.toByteArray();
    }
}
