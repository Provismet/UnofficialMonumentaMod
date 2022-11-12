package ch.njol.unofficialmonumentamod.core;

import ch.njol.unofficialmonumentamod.UnofficialMonumentaModClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class Constants {
	private static final Gson gson = new GsonBuilder()
		                                 .create();
	private static final TypeToken<HashMap<String, Shard>> token = new TypeToken<>() {
	};

	public static final HashMap<String, Shard> shards = new HashMap<>();

	private static final Identifier OverrideResource = new Identifier(UnofficialMonumentaModClient.MOD_IDENTIFIER, "override/shards.json");

	private static boolean isDevMode() {
		return FabricLoader.getInstance().isDevelopmentEnvironment() || UnofficialMonumentaModClient.options.showShardOverridingChanges;
	}

	public static void onReload() {
		try {
			InputStream stream = MinecraftClient.getInstance().getResourceManager().getResource(OverrideResource).getInputStream();
			HashMap<String, Shard> hash = gson.fromJson(new InputStreamReader(stream, "UTF-8"), token.getType());
			if (hash != null && !hash.isEmpty()) {
				for (Map.Entry<String, Shard> newEntry : hash.entrySet()) {
					if (shards.containsKey(newEntry.getKey())) {
						if (isDevMode()) {
							UnofficialMonumentaModClient.LOGGER.info("Replaced key: " + newEntry.getKey() + " to " + newEntry.getValue());
						}
						shards.replace(newEntry.getKey(), newEntry.getValue());
					} else {
						if (isDevMode()) {
							UnofficialMonumentaModClient.LOGGER.info("Added key: " + newEntry.getKey() + " with value " + newEntry.getValue());
						}
						shards.put(newEntry.getKey(), newEntry.getValue());
					}
				}
			}
		} catch (IOException | JsonParseException e) {
			UnofficialMonumentaModClient.LOGGER.error("Caught error while trying to override loaded shards");
			e.printStackTrace();
		}
	}

	public static String getOfficialName(String shard) {
		if (shards.containsKey(shard)) {
			return shards.get(shard).offName;
		}
		return null;
	}

	public static Integer getMaxChests(String shard) {
		if (shards.containsKey(shard)) {
			return shards.get(shard).maxChests;
		}
		return null;
	}

	static {
		//the maxChests values were "borrowed" from Vladomeme's Lootcounter mod, I would wholeheartedly recommend using it https://github.com/Vladomeme/lootcounter/releases.

		//main zones
		shards.put(
			"valley", new Shard("valley", "King's Valley", ShardType.overworld, null)
		);
		shards.put(
			"isles", new Shard("isles", "Celsian Isles", ShardType.overworld, null)
		);
		shards.put(
			"ring", new Shard("ring", "Architect's Ring", ShardType.overworld, null)
		);

		shards.put(
			"plots", new Shard("plots", "Plots", ShardType.overworld, null)
		);
		shards.put(
			"playerplots", new Shard("playerplots", "Player plots", ShardType.overworld, null)
		);


		//dungeons / special areas
		//R1
		shards.put(
			"tutorial", new Shard("tutorial", "No spoilers", ShardType.dungeon, null)
		);
		shards.put(
			"labs", new Shard("labs", "The Alchemy Labs", ShardType.dungeon, null)
		);
		shards.put(
			"white", new Shard("white", "Halls of Wind And Blood", ShardType.dungeon, null)
		);
		shards.put(
			"orange", new Shard("orange", "Fallen Menagerie", ShardType.dungeon, null)
		);
		shards.put(
			"magenta", new Shard("magenta", "Plagueroot Temple", ShardType.dungeon, null)
		);
		shards.put(
			"lightblue", new Shard("magenta", "Arcane Rivalry", ShardType.dungeon, null)
		);
		shards.put(
			"yellow", new Shard("yellow", "Vernal Nightmare", ShardType.dungeon, null)
		);
		shards.put(
			"willows", new Shard("willows", "The Black Willows", ShardType.dungeon, null)
		);
		shards.put(
			"corridors", new Shard("corridors", "Ephemeral Corridors", ShardType.strike, 21)
		);
		shards.put(
			"reverie", new Shard("reverie", "Malevolent Reverie", ShardType.dungeon, null)
		);
		shards.put(
			"verdant", new Shard("verdant", "Verdant Remnants", ShardType.strike, 42)
		);
		shards.put(
			"sanctum", new Shard("sanctum", "Forsworn Sanctum", ShardType.strike, 65)
		);


		//R2
		shards.put(
			"lime", new Shard("lime", "Salazar's Folly", ShardType.dungeon, null)
		);
		shards.put(
			"pink", new Shard("pink", "Harmonic Arboretum", ShardType.dungeon, null)
		);
		shards.put(
			"gray", new Shard("gray", "Valley of the Forgotten Pharaohs", ShardType.dungeon, null)
		);
		shards.put(
			"lightgray", new Shard("lightgray", "Palace Of Mirrors", ShardType.dungeon, null)
		);
		shards.put(
			"cyan", new Shard("cyan", "Scourge Of Lunacy", ShardType.dungeon, null)
		);
		shards.put(
			"purple", new Shard("purple", "Grasp Of Avarice", ShardType.dungeon, null)
		);
		shards.put(
			"teal", new Shard("teal", "Echoes Of Oblivion", ShardType.dungeon, null)
		);
		shards.put(
			"shiftingcity", new Shard("shiftingcity", "City of Shifting Waters", ShardType.dungeon, null)
		);
		shards.put(
			"forum", new Shard("forum", "The Fallen Forum", ShardType.dungeon, null)
		);
		shards.put(
			"depths", new Shard("depths", "Darkest Depths", ShardType.strike, 0)
		);
		shards.put(
			"mist", new Shard("mist", "The Black Mist", ShardType.strike, 41)
		);
		shards.put(
			"remorse", new Shard("remorse", "The Sealed Remorse", ShardType.strike, 62)
		);
		shards.put(
			"rush", new Shard("rush", "Rush of Dissonance", ShardType.minigame, null)
		);

		//R3
		shards.put(
			"blue", new Shard("blue", "Coven's Gambit", ShardType.dungeon, null)
		);
		shards.put(
			"portal", new Shard("portal", "P.O.R.T.A.L", ShardType.strike, 41)
		);
		shards.put(
			"skt", new Shard("skt", "Silver Knight's Tomb", ShardType.strike, 90)
		);
		shards.put(
			"ruin", new Shard("ruin", "Masquerader's Ruin", ShardType.strike, 98)
		);
		shards.put(
			"gallery", new Shard("gallery", "The Gallery of Fear", ShardType.minigame, null)
		);
	}

	public static class Shard {
		public final String name;
		public final String offName;
		public final ShardType shardType;
		@Nullable
		public final Integer maxChests;

		Shard(String name, String offName, ShardType shardType, @Nullable Integer maxChests) {
			this.name = name;
			this.offName = offName;
			this.shardType = shardType;
			this.maxChests = maxChests;
		}

		@Override
		public String toString() {
			return "{ name: " + name + ", officialName: " + offName + ", shardType: " + shardType.toString() + ", maxChests: " + (maxChests != null ? maxChests : "null") + " }";
		}
	}

	public enum ShardType {
		strike(),
		overworld(),
		dungeon(),
		minigame()
	}
}
