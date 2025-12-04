package en.swingy.equipement.armor;

import java.util.Random;

public enum ArmorEnum {
	IMMORTAL_KINGS_ETERNAL_REIGN("Immortal King's Eternal Reign"),
	HEART_OF_THE_CRASHING_WAVE("Heart of the Crashing Wave"),
	CUIRASS_OF_THE_WASTES("Cuirass of the Wastes"),
	EYE_OF_THE_EARTH("Eye of the Earth"),
	MOUNTAIN_OF_THE_SAVAGE("Mountain of the Savage"),
	BREASTPLATE_OF_AKKHAN("Breastplate of Akkhan"),
	CUIRASS_OF_VALOR("Cuirass of Valor"),
	HEART_OF_THE_LIGHT("Heart of the Light"),
	HEART_OF_THE_INVOKER("Heart of the Invoker"),
	ROLANDS_BEARING("Roland's Bearing"),
	NATALYAS_EMBRACE("Natalya's Embrace"),
	MARAUDERS_CARAPACE("Marauder's Carapace"),
	CAGE_OF_THE_HELLBORN("Cage of the Hellborn"),
	VEST_OF_THE_SHADOW("Vest of the Shadow"),
	DREADLANDS_ARMOR("Dreadlands Armor"),
	INNAS_VAST_EXPANSE("Inna's Vast Expanse"),
	SUNWUKOS_SOUL("Sunwuko's Soul"),
	ULIANAS_HEART("Uliana's Heart"),
	RAIMENT_CHEST("Raiment of a Thousand Storms Chest"),
	PATTERNS_OF_JUSTICE_ARMOR("Patterns of Justice Armor"),
	RATHMAS_RIBCAGE_PLATE("Rathma's Ribcage Plate"),
	INARIUSS_CONVICTION("Inarius's Conviction"),
	PESTILENCE_DEFENSE("Pestilence Defense"),
	TRAGOULS_SCALES("Trag'Oul's Scales"),
	CARNIVAL_GARB("Carnival Garb"),
	TAL_RASHAS_RELENTLESS_PURSUIT("Tal Rasha's Relentless Pursuit"),
	FIREBIRDS_BREAST("Firebird's Breast"),
	DELSERES_HEART("Delsere's Heart"),
	VYRS_ASTONISHING_AURA("Vyr's Astonishing Aura"),
	TYPHONS_THORAX("Typhon's Thorax"),
	ZUNIMASSAS_MARROW("Zunimassa's Marrow"),
	JADE_HARVESTERS_PEACE("Jade Harvester's Peace"),
	HELLTOOTH_TUNIC("Helltooth Tunic"),
	ARACHYRS_CARAPACE("Arachyr's Carapace"),
	MUNDUNUGUS_ROBE("Mundunugu's Robe"),
	BORNS_HEART_OF_STEEL("Born's Heart of Steel"),
	CAPTAIN_CRIMSONS_SILK_SHIRT("Captain Crimson's Silk Shirt"),
	AUGHILDS_RULE("Aughild's Rule"),
	SAGES_RIBCAGE("Sage's Ribcage"),
	CAINS_HABIT("Cain's Habit"),
	ASHEARAS_GUARD("Asheara's Guard"),
	BLACKTHORNES_SURCOAT("Blackthorne's Surcoat"),
	GUARDIANS_AVERSION("Guardian's Aversion"),
	DEMONS_PLATE("Demon's Plate");

	private final String label;

	ArmorEnum(String label) {
		this.label = label;
	}

	/**
	 * Returns the label (display name) of the armor.
	 *
	 * @return armor label as a string
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Returns the label of a randomly selected armor from the enum.
	 *
	 * @return a random armor label as a string
	 */
	public static String getRandom() {
		ArmorEnum[] values = ArmorEnum.values();
		int index = new Random().nextInt(values.length);
		return values[index].getLabel();
	}
}
