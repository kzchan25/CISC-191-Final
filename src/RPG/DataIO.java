package RPG;

//Tram Huynh
//Encodes and Decodes game data into JSON format and either reads or writes the data to a json file

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

public class DataIO {
	//stores file location
	private static final String MOVELIST_LOCATION = "resources/save/Moves.json";
	private static final  String PLAYER_LOCATION = "resources/save/Player.json";
	private static final String ENEMIES_LOCATION = "resources/save/Enemy.json";
	private static final String SCENE_LOCATION = "resources/save/Scene.json";
	
	//takes a file path, and gets the json string from the file
	private static String readFromFile(String fileName) {
		Scanner file;
		String text = "";
		try {
			file = new Scanner(new FileReader(fileName));
			while (file.hasNextLine()) {
				text += file.nextLine();
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return text;
	}
	//takes a string and a file location, writes to file
	private static void writeToFile(String data, String fileName) {
		FileWriter file;
		try {
			file = new FileWriter(fileName);
			file.write(data);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//initializes the player in BattleController from json string
	public static void loadPlayer() {
		try {
			JSONObject jsonText = new JSONObject(readFromFile(PLAYER_LOCATION));
			BattleController.player = new Character((String)jsonText.get("name"), jsonText.getInt("health"), jsonText.getInt("maxHealth"));
			BattleController.player.setStatus((String)jsonText.get("status"), 3, MoveList.getModifier("enemypoison"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	//loads the battle controller variables from json string
	public static void loadScene() {
		try {
			JSONObject jsonText = new JSONObject(readFromFile(SCENE_LOCATION));
			new BattleController(jsonText.getInt("turnCount"), jsonText.getInt("enemiesDefeated"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	//loads enemy from json string
	public static void loadEnemy() {
		try {
			JSONObject jsonText = new JSONObject(readFromFile(ENEMIES_LOCATION));
			BattleController.currentEnemy = new EnemyCharacter((String)jsonText.get("name"), jsonText.getInt("health"));
			BattleController.currentEnemy.setStatus((String)jsonText.get("status"), 3, MoveList.getModifier("poison"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	//loads move list from json string
	public static void loadMoveList() {
		JSONObject jsonText;
		try {
			jsonText = new JSONObject(readFromFile(MOVELIST_LOCATION));
			Iterator<?> buffer = jsonText.keys();
			while (buffer.hasNext()) {
				String key = (String) buffer.next();
				String value = jsonText.getString(key);
				MoveList.addMove(key, value);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	//takes the hashmap from MoveList and serializes into json string
	public static void saveMoveList() {
		JSONObject jsonObject = new JSONObject();
		MoveList.getList().forEach((key, value) -> {
			try {
				jsonObject.put(key, value);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		});
		writeToFile(jsonObject.toString(), MOVELIST_LOCATION);
	}
	
	//converts the player to json string then calls writer
	public static void savePlayer() {
		writeToFile(new JSONObject(BattleController.player).toString(), PLAYER_LOCATION);
	}
	
	//converts the enemy to json string then calls writer
	public static void saveEnemy() {
		writeToFile(new JSONObject(BattleController.currentEnemy).toString(), ENEMIES_LOCATION);
	}
	
	//takes turnCount and enemiesDefeated and converts them to a json String, then calls writer
	public static void saveScene() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("turnCount", BattleController.getTurnsPassed());
			jsonObject.put("enemiesDefeated", BattleController.getEnemiesDefeated());
			writeToFile(jsonObject.toString(), SCENE_LOCATION);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
