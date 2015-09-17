package com.game;
import java.util.*;
/**
 * @author NyanOnPc
 *TEST
 *
 */
//Vertical is Up and Down
//Horizontal is Left and Right
public class Battleship {
	static Scanner keyboard = new Scanner(System.in);
	static Scanner numpad   = new Scanner(System.in);

    static boolean buildMode = false;
    static int last_number = 0;


    static String Point;
    //P = Player
    //E = Enemy
    static char p[][] = new char[10][10];
    static char e[][] = new char[10][10];
    

    static boolean[] Boat = new boolean[]{true,true};
    static boolean[] Destroyer = new boolean[]{true,true};
    static boolean[] Submarine = new boolean[]{true,true};
    static boolean[] Battleship = new boolean[]{true,true};
    static boolean[] Carrier = new boolean[]{true,true};




    /*
     * Choices
     * 0 = Patrol Boat(2)
     * 1 = Destroyer(3)
     * 2 = Submarine(3)
     * 3 = Battleship(4)
     * 4 = AircraftCarrier(5)
     */
    static boolean ships[] = new boolean[]{true,true,true,true,true};
    static String choices[] = new String[]{"Boat","Destroyer",
		"Submarine","Battleship",
		"Carrier"};


    public static class Ship{
    	int x;
    	int y;
		int CordX;
		int CordY;
		int size;
		int shipSize;
		String name;
		String shipName;
		String direction;
		String shipDirection;
		public Ship(String name, int x, int y,int size, String direction){
			CordX = x;
			CordY = y;
			shipSize = size;
			shipName = name;
			shipDirection = direction;
		}
		public boolean checkShip(Ship info){
			/*
			 * Checks too see if the ship is going to be placed on another.
			 */
			if (shipDirection.equals("HORIZONTAL") && (p[CordY][CordX-1] == '/') && (shipSize == 2)){ return false;}
			if (shipDirection.equals("VERTICAL") && (shipSize == 2) && (p[CordY-1][CordX] == '/')){return false;}
			if (shipDirection.equals("HORIZONTAL") && (shipSize == 3) && (p[CordY][CordX-1] == '/' || p[CordY-2][CordX-1] == '/')) {return false;}
			if (shipDirection.equals("VERTICAL") && (shipSize == 3) && (p[CordY-1][CordX] == '/' || p[CordY-1][CordX-2] == '/')){return false;}
			if (shipDirection.equals("HORIZONTAL") && (shipSize == 4) && (p[CordY][CordX-1] == '/' || p[CordY-2][CordX-1] == '/' || p[CordY+1][CordX-1] == '/')){return false;}
			if (shipDirection.equals("VERTICAL") && (shipSize == 4) && (p[CordY-1][CordX] == '/' || p[CordY-1][CordX-2] == '/' || p[CordY-1][CordX+1] == '/')){return false;}
			if (shipDirection.equals("HORIZONTAL") && (shipSize == 5) && (p[CordY][CordX-1] == '/' || p[CordY-2][CordX-1] == '/' || p[CordY+1][CordX-1] == '/' || p[CordY+2][CordX-1] == '/')){return false;}
			if (shipDirection.equals("VERTICAL") && (shipSize == 5) && (p[CordY-1][CordX] == '/' || p[CordY-1][CordX-2] == '/' || p[CordY-1][CordX+1] == '/' || p[CordY-1][CordX+2] == '/')){return false;}
			/*
			 * If Cords are out of Bounds
			 * returns back False
			 * Horizontal == left and Right
			 * Vertical == up and Down
			 */
			if (CordX <= 0 || CordX >= 10){
				return false;
			}
			else if (CordY <= 0 || CordY >= 10){
				return false;
			}
			else if (shipDirection.equals("HORIZONTAL")){
				int result = 0;
				result = CordY - shipSize;
				if (result < -1){return false;}
				result = 0;
				result  = CordY + shipSize;
				if (result >= 11){return false;}
				else {return true;}
			}
			else if (shipDirection.equals("VERTICAL")){
				int result = 0;
				result = CordX - shipSize;
				if (result < -1){return false;}
				result = 0;
				result  = CordX + shipSize;
				if (result >= 11){return false;}
				else {return true;}
			}
			else{return false;}
		}
		public void placeBoat(Ship info){
			p[CordY-1][CordX-1] = '/';
			if(shipDirection.equals("HORIZONTAL")){
				if (shipSize == 2){p[CordY][CordX-1] = '/';}
				if (shipSize == 3){p[CordY][CordX-1] = '/'; p[CordY-2][CordX-1] = '/';}
				if (shipSize == 4){p[CordY][CordX-1] = '/';p[CordY-2][CordX-1] = '/';p[CordY+1][CordX-1] = '/';}
				if (shipSize == 5){p[CordY][CordX-1] = '/';p[CordY-2][CordX-1] = '/';p[CordY+1][CordX-1] = '/';p[CordY+2][CordX-1] = '/';}
			}
			if(shipDirection.equals("VERTICAL")){
				if (shipSize == 2){p[CordY-1][CordX] = '/';}
				if (shipSize == 3){p[CordY-1][CordX] = '/'; p[CordY-1][CordX-2] = '/';}
				if (shipSize == 4){p[CordY-1][CordX] = '/';p[CordY-1][CordX-2] = '/';p[CordY-1][CordX+1] = '/';}
				if (shipSize == 5){p[CordY-1][CordX] = '/';p[CordY-1][CordX-2] = '/';p[CordY-1][CordX+1] = '/';p[CordY-1][CordX+2] = '/';}
			}
		}
    }

