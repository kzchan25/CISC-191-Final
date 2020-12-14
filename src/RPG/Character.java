package RPG;

//Kai Chan
//Base Character class, is used for the player, parent class of the enemy character
//calculates and stores data used by the character (except for the move data, that's in MoveList)

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Character {
	protected String name;
	protected int health;
	protected int maxHealth;
	protected String status;
	protected String[] moves; 
	protected int statusLength;
	protected int shield;
	protected int speed;
	protected int statusStrength;
	
	//constructors
	
	public Character(String name, int health) {
		this(name, health, 100);
	}
	
	public Character(String name, int health, int maxHealth) {
		this.maxHealth = maxHealth;
		this.name = name;
		this.health = health;
		this.moves = new String[4];
		this.status = "";
		Arrays.asList(moves).forEach(move -> move = null);
	}
	
	public Character(String name) {
		this(name, 20);
	}
	
	public Character() {
		this("", 20);
	}

	//setters and getters
	
	public int getAverageModifier() { //gets average modifier value in the move list
		AtomicInteger modifier = new AtomicInteger(0);
		Arrays.asList(moves).forEach(move -> modifier.addAndGet(MoveList.getModifier(move)));
		return modifier.get() / moves.length;
	}

	public String[] getMoves() {
		return moves;
	}
	
	public void setShield(int size) {
		this.shield = size;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getStatus() {
		return status;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public boolean setStatus(String status, int length, int strength) { //if status is clear, sets status and returns true, if there is a status, returns false
		if (this.status.equals("")) {
			this.status = status;
			this.statusLength = length;
			this.statusStrength = strength;
			return true;
		}
		return false;
	}
	
	//end of turn call
	
	public void endOfTurnStatusUpdate() { //calculates poison damage if any, checks status length and sets gets rid of status on statusLength equals 0, will tick negative, but should not cause bugs
		health -= statusStrength * (status.equals("poison") ? 1 : 0);
		if (statusLength == 0) {
			status = "";
		}
		statusLength--;
	}
	
	//game calls 
	
	public void takeDamage(int damage) { //damage soaks into shield first, if the damage is greater than shield, shield is set to 0 and the damage is reduced by the shield
		if (damage > shield) {
			health -= damage - shield;
			shield = 0;
		}
		else {
			shield -= damage;
		}
	}
	
	public void heal(int health) {//heal
		if (health + this.health > maxHealth) {
			health = maxHealth;
		}
		else {
			this.health += health;
		}
	}
	
	public boolean makeMove(String name) { //makes move, first checks to see if valid move, then sends to move list
		for (String move : this.moves) {
			if (move != null && move.equals(name)) {
				return MoveList.makeMove(this, BattleController.currentEnemy, name);
			}
		}
		return false;
	}
	
	//move array methods
	
	public void removeMove(String name) {
		Stream.of(moves).filter(move -> !move.equals(name));
	}
	
	public boolean addMove(String move) { //add moves at empty space index if the index is not equal to -1
		int index = getEmptySpaceIndex();
		if (index != -1) {
			this.moves[index] = move;
			return true;
		}
		return false;
	}
	
	public void replaceMove(String name, String move) {
		removeMove(name);
		addMove(move);
	}
	
	public boolean isFull() { //checks to see if the move list is full
		return getEmptySpaceIndex() == -1;
	}
	
	public int getEmptySpaceIndex() { //if there is an empty space in moves, will return the empty space index, returns -1 if no space
		for (int i = 0; i < this.moves.length; i++) {
			if (this.moves[i] == null) {
				return i;
			}
		}
		return -1;
	}
}
