import java.util.HashMap;

public class MoveList {
	private static HashMap<String, String> moves = new HashMap<String, String>();
	
	public MoveList() {
		moves.put("attack1", "attack,1,5");
		moves.put("poison1", "attack,4,3");
		moves.put("heal1", "heal,1,5");
		moves.put("debug1shot", "attack,1,99999");
	}
	
	public static boolean makeMove(Character caster, Character target, String moveName) {
		String moveEffect = moves.get(moveName);
		if (moveEffect == null || !moves.containsKey(moveName)) {
			return false;
		}
		move(caster, target, moveEffect);
		return true;
	}
	
	public static boolean removeMove(String moveName) {
		return moves.remove(moveName) != null;
	}
	
	public static void modifyMove(String moveName, String effect, int duration, int modifier) {
		if (moves.containsKey(moveName)) {
			moves.replace(moveName, (effect + "," + duration + "," + modifier));
		}
		moves.put(moveName, (effect + "," + duration + "," + modifier));
	}
	
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
