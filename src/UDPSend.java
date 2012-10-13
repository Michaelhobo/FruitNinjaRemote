import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSend {

	private static String ip_address = "192.168.135.1";
	private static int port = 9009;
	
    public static void sendUDPMessage(String message) throws java.io.IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverIP = InetAddress.getByName(ip_address);
        System.out.println(serverIP);
        byte[] outData = (message).getBytes();
        DatagramPacket out = new DatagramPacket(outData,outData.length, serverIP, port);
        socket.send(out);
        socket.close();
    }
    
    public static void main(String[] args){
    	System.out.println("Asdf" + 1);
    	try {
    		for(int i = 0; i < 100; i++){
    			sendUDPMessage("Pinging" + i + 1);
    			Thread.sleep(1000);
    		}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	System.out.println("Asdfa");
    }

}