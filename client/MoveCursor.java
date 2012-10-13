import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.*;
import java.net.*;

public class MoveCursor {
	
	private static Robot r;
	private static int port = 9002;
	
	private static int topx = 200;
	private static int topy = 200;
	
	private static int botx = 826;
	private static int boty = 620;
	
	private static int midx = (topx + botx) / 2;
	private static int midy = (topy + boty) / 2;
	
	private static double length = botx - topx;
	private static double height = boty - topy;
	
	private static double swipe_time = 100; // 500 milliseconds		
	
	public static void main(String[] args) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(port);
		byte[] receiveData = new byte[1024];		
		r = new Robot();
		Thread.sleep(1000);
//		r.mousePress(InputEvent.BUTTON1_MASK);
//		moveAbsolute(botx, boty);
//		move(-0.210624163730086, 0.4921884378507483);
//		for(double angle = 0.1; angle < 2*Math.PI; angle += .1){
//			move(angle, 1);
//		}
		while (true) {
			r.mousePress(InputEvent.BUTTON1_MASK);
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			String[] splitsentence = sentence.split("\\s+");
			double theta = Double.parseDouble(splitsentence[0]);
			double intensity = Double.parseDouble(splitsentence[1]);
			System.out.println(theta + ", " + intensity);
			move(theta, intensity);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
		}
		
	}
	
	public static void move(double theta, double intensity) throws InterruptedException{
		double[] segment = intersect(theta);
		double length = length(segment);
		length = intensity * length;
		for(int i = 0; i < 4; i++)
			segment[i] *= intensity;
		
		double deltax = segment[2] - segment[0];
		double deltay = segment[3] - segment[1];
		
		deltax /= swipe_time;
		deltay /= swipe_time;
		
		double curx = segment[0];
		double cury = segment[1];
		
		for(int i = 0; i < swipe_time; i++){
			movepos((int)Math.round(curx), (int)Math.round(cury));
			curx += deltax;
			cury += deltay;
		}
	}
	
	public static void movepos(int x, int y) throws InterruptedException{
		r.mouseMove(x + midx, midy - y);
		Thread.sleep(1);		
	}
	
	private static void moveAbsolute(int x, int y) throws InterruptedException{
		r.mouseMove(x, y);
		Thread.sleep(1);
	}
	
	private static double[] intersect(double theta){
		theta = (theta + 2 * Math.PI) % (2 * Math.PI);

		double tan = Math.tan(theta);
		double alpha = Math.atan(height/length);
		double x, y;
		x = y = 0;
		
		//case 1: ends at right side wall
		if(2*Math.PI-alpha < theta || theta < alpha){
			x = length / 2;
			y = tan * x;
		}
		
		//case 2: ends at top wall
		else if(alpha < theta && theta < Math.PI - alpha){
			y = height / 2;
			x = y / tan;
		}
		
		//case 3: ends at left side wall
		else if (Math.PI - alpha < theta && theta < Math.PI + alpha){
			x = -length / 2;
			y = tan * x;
		}
		
		//case 4: ends at bottom wall
		else if (Math.PI + alpha < theta && theta < 2*Math.PI-alpha){
			y = -height / 2;
			x = y / tan;
		}
		
		double[] segment = {-x, -y, x, y};
		return segment;
	}
	
	private static double length(double[] seg){
		return Math.sqrt(Math.pow(seg[0] - seg[2], 2) + Math.pow(seg[1] - seg[3], 2));
	}
}