import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTestSend {

	private static String ip_address = "192.168.2.106";
	private static int port = 9002;
	
	public static void send(int x, int y) throws IOException{
//		String releaseString = (release) ? "True" : "False";
		sendUDPMessage(x + " " + y);
	}
	
    public static void sendUDPMessage(String message) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverIP = InetAddress.getByName(ip_address);
        byte[] outData = (message).getBytes();
//        System.out.println(serverIP + ": " + message);
        DatagramPacket out = new DatagramPacket(outData,outData.length, serverIP, port);
        socket.send(out);
        socket.close();
    }
    
    public static void main(String[] args){
    	System.out.println("Asdf" + 1);
    	try {
    		for (int i = 0; i < 10; i++) {
				int y = 500;
				for (int x = 200; x <= 600; x += 1) {
					send(x, y);
					Thread.sleep(2);
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}