import java.io.*;   // for InputStream and IOException
import java.net.*;  // for DatagramPacket

public interface RequestDecoder {
  Request encode(InputStream source) throws IOException;
  Request decode(InputStream source) throws IOException;

}
