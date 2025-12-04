package en.swingy.equipement.weapon;

import java.util.Random;

public enum WeaponEnum {
	IMMORTAL_KINGS_BOULDER_BREAKER("Immortal King's Boulder Breaker"),
	GAVEL_OF_THE_FIRST_MEN("Gavel of the First Men"),
	FJORD_CUTTER("Fjord Cutter"),
	REMORSELESS("Remorseless"),
	AKKHANS_LENIENCY("Akkhan's Leniency"),
	BLADE_OF_PROPHECY("Blade of Prophecy"),
	GOLDEN_FLENSE("Golden Flense"),
	PIG_STICKER("Pig Sticker"),
	SKYCUTTER("Skycutter"),
	NATALYAS_SLAYER("Natalya's Slayer"),
	NATALYAS_REFLECTION("Natalya's Reflection"),
	MANTICORE("Manticore"),
	YANGS_RECURVE("Yang's Recurve"),
	KARLEIS_POINT("Karlei's Point"),
	DAWN("Dawn"),
	INNAS_REACH("Inna's Reach"),
	SUNWUKOS_SHARD("Sunwuko's Shard"),
	ULIANAS_FURY("Uliana's Fury"),
	FIST_OF_AZTURRASQ("Fist of Az'Turrasq"),
	WON_KHIM_LAU("Won Khim Lau"),
	JESSETH_SKULLSCYTHE("Jesseth Skullscythe"),
	JESSETH_SKULLSHIELD("Jesseth Skullshield"),
	SCYTHE_OF_THE_CYCLE("Scythe of the Cycle"),
	TRAGOULS_CORRODED_FANG("Trag'Oul's Corroded Fang"),
	NAYRS_BLACK_DEATH("Nayr's Black Death"),
	MALTORIUSS_PETRIFIED_SPIKE("Maltorius's Petrified Spike"),
	TAL_RASHAS_UNWAVERING_GLARE("Tal Rasha's Unwavering Glare"),
	FIREBIRDS_TALON("Firebird's Talon"),
	CHANTODOS_WILL("Chantodo's Will"),
	CHANTODOS_FORCE("Chantodo's Force"),
	ETCHED_SIGIL("Etched Sigil"),
	SERPENTS_SPARKER("Serpent's Sparker"),
	ZUNIMASSAS_STRING_OF_SKULLS("Zunimassa's String of Skulls"),
	SACRED_HARVESTER("Sacred Harvester"),
	STAFF_OF_CHIROPTERA("Staff of Chiroptera"),
	GAZING_DEMISE("Gazing Demise"),
	BORNS_FURIOUS_WRATH("Born's Furious Wrath"),
	BORNS_PRIVILEGE("Born's Privilege"),
	LITTLE_ROGUE("Little Rogue"),
	THE_SLANDERER("The Slanderer"),
	FLAIL_OF_THE_CHARGE("Flail of the Charge"),
	SHIELD_OF_THE_STEED("Shield of the Steed");

	private final String label;

	WeaponEnum(String label) {
		this.label = label;
	}

	/**
	 * Returns the label (display name) of the weapon.
	 *
	 * @return weapon label as a string
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Returns the label of a randomly selected weapon from the enum.
	 *
	 * @return a random weapon label as a string
	 */
	public static String getRandom() {
		WeaponEnum[] values = WeaponEnum.values();
		int index = new Random().nextInt(values.length);
		return values[index].getLabel();
	}
}
