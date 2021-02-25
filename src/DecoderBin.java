import java.io.*;  // for ByteArrayInputStream
import java.net.*; // for DatagramPacket

public class DecoderBin implements Decoder, ReqBinConst {

  private String encoding;  // Character encoding

  public DecoderBin() {
    encoding = DEFAULT_ENCODING;
  }

  public DecoderBin(String encoding) {
    this.encoding = encoding;
  }




  public Request decodeRequest(DataInputStream wire) throws Exception {
    byte TML, ID, x, numThree, numTwo, numOne, numZero;
    byte len = 7;
    byte[] b = new byte[len];
    wire.readFully(b);
    TML = b[0];
    ID = b[1];
    x = b[2];
    numThree = b[3];
    numTwo = b[4];
    numOne = b[5];
    numZero = b[6];

//    ID = wire.readByte();
//    x = wire.readByte();
//    numThree = wire.readByte();
//    numTwo = wire.readByte();
//    numOne = wire.readByte();
//    numZero = wire.readByte();


    return new Request(TML, ID, x, numThree, numTwo, numOne, numZero);

  }

  public Response decodeResponse(DataInputStream wire) throws Exception {
    System.out.println("decoding response...");
    byte TML = wire.readByte();
    byte ID = wire.readByte();
    byte eC = wire.readByte();
    byte result = wire.readByte();
    byte checksum = 0;

    return new Response(TML,ID,eC,result,checksum);
  }
}
