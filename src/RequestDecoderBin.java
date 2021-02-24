import java.io.*;  // for ByteArrayInputStream
import java.net.*; // for DatagramPacket

public class RequestDecoderBin implements RequestDecoder, ReqBinConst {

  private String encoding;  // Character encoding

  public RequestDecoderBin() {
    encoding = DEFAULT_ENCODING;
  }

  public RequestDecoderBin(String encoding) {
    this.encoding = encoding;
  }

  public Request decode(DataInputStream wire) throws IOException {
    System.out.println("top of decode method");
    DataInputStream src = new DataInputStream(wire);
    byte ID = src.readByte();
    byte x = src.readByte();
    byte numThree = src.readByte();
    byte numTwo = src.readByte();
    byte numOne = src.readByte();
    byte numZero = src.readByte();
    System.out.println("before returning request");

    return new Request(ID, x, numThree, numTwo, numOne, numZero);
  }

}
