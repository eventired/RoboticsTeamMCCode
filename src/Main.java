import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.*;

public class Main {
	static Scanner read = new Scanner(System.in);
	static Robot robot;
	static int finalXP, currentXP; 
	static float seconds;
	static int timer;
	
	public static void main(String[] args) throws AWTException, InterruptedException {
		/*
		   Important information!
		   The rate for mobs is about 1 mob / 5 seconds
		  
			Total EXP needed to reach level N (starting from 1)		Level interval
			N2 + 6 N												1 to 16
			2.5 N2 - 40.5 N + 360									17 to 31
			4.5 N2 - 27.9 N											32 to 70
			Most mobs in this grinder gives 5 XP
		 */
		
		robot = new Robot();
		
		System.out.println("This code was made by eventiger!");
		System.out.println("Code for finding XP levels are from Mahagon87. \n");
		
		// sets finalXP amount
		System.out.println("Enter desired XP number:");
		finalXP = read.nextInt();
		
		System.out.println("Enter current XP number:");
		currentXP = read.nextInt();
		
		seconds = getXPfromLevel(finalXP)/5; // find mobs needed to kill
		seconds -= getXPfromLevel(currentXP)/5;
		seconds *= 5; // multiply by 5 to get amount of time
		
		System.out.println("\n This process will take " + seconds + " seconds!");
		System.out.print("Starting in 5 seconds...");
		// starts delay to open Minecraft
		Thread.sleep(5000);
		
		
		// starts loop 
		new Thread(() -> {
			try {
				Thread.sleep((long) seconds * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Program Complete!");
			System.exit(0);
		}).start();
		kill();
		

	}

	public static void kill() throws InterruptedException {	
		for (int i = 1; i <= 10000; i++) {
			for (int r = 1; r <= 8; r++) {
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(625); // 625 is the amount of miliseconds it takes for a sword to "recharge" its hits
				
				// moves to account for mobs not on crosshair 
				robot.keyPress(KeyEvent.VK_D);
				Thread.sleep(50);
				robot.keyRelease(KeyEvent.VK_D);
			}
			
			for (int r = 1; r <= 8; r++) {
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(625); // 625 is the amount of miliseconds it takes for a sword to "recharge" its hits
				
				// moves to account for mobs not on crosshair 
				robot.keyPress(KeyEvent.VK_A);
				Thread.sleep(50);
				robot.keyRelease(KeyEvent.VK_A);
			}
		}
	}
	
	// got this from https://www.reddit.com/r/Minecraft/comments/3k7kk3/mathematical_formula_for_experience/
	public static int getXPfromLevel(int level) {
		if (level >= 1 && level <= 16) {
			
			return (int) (Math.pow(level, 2) + 6 * level);
			
		} else if (level >= 17 && level <= 31) {
			
			return (int) ( 2.5 * Math.pow(level, 2) - 40.5 * level + 360);
			
		} else if (level >= 32) {
			
			return (int) (4.5 * Math.pow(level, 2) - 162.5 * level + 2220);
			
		} else {

			return 0;
			
		}
	}
}
