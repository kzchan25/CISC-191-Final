package RPG;
import java.util.HashMap;

public class MoveList {
	private static HashMap<String, String> moves = new HashMap<String, String>();
	
	//adds moves for new game
	public MoveList() {
		moves.put("attack", "attack,1,5");
		moves.put("poison", "poison,4,3");
		moves.put("heal", "heal,1,4");
		moves.put("shield", "shield,1,3");
		moves.put("enemyattack", "attack,1,3");
		moves.put("enemypoison", "poison,3,2");
		moves.put("enemyheal", "heal,1,5");
	}
	
	//checks to see if the move is valid, then calls the primary move 
	public static boolean makeMove(Character caster, Character target, String moveName) {
		String moveEffect = moves.get(moveName);
		if (moveEffect == null || !moves.containsKey(moveName)) {
			return false;
		}
		move(caster, target, moveEffect);
		return true;
	}
	
	//used to populate the move list from a save file
	public static void addMove(String name, String effect) {
		moves.put(name, effect);
	}
	
	//increases modifier of a move by 1
	public static boolean upgradeMove(String move) {
		if (moves.containsKey(move)) {
			String value = moves.get(move).substring(0, moves.get(move).lastIndexOf(",")) + "," + (getModifier(move) + 1);
			moves.replace(move, value);
			return true;
		}
		return false;
	}
	
	//gets the modifier of a move
	public static int getModifier(String move) {
		return Integer.valueOf(moves.get(move).substring(moves.get(move).lastIndexOf(',') + 1));
	}
	
	public static HashMap<String, String> getList() {
		return moves;
	}
	
	//takes a move, breaks it into different components, then runs the move
	private static void move(Character caster, Character target, String moveEffect) {
		String effect = moveEffect.substring(0, moveEffect.indexOf(','));
		int duration = Integer.valueOf(moveEffect.substring(moveEffect.indexOf(',') + 1, moveEffect.lastIndexOf(',')));
		int modifier = Integer.valueOf(moveEffect.substring(moveEffect.lastIndexOf(',') + 1));
		
		switch (effect) {
		case "attack":
			target.takeDamage(modifier);
			break;
		case "heal":
			caster.heal(modifier);
			break;
		case "poison":
			target.setStatus("poison", duration, modifier);
			break;
		case "shield":
			caster.setShield(modifier);
			break;
		case "speed":
			caster.setStatus("speed", duration, modifier);
			break;
		}
	}
}
