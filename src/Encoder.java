public interface Encoder {
  byte[] encodeRequest(Request request) throws Exception;
  byte[] encodeResponse(Response response) throws Exception;
}
