import java.io.*;   // for InputStream and IOException
import java.net.*;  // for DatagramPacket

public interface Decoder {
  Request decodeRequest(DataInputStream wire) throws IOException, Exception;
  Response decodeResponse(DataInputStream wire) throws IOException, Exception;
}
