package main;

import mod.Msg;

//This is the main class which holds all the methods needed for the code

public class main {
	
	//this is a private instance variable which will hold the actual lightboard
	private boolean[][] lights;
	
	//This is the main method which is where the code starts
	public static void main(String[] args0) {
		openPage();
	}
	
	//This method has the user pick the number of rows and columns and then randomly fills in the the light board with those parameters and shows it to the user. It then calls on the 'editPage' method.
	public static void openPage() {
		String r = Msg.in("Pick a number of rows from 1-20:");
		int row = Integer.parseInt(r);
		String c = Msg.in("Pick a number of columns from 1-40:");
		int col = Integer.parseInt(c);
		if (row > 20 || col > 40) {
			Msg.msg("Sorry! Your number(s) have to be  < 10");
			openPage();
		}
		else {
			boolean[][] lights = new boolean[row][col];
			for (int i = 0; i < lights.length; i++) {
				for (int j = 0; j < lights[0].length; j++) {
					double rnd = Math.random();
					if (rnd >= 0.40) {
						lights[i][j] = true;
					}
					else {
						lights[i][j] = false;
					}
				}
			}
			Msg.msg("Here is your light board: " + "\n" + "the '1' means the light is ON and the '0' means its OFF" + "\n" + drawBoard(lights) + "Click 'Ok' to continue:");
			editPage(drawBoard(lights), lights);
		}
	}
	
	//This method returns the light board as a String. It does this by assigning a String value to 'false' and another string value to 'true'.  
	public static String drawBoard(boolean[][] lB) {
		String on = "1";
		String off = "0";
		String space = " ";
		String BOARD = "";
		for (int r = 0; r < lB.length; r++) {
			for (int c = 0; c < lB[0].length; c++) {
				if (lB[r][c]) {
					BOARD += on + space;
				}
				else {
					BOARD += off + space;
				}
			}
			BOARD += "\n";
		}
		return BOARD;
	}
	
	//This is where the user will be able to edit the light board as much as they would like. They are given 8 options 
	//to choose from and depending on which button they click, the code will be directed to a different method.
	public static void editPage(String drawnB, boolean[][] lB) {
		String[] options = {"a Row", "a Column", "a single light", "the entire grid", "a certain section", "random", "criss cross", "exit"};
		int opts = 0;
		opts = Msg.opt(options, drawnB + "\n" +"What would you like to edit on your LightBoard: ", "LightBoard");
		if (opts == 0) {
			entireRow(drawnB, lB);
		}
		else if (opts == 1) {
			entireCol(drawnB, lB);
		}
		else if (opts == 2) {
			singleLight(drawnB, lB);
		}
		else if (opts == 3) {
			entireGrid(drawnB, lB);
		}
		else if (opts == 4) {
			cSection(drawnB, lB);
		}
		else if (opts == 5) {
			random(drawnB, lB);
		}
		else if (opts == 6) {
			crissCross(drawnB, lB);
		}
		else if (opts == 7) {
			exit(drawnB, lB);
		}
	}
	
	//This method lets the user edit an entire row of lights. It allows the user to choose whether they want the entire row to be on or off.
	public static void entireRow(String drawnB, boolean[][] lB) {
		String ro = Msg.in(drawnB + "\n" + "What row would you like to edit? " + "\n" + "(keep in mind that the first row is considered 'row 0')");
		int row = Integer.parseInt(ro);
		String[] options = {"On", "Off"};
		int opts = 0;
		opts = Msg.opt(options, "Would you like these lights to be turned on or off?", "LightBoard");
		if (opts == 0) {
			for (int r = 0; r < lB.length; r++) {
				for (int c = 0; c < lB[0].length; c++) {
					if (r == row) {
						lB[r][c] = true;
					}
				}
			}
		}
		else if (opts == 1) {
			for (int r = 0; r < lB.length; r++) {
				for (int c = 0; c < lB[0].length; c++) {
					if (r == row) {
						lB[r][c] = false;
					}
				}
			}
		}
		
		String newB = drawBoard(lB);
		editPage(newB, lB);
	}
	
	//This method lets the user edit an entire column of lights. It allows the user to choose whether they want the entire column to be on or off.
	public static void entireCol(String drawnB, boolean[][] lB) {
		String co = Msg.in(drawnB + "\n" + "What column would you like to edit? " + "\n" + "(keep in mind that the first column is considered 'column 0')");
		int col = Integer.parseInt(co);
		String[] options = {"On", "Off"};
		int opts = 0;
		opts = Msg.opt(options, "Would you like these lights to be turned on or off?", "LightBoard");
		if (opts == 0) {
			for (int r = 0; r < lB.length; r++) {
				for (int c = 0; c < lB[0].length; c++) {
					if (c == col) {
						lB[r][c] = true;
					}
				}
			}
		}
		else if (opts == 1) {
			for (int r = 0; r < lB.length; r++) {
				for (int c = 0; c < lB[0].length; c++) {
					if (c == col) {
						lB[r][c] = false;
					}
				}
			}
		}
		
		String newB = drawBoard(lB);
		editPage(newB, lB);
	}
	
