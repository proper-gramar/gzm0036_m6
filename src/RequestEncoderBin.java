import java.io.*;  // for ByteArrayOutputStream and DataOutputStream

public class RequestEncoderBin implements RequestEncoder, ReqBinConst {

  private String encoding;  // Character encoding

  public RequestEncoderBin() {
    encoding = DEFAULT_ENCODING;
  }


  public void encode(Request request) throws Exception {

    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(buf);

    out.writeByte(request.TML);
    out.writeLong(request.ID);
    out.writeByte(request.x);
    out.writeByte(request.numThree);
    out.writeByte(request.numTwo);
    out.writeByte(request.numOne);
    out.writeByte(request.numZero);
    out.writeByte(request.checkSum);

//    out.flush();
//    return buf.toByteArray();
  }
}
