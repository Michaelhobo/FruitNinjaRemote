import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSend {

	private static String ip_address = "169.254.14.183";
	private static int port = 9002;
	
	public static void send(double x, double y, boolean release) throws IOException{
		String releaseString = (release) ? "True" : "False";
		sendUDPMessage("[" + x + "," + y + "," + releaseString +"]");
	}
	
    public static void sendUDPMessage(String message) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverIP = InetAddress.getByName(ip_address);
        byte[] outData = (message).getBytes();
        System.out.println(serverIP + ": " + message);
        DatagramPacket out = new DatagramPacket(outData,outData.length, serverIP, port);
        socket.send(out);
        socket.close();
    }
}