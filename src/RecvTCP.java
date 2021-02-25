import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.*;  // for Socket and ServerSocket
import java.util.Arrays;

public class RecvTCP {

  public static void main(String args[]) throws Exception {


      for(;;){

          if (args.length != 1)  // Test for correct # of args
              throw new IllegalArgumentException("Parameter(s): <Port>");

          int port = Integer.parseInt(args[0]);   // Receiving Port
          System.out.println("Waiting for connection...");
          ServerSocket servSock = new ServerSocket(port);
          Socket clntSock = servSock.accept(); //address to send back to client
          System.out.println("Handling client at "
                  + clntSock.getInetAddress() + " on port " + clntSock.getPort());
          DecoderBin decoder = new DecoderBin();
          EncoderBin encoder = new EncoderBin();

          //gets input stream and decode
          System.out.println("listening for request...");
          Request receivedRequest = decoder.decodeRequest(new DataInputStream(clntSock.getInputStream())); //HANGING HERE
          System.out.println("received request...");

          //computes and returns a response
          Response resp = compute(receivedRequest);
          System.out.println("computed result: " + resp.result);

          //prep and send packet back
          OutputStream out = clntSock.getOutputStream();

          //encode and sent
          out.write(encoder.encodeResponse(resp));


//      servSock.close();
//      servSock.close();

      }
  }

  //computes statement
  public static Response compute(Request req){

    System.out.println(req.numThree);
    //a3 * x^3 + a2 * x^2 + a1 * x + a0* x
    double result = (req.numThree * Math.pow(req.x,3));
            result+= (req.numTwo * Math.pow(req.x,2));
            result+=(req.numOne * req.x) + req.numZero;
    byte errorCode = 0;
    //byte[] resultArray = new byte[]{req.ID, errorCode, (byte)result, req.checkSum};

    return new Response(req.TML, (byte) req.ID, errorCode, (byte)result,  req.checkSum);

  }

}
