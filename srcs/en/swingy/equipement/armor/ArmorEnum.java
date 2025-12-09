package en.swingy.equipement.armor;

import java.util.Random;

public enum ArmorEnum {
	GODLY_PLATE_OF_THE_WHALE("Godly Plate of the Whale"),
	ARKAINES_VALOR("Arkaine's Valor"),
	MYSTERY_CHEST_ARMOR("Mystery Chest Armor"),
	CLOTH_TUNIC("Cloth Tunic"),
	LEATHER_DOUBLET("Leather Doublet"),
	HIDE_TUNIC("Hide Tunic"),
	BRIGANDINE_COAT("Brigandine Coat"),
	HEART_OF_IRON("Heart of Iron"),
	CHAIN_MAIL("Chain Mail"),
	SPLINT_CUIRASS("Splint Cuirass"),
	BORNS_HEART_OF_STEEL("Born's Heart of Steel"),
	AQUILA_CUIRASS("Aquila Cuirass"),
	BANDED_CUIRASS("Banded Cuirass"),
	PLATE_MAIL("Plate Mail"),
	CHAINGMAIL("Chaingmail"),
	STYGIAN_HARNESS("Stygian Harness"),
	SHI_MIZUS_HAORI("Shi Mizu's Haori"),
	CINDERCOAT("Cindercoat"),
	ETCHED_JACKET("Etched Jacket"),
	JAZERAINT_MAIL("Jazeraint Mail"),
	AUGHILDS_DOMINION("Aughild's Dominion"),
	BATTLE_ARMOR("Battle Armor"),
	GOLDSKIN("Goldskin"),
	BONEWEAVE_HAUBERK("Boneweave Hauberk"),
	BALOR_ARMOR("Balor Armor"),
	ASTRAL_MAIL("Astral Mail"),
	WARLORD_PLATE("Warlord Plate"),
	ZUNIMASSAS_MARROW("Zunimassa's Marrow"),
	IMMORTAL_KINGS_ETERNAL_REIGN("Immortal King's Eternal Reign"),
	BLACKTHORNES_SURCOAT("Blackthorne's Surcoat"),
	DEMONS_HEART("Demon's Heart"),
	TAL_RASHAS_RELENTLESS_PURSUIT("Tal Rasha's Relentless Pursuit"),
	TYRAELS_MIGHT("Tyrael's Might"),
	INNAS_VAST_EXPANSE("Inna's Vast Expanse"),
	ROBES_OF_THE_RYDRAELM("Robes of the Rydraelm"),
	ARCHON_ARMOR("Archon Armor"),
	DOOM_ARMOR("Doom Armor"),
	SOVEREIGN_MAIL("Sovereign Mail"),
	RAKKISGARD_ARMOR("Rakkisgard Armor"),
	BLOODSONG_MAIL("Bloodsong Mail"),
	JADE_HARVESTERS_PEACE("Jade Harvester's Peace"),
	MANTLE_OF_THE_RYDRAELM("Mantle of the Rydraelm"),
	DEMONS_MARROW("Demon's Marrow"),
	AUGHILDS_RULE("Aughild's Rule"),
	ARMOR_OF_THE_KIND_REGENT("Armor of the Kind Regent"),
	SPIRIT_OF_THE_EARTH("Spirit of the Earth"),
	BORNS_FROZEN_SOUL("Born's Frozen Soul"),
	THE_SHADOWS_BANE("The Shadow's Bane"),
	SUNWUKOS_SOUL("Sunwuko's Soul"),
	VYRS_ASTONISHING_AURA("Vyr's Astonishing Aura"),
	ASCENDED_ARMOR("Ascended Armor"),
	CUIRASS_OF_THE_WASTES("Cuirass of the Wastes"),
	MARKINGS_OF_SAVAGES("Markings of Savages"),
	RAEKORS_HEART("Raekor's Heart"),
	ROLANDS_BEARING("Roland's Bearing"),
	BREASTPLATE_OF_AKKHAN("Breastplate of Akkhan"),
	HEART_OF_THE_LIGHT("Heart of the Light"),
	BRIGANDINE_OF_VALOR("Brigandine of Valor"),
	MARAUDERS_CARAPACE("Marauder's Carapace"),
	ULIANAS_HEART("Uliana's Heart"),
	HEART_OF_THE_CRASHING_WAVE("Heart of the Crashing Wave"),
	LAMELLARS_OF_JUSTICE("Lamellars of Justice"),
	FIREBIRDS_BREAST("Firebird's Breast"),
	TYPHONS_THORAX("Typhon's Thorax"),
	HARNESS_OF_TRUTH("Harness of Truth"),
	MUNDUNUGUS_ROBE("Mundunugu's Robe"),
	HELLTOOTH_TUNIC("Helltooth Tunic"),
	ARACHYRS_CARAPACE("Arachyr's Carapace"),
	SOPHISTICATED_VEST("Sophisticated Vest"),
	TRAGOULS_SCALES("Trag'Oul's Scales"),
	RATHMAS_RIBCAGE_PLATE("Rathma's Ribcage Plate"),
	PESTILENCE_ROBE("Pestilence Robe"),
	INARIUSS_CONVICTION("Inarius's Conviction"),
	REQUIEM_CEREPLATE("Requiem Cereplate");

	private final String label;

	ArmorEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static String getRandom() {
		ArmorEnum[] values = ArmorEnum.values();
		int index = new Random().nextInt(values.length);
		return values[index].getLabel();
	}
}