    public static void buildMode(){
        /*
         * Choices
         * 0 = Patrol Boat(2)
         * 1 = Destroyer(3)
         * 2 = Submarine(3)
         * 3 = Battleship(4)
         * 4 = AircraftCarrier(5)
         */
		buildMode = true;
		int response;
		do{
            System.out.println(" ");
            System.out.println("Which ship would you like to place?");
            if (ships[0]){System.out.println(choices[0]+ " [1]");}
            if (ships[1]){System.out.println(choices[1]+ " [2]");}
            if (ships[2]){System.out.println(choices[2]+ " [3]");}
            if (ships[3]){System.out.println(choices[3]+ " [4]");}
            if (ships[4]){System.out.println(choices[4]+ " [5]");}
            if (!ships[0] && !ships[1] && !ships[2] && !ships[3] && !ships[4]){
            	System.out.println(" ");
            	System.out.println(" ");
            	System.out.println(" ");
            	System.out.println(" ");
            	System.out.println(" ");
            	System.out.println(" ");
            	System.out.println(" ");
            	System.out.println(" ");
				buildMode = false;
				break;
            }
            response = numpad.nextInt();
            switch(response){
				case 1:
                    if (!ships[0]){System.out.println("You already used that ship."); break;}
                    else{
						System.out.println("Which row would you like (#)");
						int x = numpad.nextInt();
						System.out.println("Which column would you like (#)");
						int y = numpad.nextInt();
						System.out.println("Would you like to place it Horizontal or Vertical?");
						String direction = keyboard.nextLine();
						direction = direction.toUpperCase();
						if (direction.equals("HORIZONTAL")){Ship boat = new Ship("boat",x, y, 2, direction);
						if (boat.checkShip(boat)){
							boat.placeBoat(boat);
							ships[0] = false;
							updateBoard();
							break;
						}else{System.out.println("Ship out of bounds."); break;}
							}
						else if (direction.equals("VERTICAL")){Ship boat = new Ship("boat",x, y, 2, direction);
						if (boat.checkShip(boat)){
							boat.placeBoat(boat);
							ships[0] = false;
							updateBoard();
							break;
						}else{System.out.println("Ship out of bounds."); break;}
							}
						else{break;}
                    }
			case 2:
                if (!ships[1]){System.out.println("You already used that ship."); break;}
                else{
					System.out.println("Which row would you like (#)");
					int x = numpad.nextInt();
					System.out.println("Which column would you like (#)");
					int y = numpad.nextInt();
					System.out.println("Would you like to place it Horizontal or Vertical?");
					String direction = keyboard.nextLine();
					direction = direction.toUpperCase();
					if (direction.equals("HORIZONTAL")){Ship destroyer = new Ship("destroyer",x, y, 3, direction);
					if (destroyer.checkShip(destroyer)){
						destroyer.placeBoat(destroyer);
						ships[1] = false;
						updateBoard();
						break;
					}else{System.out.println("Ship out of bounds."); break;}
						}
					else if (direction.equals("VERTICAL")){Ship destroyer = new Ship("destroyer",x, y, 3, direction);
					if (destroyer.checkShip(destroyer)){
						destroyer.placeBoat(destroyer);
						ships[1] = false;
						updateBoard();
						break;
					}else{System.out.println("Ship out of bounds."); break;}
						}
					else{break;}
                }
				case 3:
	                if (!ships[2]){System.out.println("You already used that ship."); break;}
	                else{
						System.out.println("Which row would you like (#)");
						int x = numpad.nextInt();
						System.out.println("Which column would you like (#)");
						int y = numpad.nextInt();
						System.out.println("Would you like to place it Horizontal or Vertical?");
						String direction = keyboard.nextLine();
						direction = direction.toUpperCase();
						if (direction.equals("HORIZONTAL")){Ship destroyer = new Ship("submarine",x, y, 3, direction);
						if (destroyer.checkShip(destroyer)){
							destroyer.placeBoat(destroyer);
							ships[2] = false;
							updateBoard();
							break;
						}else{System.out.println("Ship out of bounds."); break;}
							}
						else if (direction.equals("VERTICAL")){Ship destroyer = new Ship("submarine",x, y, 3, direction);
						if (destroyer.checkShip(destroyer)){
							destroyer.placeBoat(destroyer);
							ships[2] = false;
							updateBoard();
							break;
						}else{System.out.println("Ship out of bounds."); break;}
							}
						else{break;}
	                }
				case 4:
	                if (!ships[3]){System.out.println("You already used that ship."); break;}
	                else{
						System.out.println("Which row would you like (#)");
						int x = numpad.nextInt();
						System.out.println("Which column would you like (#)");
						int y = numpad.nextInt();
						System.out.println("Would you like to place it Horizontal or Vertical?");
						String direction = keyboard.nextLine();
						direction = direction.toUpperCase();
						if (direction.equals("HORIZONTAL")){Ship destroyer = new Ship("battleship",x, y, 4, direction);
						if (destroyer.checkShip(destroyer)){
							destroyer.placeBoat(destroyer);
							ships[3] = false;
							updateBoard();
							break;
						}else{System.out.println("Ship out of bounds."); break;}
							}
						else if (direction.equals("VERTICAL")){Ship destroyer = new Ship("battleship",x, y, 4, direction);
						if (destroyer.checkShip(destroyer)){
							destroyer.placeBoat(destroyer);
							ships[3] = false;
							updateBoard();
							break;
						}else{System.out.println("Ship out of bounds."); break;}
							}
						else{break;}
	                }
				case 5:
	                if (!ships[4]){System.out.println("You already used that ship."); break;}
	                else{
						System.out.println("Which row would you like (#)");
						int x = numpad.nextInt();
						System.out.println("Which column would you like (#)");
						int y = numpad.nextInt();
						System.out.println("Would you like to place it Horizontal or Vertical?");
						String direction = keyboard.nextLine();
						direction = direction.toUpperCase();
						if (direction.equals("HORIZONTAL")){Ship destroyer = new Ship("aircraft",x, y, 5, direction);
						if (destroyer.checkShip(destroyer)){
							destroyer.placeBoat(destroyer);
							ships[4] = false;
							updateBoard();
							break;
						}else{System.out.println("Ship out of bounds."); break;}
							}
						else if (direction.equals("VERTICAL")){Ship destroyer = new Ship("aircraft",x, y, 5, direction);
						if (destroyer.checkShip(destroyer)){
							destroyer.placeBoat(destroyer);
							ships[4] = false;
							updateBoard();
							break;
						}else{System.out.println("Ship out of bounds."); break;}
							}
						else{break;}
	                }
				default:
					System.out.println("you should not be here.");
					break;
            }
		}while (buildMode);
    }

