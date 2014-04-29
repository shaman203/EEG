package test;

import gui.Controller;
import gui.MainGUI;

public class Prog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Controller c = new Controller();
		MainGUI gui = new MainGUI(c);
		c.setGui(gui);
	}

}
