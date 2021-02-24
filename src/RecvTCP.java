import java.io.OutputStream;
import java.net.*;  // for Socket and ServerSocket
import java.util.Arrays;

public class RecvTCP {

  public static void main(String args[]) throws Exception {


      if (args.length != 1)  // Test for correct # of args
        throw new IllegalArgumentException("Parameter(s): <Port>");

      int port = Integer.parseInt(args[0]);   // Receiving Port
      System.out.println("Waiting for connection...");
      ServerSocket servSock = new ServerSocket(port);
      Socket clntSock = servSock.accept(); //address to send back to client
      System.out.println("Handling client at "
              + clntSock.getInetAddress() + " on port " + clntSock.getPort());
      RequestDecoderBin decoder = new RequestDecoderBin();
      ResponseEncoderBin encoder = new ResponseEncoderBin();

      //gets input stream and decode
      System.out.println("before decoder");
      Request receivedRequest = decoder.decode(clntSock.getInputStream()); //HANGING HERE
      System.out.println("after decoder");
      System.out.println("Received Request: " + receivedRequest.toString());

      //computes and returns a response
      Response resp = compute(receivedRequest);
      System.out.println("computed result: " + resp.toString());

      //prep and send packet back
      OutputStream out = clntSock.getOutputStream();

      //encode and sent
      out.write(encoder.encode(resp));


//      servSock.close();
//      servSock.close();


  }

  //computes statement
  public static Response compute(Request req){

    System.out.println(req.numThree);
    //a3 * x^3 + a2 * x^2 + a1 * x + a0* x
    double result = (req.numThree * Math.pow(req.x,3));
            result+= (req.numTwo * Math.pow(req.x,2));
            result+=(req.numOne * req.x) + req.numZero;
    byte errorCode = 0;
    byte TML = 9;
    byte[] resultArray = new byte[]{req.TML, (byte) req.ID, errorCode, (byte)result, req.checkSum};
    System.out.println(Arrays.toString(resultArray));

    return new Response(TML, (byte) req.ID, errorCode, (byte)result, req.checkSum);

  }

}