    public static void clearBoard(){
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p[0].length; j++) {
				p[i][j] = 'o';
				e[i][j] = 'o';
			}
		}
    }
    //Self Explanatory.
    public static void updateBoard(){
		//Top
		System.out.println(" ");
		System.out.println("Enemy View");
		System.out.println("     1         2         3         4         5         6         7         8         9         10");
		System.out.println(" 1   "+e[0][0]+"         "+e[1][0]+"         "+e[2][0]+"         "+e[3][0]+"         "+e[4][0]+"         "+e[5][0]+"         "+e[6][0]+"         "+e[7][0]+"         "+e[8][0]+"         "+e[9][0]);
		System.out.println(" 2   "+e[0][1]+"         "+e[1][1]+"         "+e[2][1]+"         "+e[3][1]+"         "+e[4][1]+"         "+e[5][1]+"         "+e[6][1]+"         "+e[7][1]+"         "+e[8][1]+"         "+e[9][1]);
		System.out.println(" 3   "+e[0][2]+"         "+e[1][2]+"         "+e[2][2]+"         "+e[3][2]+"         "+e[4][2]+"         "+e[5][2]+"         "+e[6][2]+"         "+e[7][2]+"         "+e[8][2]+"         "+e[9][2]);
		System.out.println(" 4   "+e[0][3]+"         "+e[1][3]+"         "+e[2][3]+"         "+e[3][3]+"         "+e[4][3]+"         "+e[5][3]+"         "+e[6][3]+"         "+e[7][3]+"         "+e[8][3]+"         "+e[9][3]);
		System.out.println(" 5   "+e[0][4]+"         "+e[1][4]+"         "+e[2][4]+"         "+e[3][4]+"         "+e[4][4]+"         "+e[5][4]+"         "+e[6][4]+"         "+e[7][4]+"         "+e[8][4]+"         "+e[9][4]);
		System.out.println(" 6   "+e[0][5]+"         "+e[1][5]+"         "+e[2][5]+"         "+e[3][5]+"         "+e[4][5]+"         "+e[5][5]+"         "+e[6][5]+"         "+e[7][5]+"         "+e[8][5]+"         "+e[9][5]);
		System.out.println(" 7   "+e[0][6]+"         "+e[1][6]+"         "+e[2][6]+"         "+e[3][6]+"         "+e[4][6]+"         "+e[5][6]+"         "+e[6][6]+"         "+e[7][6]+"         "+e[8][6]+"         "+e[9][6]);
		System.out.println(" 8   "+e[0][7]+"         "+e[1][7]+"         "+e[2][7]+"         "+e[3][7]+"         "+e[4][7]+"         "+e[5][7]+"         "+e[6][7]+"         "+e[7][7]+"         "+e[8][7]+"         "+e[9][7]);
		System.out.println(" 9   "+e[0][8]+"         "+e[1][8]+"         "+e[2][8]+"         "+e[3][8]+"         "+e[4][8]+"         "+e[5][8]+"         "+e[6][8]+"         "+e[7][8]+"         "+e[8][8]+"         "+e[9][8]);
		System.out.println("10   "+e[0][9]+"         "+e[1][9]+"         "+e[2][9]+"         "+e[3][9]+"         "+e[4][9]+"         "+e[5][9]+"         "+e[6][9]+"         "+e[7][9]+"         "+e[8][9]+"         "+e[9][9]);
		//Bottom
		System.out.println(" ");
		System.out.println("Your Ships");
		System.out.println("     1         2         3         4         5         6         7         8         9         10");
		System.out.println(" 1   "+p[0][0]+"         "+p[1][0]+"         "+p[2][0]+"         "+p[3][0]+"         "+p[4][0]+"         "+p[5][0]+"         "+p[6][0]+"         "+p[7][0]+"         "+p[8][0]+"         "+p[9][0]);
		System.out.println(" 2   "+p[0][1]+"         "+p[1][1]+"         "+p[2][1]+"         "+p[3][1]+"         "+p[4][1]+"         "+p[5][1]+"         "+p[6][1]+"         "+p[7][1]+"         "+p[8][1]+"         "+p[9][1]);
		System.out.println(" 3   "+p[0][2]+"         "+p[1][2]+"         "+p[2][2]+"         "+p[3][2]+"         "+p[4][2]+"         "+p[5][2]+"         "+p[6][2]+"         "+p[7][2]+"         "+p[8][2]+"         "+p[9][2]);
		System.out.println(" 4   "+p[0][3]+"         "+p[1][3]+"         "+p[2][3]+"         "+p[3][3]+"         "+p[4][3]+"         "+p[5][3]+"         "+p[6][3]+"         "+p[7][3]+"         "+p[8][3]+"         "+p[9][3]);
		System.out.println(" 5   "+p[0][4]+"         "+p[1][4]+"         "+p[2][4]+"         "+p[3][4]+"         "+p[4][4]+"         "+p[5][4]+"         "+p[6][4]+"         "+p[7][4]+"         "+p[8][4]+"         "+p[9][4]);
		System.out.println(" 6   "+p[0][5]+"         "+p[1][5]+"         "+p[2][5]+"         "+p[3][5]+"         "+p[4][5]+"         "+p[5][5]+"         "+p[6][5]+"         "+p[7][5]+"         "+p[8][5]+"         "+p[9][5]);
		System.out.println(" 7   "+p[0][6]+"         "+p[1][6]+"         "+p[2][6]+"         "+p[3][6]+"         "+p[4][6]+"         "+p[5][6]+"         "+p[6][6]+"         "+p[7][6]+"         "+p[8][6]+"         "+p[9][6]);
		System.out.println(" 8   "+p[0][7]+"         "+p[1][7]+"         "+p[2][7]+"         "+p[3][7]+"         "+p[4][7]+"         "+p[5][7]+"         "+p[6][7]+"         "+p[7][7]+"         "+p[8][7]+"         "+p[9][7]);
		System.out.println(" 9   "+p[0][8]+"         "+p[1][8]+"         "+p[2][8]+"         "+p[3][8]+"         "+p[4][8]+"         "+p[5][8]+"         "+p[6][8]+"         "+p[7][8]+"         "+p[8][8]+"         "+p[9][8]);
		System.out.println("10   "+p[0][9]+"         "+p[1][9]+"         "+p[2][9]+"         "+p[3][9]+"         "+p[4][9]+"         "+p[5][9]+"         "+p[6][9]+"         "+p[7][9]+"         "+p[8][9]+"         "+p[9][9]);
    }

    public static void main(String[] args) {

		clearBoard();
		updateBoard();
		buildMode();
		System.out.println("I exited out sucessfully");
	}
}

