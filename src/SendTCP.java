import java.io.*;   // for Input/OutputStream
import java.net.*;  // for Socket
import java.util.Scanner;


//TODO
//build out encoder decoder classes



public class SendTCP {

  public static void main(String args[]) throws Exception {

    long reqID = 1;
    boolean finished = false;
    while (!finished) {
      if (args.length != 2 && args.length != 3)  // Test for correct # of args
      {
        throw new IllegalArgumentException("Parameter(s): <Destination>" +
                " <Port> [<encoding]");

      }

      Scanner scan = new Scanner(System.in);



      InetAddress destAddr = InetAddress.getByName(args[0]);  // Destination address default: 127.0.0.1
      int destPort = Integer.parseInt(args[1]);// Destination port 10100


      reqID+= 1; //increment our requestID
      Socket sock = new Socket(destAddr, destPort);

      //prompts user for input, creates request object
      Request liveRequest = newRequest((byte) reqID);
      EncoderBin reqEncoder = new EncoderBin();
      DecoderBin respDecoder = new DecoderBin();

      long startTime = System.nanoTime();
      OutputStream out = sock.getOutputStream(); // Get a handle onto Output Stream
      out.write(reqEncoder.encodeRequest(liveRequest)); // Encode the REQUEST and send

      //gets data and returns
      //DECODES OUR PACKET
      Response result = respDecoder.decodeResponse(new DataInputStream(sock.getInputStream()));
      long endTime = System.nanoTime();
      //store response in byte array & print
      byte[] bArray = {9, result.ID, result.errorCode, result.result, result.checksum};
      System.out.println("\nresponse: TML, ID, error code, result, checksum");


      //parse and return the resulting packet

      System.out.println(result.TML + " " + result.ID + " " + result.errorCode
              +  " " + result.result + " " + 00);

      long totalTime = (endTime - startTime) /1000000;
      System.out.println("time elapsed: " + totalTime + "ms");

      System.out.print("continue? 'y' to continue, 'n' to quit: ");
      String answer = scan.nextLine();
      if(!(answer.equals("y") || (answer.equals("Y")))){
        sock.close();
        finished = true;
      }

    }
  }


  public static Request newRequest(byte reqID){
    Request equation;
    while(true) {

      Scanner scan = new Scanner(System.in);
      System.out.println("equation template: (a3 * x^3) + (a2 * x^2) + (a1 * x) + a0");
      System.out.println("enter desired 'x' value: ");
      String sX = scan.nextLine();
      System.out.println("enter a3: ");
      String sNum3 = scan.nextLine();
      System.out.println("enter a2: ");
      String sNum2 = scan.nextLine();
      System.out.println("enter a1: ");
      String sNum1 = scan.nextLine();
      System.out.println("enter a0: ");
      String sNum0 = scan.nextLine();
      System.out.println();

      byte x = Byte.parseByte(sX);
      byte num3 = Byte.parseByte(sNum3);
      byte num2 = Byte.parseByte(sNum2);
      byte num1 = Byte.parseByte(sNum1);
      byte num0 = Byte.parseByte(sNum0);
      //byte checksum = 00;

      byte[] barray = {reqID, num3, num2, num1, num0};
      byte TML = (byte) (barray.length);
      byte[] barray1 = {TML, reqID, x, num3, num2, num1, num0};
      //print out entered data & hexadecimal representation
      System.out.println("x = " + x);
      System.out.println("equation entered: (" + num3 + " * " + x + "^3) + (" + num2 + " * " + x
              + "^2) + (" + num1 + " * " + x + ") + " + num0);
      byteToHexadecimal(barray1);
      equation = new Request(TML, reqID, x, num3, num2, num1, num0);

      return equation;

    }
  }

  public static void byteToHexadecimal(byte[] barray1){
    barray1[0] += 4;
    System.out.print("hex: ");
    for (byte i : barray1){
      String hex = String.format("%02X", i);
      System.out.print("0x" + hex + " ");

    }
    System.out.print("00");
    System.out.println();

  }

  //Prints out result array
  //Passed in response is already decoded...
  public static void printo(Response response) throws Exception {
    //RESPONSE TO STRING REPRESENTATION
    System.out.println(9 + " " + response.ID + " " + response.errorCode + " " + response.result
            + " " + 6969);


  }

}