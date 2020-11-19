import java.util.Arrays;
import java.util.stream.Stream;

public class Character {
	private String name;
	private int health;
	private String status;
	private String[] moves; 
	private int statusLength;
	private int shield;
	private int speed;
	private int statusStrength;
	public Character(String name, int health) {
		this.name = name;
		this.health = health;
		this.moves = new String[6];
		this.status = "";
		Arrays.asList(moves).forEach(move -> move = null);
	}
	
	public Character(String name) {
		this(name, 20);
	}
	
	public void takeDamage(int damage) {
		if (damage > shield) {
			health -= damage - shield;
			shield = 0;
		}
		else {
			shield -= damage;
		}
	}

	public String[] getMoves() {
		return moves;
	}
	public void endOfTurnStatusUpdate() {
		health -= statusStrength * (status.equals("poison") ? 1 : 0);
		if (statusLength == 0) {
			status = "";
		}
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
	
	public int getSpeed() {
		return speed + (status.equals("speed") ? 1 : 0) * statusStrength;
	}

	public boolean setStatus(String status, int length, int strength) {
		if (this.status.equals("")) {
			this.status = status;
			this.statusLength = length;
			this.statusStrength = strength;
			return true;
		}
		return false;
	}
	
	public void heal(int health) {
		this.health += health;
	}
	
	public boolean isFull() {
		return getEmptySpaceIndex() == -1;
	}
	
	public int getEmptySpaceIndex() {
		for (int i = 0; i < this.moves.length; i++) {
			if (this.moves[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	public void removeMove(String name) {
		Stream.of(moves).filter(move -> !move.equals(name));
	}
	
	public boolean addMove(String move) {
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
	
	
	public boolean makeMove(String name) {
		for (String move : this.moves) {
			if (move != null && move.equals(name)) {
				return MoveList.makeMove(this, BattleController.getCurrentEnemy(), name);
			}
		}
		return false;
	}
}
