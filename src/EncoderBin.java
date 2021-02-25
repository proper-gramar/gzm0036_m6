import java.io.*;  // for ByteArrayOutputStream and DataOutputStream

public class EncoderBin implements Encoder, ReqBinConst {

  private String encoding;  // Character encoding

  public EncoderBin() {
    encoding = DEFAULT_ENCODING;
  }



  @Override
  public byte[] encodeRequest(Request request) throws Exception {
    System.out.println("\nencoding request...");
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(buf);

    out.writeByte(request.TML);
    out.writeByte(request.ID);
    out.writeByte(request.x);
    out.writeByte(request.numThree);
    out.writeByte(request.numTwo);
    out.writeByte(request.numOne);
    out.writeByte(request.numZero);
    out.writeByte(request.checkSum);
    out.flush();
    return buf.toByteArray();
  }

  @Override
  public byte[] encodeResponse(Response response) throws Exception {
    System.out.println("encoding response...");
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(buf);

    out.writeByte(response.TML); //TML
    out.writeByte(response.ID);
    out.writeByte(response.errorCode);
    out.writeByte(response.result);
    out.writeByte(69); //checksum
    out.flush();
    System.out.println("sent.");
    return buf.toByteArray();
  }
}
