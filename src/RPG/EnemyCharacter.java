package RPG;

public class EnemyCharacter extends Character {

	
	public EnemyCharacter(String name) {
		super(name);
	}

	public EnemyCharacter(String name, int health) {
		super(name, health, health);
	}
	
	//if the attacks if player is not statused, will heal if less than 20% health, otherwise attacks
	public void makeMove() {
		if ((BattleController.player.getHealth() < MoveList.getModifier("enemyattack")) && BattleController.player.getStatus().equals("")) {
			MoveList.makeMove(this, BattleController.player, "enemypoison");
			BattleController.enemyMoveMade("poison");
		}
		else if (this.health < this.health / 5) {
			MoveList.makeMove(this, BattleController.player, "enemyheal");
			BattleController.enemyMoveMade("heal");
		}
		else {
			MoveList.makeMove(this, BattleController.player, "enemyattack");
			BattleController.enemyMoveMade("attack");
		}
	}

	
}
