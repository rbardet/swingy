package en.swingy.equipement.armor;

import java.util.Random;

public enum ArmorEnum {
	ARCHON_ARMOR("Archon Armor"),
	ARMOR_OF_THE_KIND_REGENT("Armor of the Kind Regent"),
	ARKAINES_VALOR("Arkaine's Valor"),
	ARACHYRS_CARAPACE("Arachyr's Carapace"),
	ASCENDED_ARMOR("Ascended Armor"),
	ASTRAL_MAIL("Astral Mail"),
	AQUILA_CUIRASS("Aquila Cuirass"),
	AUGHILDS_DOMINION("Aughild's Dominion"),
	AUGHILDS_RULE("Aughild's Rule"),
	BALOR_ARMOR("Balor Armor"),
	BANDED_CUIRASS("Banded Cuirass"),
	BATTLE_ARMOR("Battle Armor"),
	BLACKTHORNES_SURCOAT("Blackthorne's Surcoat"),
	BLOODSONG_MAIL("Bloodsong Mail"),
	BONEWEAVE_HAUBERK("Boneweave Hauberk"),
	BORNS_FROZEN_SOUL("Born's Frozen Soul"),
	BORNS_HEART_OF_STEEL("Born's Heart of Steel"),
	BREASTPLATE_OF_AKKHAN("Breastplate of Akkhan"),
	BRIGANDINE_COAT("Brigandine Coat"),
	BRIGANDINE_OF_VALOR("Brigandine of Valor"),
	CHAINGMAIL("Chaingmail"),
	CHAIN_MAIL("Chain Mail"),
	CINDERCOAT("Cindercoat"),
	CLOTH_TUNIC("Cloth Tunic"),
	CUIRASS_OF_THE_WASTES("Cuirass of the Wastes"),
	DEMONS_HEART("Demon's Heart"),
	DEMONS_MARROW("Demon's Marrow"),
	DOOM_ARMOR("Doom Armor"),
	ETCHED_JACKET("Etched Jacket"),
	FIREBIRDS_BREAST("Firebird's Breast"),
	GODLY_PLATE_OF_THE_WHALE("Godly Plate of the Whale"),
	GOLDSKIN("Goldskin"),
	HELLTOOTH_TUNIC("Helltooth Tunic"),
	HARNESS_OF_TRUTH("Harness of Truth"),
	HEART_OF_IRON("Heart of Iron"),
	HEART_OF_THE_CRASHING_WAVE("Heart of the Crashing Wave"),
	HEART_OF_THE_LIGHT("Heart of the Light"),
	IMMORTAL_KINGS_ETERNAL_REIGN("Immortal King's Eternal Reign"),
	INARIUSS_CONVICTION("Inarius's Conviction"),
	INNAS_VAST_EXPANSE("Inna's Vast Expanse"),
	JADE_HARVESTERS_PEACE("Jade Harvester's Peace"),
	JAZERAINT_MAIL("Jazeraint Mail"),
	LAMELLARS_OF_JUSTICE("Lamellars of Justice"),
	LEATHER_DOUBLET("Leather Doublet"),
	MANTLE_OF_THE_RYDRAELM("Mantle of the Rydraelm"),
	MARAUDERS_CARAPACE("Marauder's Carapace"),
	MARKINGS_OF_SAVAGES("Markings of Savages"),
	MUNDUNUGUS_ROBE("Mundunugu's Robe"),
	MYSTERY_CHEST_ARMOR("Mystery Chest Armor"),
	PESTILENCE_ROBE("Pestilence Robe"),
	PLATE_MAIL("Plate Mail"),
	RAEKORS_HEART("Raekor's Heart"),
	RAKKISGARD_ARMOR("Rakkisgard Armor"),
	RATHMAS_RIBCAGE_PLATE("Rathma's Ribcage Plate"),
	REQUIEM_CEREPLATE("Requiem Cereplate"),
	ROBES_OF_THE_RYDRAELM("Robes of the Rydraelm"),
	ROLANDS_BEARING("Roland's Bearing"),
	SHI_MIZUS_HAORI("Shi Mizu's Haori"),
	SOPHISTICATED_VEST("Sophisticated Vest"),
	SPIRIT_OF_THE_EARTH("Spirit of the Earth"),
	STYGIAN_HARNESS("Stygian Harness"),
	SUNWUKOS_SOUL("Sunwuko's Soul"),
	TAL_RASHAS_RELENTLESS_PURSUIT("Tal Rasha's Relentless Pursuit"),
	THE_SHADOWS_BANE("The Shadow's Bane"),
	TRAGOULS_SCALES("Trag'Oul's Scales"),
	TYPHONS_THORAX("Typhon's Thorax"),
	TYRAELS_MIGHT("Tyrael's Might"),
	ULIANAS_HEART("Uliana's Heart"),
	VYRS_ASTONISHING_AURA("Vyr's Astonishing Aura"),
	WARLORD_PLATE("Warlord Plate"),
	ZUNIMASSAS_MARROW("Zunimassa's Marrow");

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