	//This method lets the user edit a singular light. It allows the user to choose whether they want the light to be on or off.
	public static void singleLight(String drawnB, boolean[][] lB) {
		String ro = Msg.in(drawnB + "\n" + "What row would you like to edit? " + "\n" + "(keep in mind that the first row is considered 'row 0')");
		int row = Integer.parseInt(ro);
		String co = Msg.in(drawnB + "\n" + "What column would you like to edit? " + "\n" + "(keep in mind that the first column is considered 'column 0')");
		int col = Integer.parseInt(co);
		String[] options = {"On", "Off"};
		int opts = 0;
		opts = Msg.opt(options, "Would you like this light to be turned on or off?", "LightBoard");
		if (opts == 0) {
			for (int r = 0; r < lB.length; r++) {
				for (int c = 0; c < lB[0].length; c++) {
					if (c == col && r == row) {
						lB[r][c] = true;
					}
				}
			}
		}
		else if (opts == 1) {
			for (int r = 0; r < lB.length; r++) {
				for (int c = 0; c < lB[0].length; c++) {
					if (c == col && r == row) {
						lB[r][c] = false;
					}
				}
			}
		}
		
		String newB = drawBoard(lB);
		editPage(newB, lB);
	}
	
	//This method lets the user edit the entire grid of lights. It allows the user to choose whether they want the entire grid to be on or off.
	public static void entireGrid(String drawnB, boolean[][] lB) {
		String[] options = {"On", "Off"};
		int opts = 0;
		opts = Msg.opt(options, "Would you like the lights to be turned on or off?", "LightBoard");
		if (opts == 0) {
			for (int r = 0; r < lB.length; r++) {
				for (int c = 0; c < lB[0].length; c++) {
					lB[r][c] = true;
				}
			}
		}
		else if (opts == 1) {
			for (int r = 0; r < lB.length; r++) {
				for (int c = 0; c < lB[0].length; c++) {
					lB[r][c] = false;
				}
			}
		}
		String newB = drawBoard(lB);
		editPage(newB, lB);
	}
		
	//This method lets the user edit a specified section of lights. It allows the user to choose whether they want this section to be on or off.
	public static void cSection(String drawnB, boolean[][] lB) {
		String sRo = Msg.in(drawnB + "\n" + "What start row would you like? " + "\n" + "(keep in mind that the first row is considered 'row 0')");
		int sRow = Integer.parseInt(sRo);
		String sCo = Msg.in(drawnB + "\n" + "What start column would you like? " + "\n" + "(keep in mind that the first column is considered 'column 0')");
		int sCol = Integer.parseInt(sCo);
		String eRo = Msg.in(drawnB + "\n" + "What end row would you like? " + "\n" + "(keep in mind that the first row is considered 'row 0')");
		int eRow = Integer.parseInt(eRo);
		String eCo = Msg.in(drawnB + "\n" + "What end column would you like? " + "\n" + "(keep in mind that the first column is considered 'column 0')");
		int eCol = Integer.parseInt(eCo);
		String[] options = {"On", "Off"};
		int opts = 0;
		opts = Msg.opt(options, "Would you like these lights to be turned on or off?", "LightBoard");
		if (opts == 0) {
			for (int r = 0; r < lB.length; r++) {
				for (int c = 0; c < lB[0].length; c++) {
					if (r == sRow && c == sCol) {
						for (int r1 = sRow; r1 <= eRow; r1++) {
							for (int c1 = sCol; c1 <= eCol; c1++) {
								lB[r1][c1] = true;
							}
						}
					}
				}
			}
		}
		else if (opts == 1) {
			for (int r = 0; r < lB.length; r++) {
				for (int c = 0; c < lB[0].length; c++) {
					if (r == sRow && c == sCol) {
						for (int r1 = sRow; r1 <= eRow; r1++) {
							for (int c1 = sCol; c1 <= eCol; c1++) {
								lB[r1][c1] = false;
							}
						}
					}
				}
			}
		}
		
		String newB = drawBoard(lB);
		editPage(newB, lB);
	}
	
	//This method makes a random combination of lights that are on and lights that are off.
	public static void random(String drawnB, boolean[][] lB) {
		for (int r = 0; r < lB.length; r++) {
			for (int c = 0; c < lB[0].length; c++) {
				double rnd = Math.random();
				if (rnd >= 0.50) {
					lB[r][c] = true;
				}
				else {
					lB[r][c] = false;
				}
			}
		}
		String newB = drawBoard(lB);
		editPage(newB, lB);
	}
	
	//This method lets the user create a crisscross with the lights.
	public static void crissCross(String drawnB, boolean[][] lB) {
		for (int r = 0; r < lB.length; r++) {
			for (int c = 0; c < lB[0].length; c++) {
				if (r % 2 == 0) {
					if (c % 2 == 0) {
						lB[r][c] = true;
					}
					else {
						lB[r][c] = false;
					}
				}
				else {
					if (c % 2 != 0) {
						lB[r][c] = true;
					}
					else {
						lB[r][c] = false;
					}
				}
			}
		}
		String newB = drawBoard(lB);
		editPage(newB, lB);
	}
	
	//This is the exit method which is what runs when the user want to exit the program
	public static void exit(String drawnB, boolean[][] lB) {
		String[] options = {"Yes", "No"};
		int opts = 0;
		opts = Msg.opt(options, "Are you sure you would like to exit?", "LightBoard");
		if (opts == 0) {
			Msg.msg("GOODBYE!");
		}
		if (opts == 1) {
			String[] option = {"current", "new"};
			int opt = 0;
			opt = Msg.opt(option, "Would you liked to be sent back to your current board or would u like to create a new one?", "LightBoard");
			if (opt == 0) {
				editPage(drawnB, lB);
			}
			else if (opt == 1) {
				openPage();
			}
		}
	}

	
}
