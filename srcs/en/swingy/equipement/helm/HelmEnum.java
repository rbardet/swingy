package en.swingy.equipement.helm;

import java.util.Random;

public enum HelmEnum {
	VEIL_OF_STEEL("Veil of Steel"),
	LEORICS_CROWN("Leoric's Crown"),
	HARLEQUIN_CREST("Harlequin Crest"),
	THE_UNDEAD_CROWN("The Undead Crown"),
	MYSTERY_HELM("Mystery Helm"),
	HELM_OF_THE_CRANIAL_CRUSTACEAN("Helm of the Cranial Crustacean"),
	STAR_HELM("Star Helm"),
	LEATHER_HOOD("Leather Hood"),
	COIF("Coif"),
	PRIDES_FALL("Pride's Fall"),
	ARMING_CAP("Arming Cap"),
	CHAIN_HOOD("Chain Hood"),
	BROKEN_CROWN("Broken Crown"),
	HELMET("Helmet"),
	CAINS_MEMORY("Cain's Memory"),
	PLATED_HELM("Plated Helm"),
	BLIND_FAITH("Blind Faith"),
	CROWN("Crown"),
	DEATHSEERS_COWL("Deathseer's Cowl"),
	WARHELM_OF_KASSAR("Warhelm of Kassar"),
	VISAGE_OF_GUNES("Visage of Gunes"),
	BASINET("Basinet"),
	KLAPPVISIER("Klappvisier"),
	MASK_OF_SCARLET_DEATH("Mask of Scarlet Death"),
	AUGHILDS_PEAK("Aughild's Peak"),
	CASQUE("Casque"),
	SKULL_OF_RESONANCE("Skull of Resonance"),
	GREAT_HELM("Great Helm"),
	HOUNSKULL("Hounskull"),
	GUARDIANS_FORESIGHT("Guardian's Foresight"),
	STECHHELM("Stechhelm"),
	ZISCHAGGE("Zischagge"),
	NATALYAS_SIGHT("Natalya's Sight"),
	MEMPO_OF_TWILIGHT("Mempo of Twilight"),
	TAL_RASHAS_GUISE_OF_WISDOM("Tal Rasha's Guise of Wisdom"),
	SAGES_ORBIT("Sage's Orbit"),
	IMMORTAL_KINGS_TRIUMPH("Immortal King's Triumph"),
	THE_HELM_OF_COMMAND("The Helm of Command"),
	ANDARIELS_VISAGE("Andariel's Visage"),
	HELLSCAPE_MASK("Hellscape Mask"),
	SOVEREIGN_HELM("Sovereign Helm"),
	ARCHON_CROWN("Archon Crown"),
	FATES_VOW("Fate's Vow"),
	RAKKISGARD_HELM("Rakkisgard Helm"),
	JADE_HARVESTERS_WISDOM("Jade Harvester's Wisdom"),
	THE_HELM_OF_RULE("The Helm of Rule"),
	SUNWUKOS_CROWN("Sunwuko's Crown"),
	SAGES_APOGEE("Sage's Apogee"),
	VYRS_SIGHTLESS_SKULL("Vyr's Sightless Skull"),
	CAINS_INSIGHT("Cain's Insight"),
	CROWN_OF_THE_INVOKER("Crown of the Invoker"),
	AUGHILDS_SPIKE("Aughild's Spike"),
	THE_SHADOWS_MASK("The Shadow's Mask"),
	EYES_OF_THE_EARTH("Eyes of the Earth"),
	GUARDIANS_GAZE("Guardian's Gaze"),
	ASCENDED_CROWN("Ascended Crown"),
	RAEKORS_WILL("Raekor's Will"),
	HELM_OF_THE_WASTES("Helm of the Wastes"),
	SKULL_OF_SAVAGES("Skull of Savages"),
	CROWN_OF_THE_LIGHT("Crown of the Light"),
	CROWN_OF_VALOR("Crown of Valor"),
	ROLANDS_VISAGE("Roland's Visage"),
	HELM_OF_AKKHAN("Helm of Akkhan"),
	ACCURSED_VISAGE("Accursed Visage"),
	DYSTOPIAN_GOGGLES("Dystopian Goggles"),
	MARAUDERS_VISAGE("Marauder's Visage"),
	MASK_OF_THE_SEARING_SKY("Mask of the Searing Sky"),
	ULIANAS_SPIRIT("Uliana's Spirit"),
	DECREE_OF_JUSTICE("Decree of Justice"),
	FIREBIRDS_PLUME("Firebird's Plume"),
	SHROUDED_MASK("Shrouded Mask"),
	TYPHONS_FRONS("Typhon's Frons"),
	ARACHYRS_VISAGE("Arachyr's Visage"),
	HELLTOOTH_MASK("Helltooth Mask"),
	LUXURIOUS_BAUTA("Luxurious Bauta"),
	TRAGOULS_GUISE("Trag'Oul's Guise"),
	INARIUSS_UNDERSTANDING("Inarius's Understanding"),
	PESTILENCE_MASK("Pestilence Mask"),
	RATHMAS_SKULL_HELM("Rathma's Skull Helm");

	private final String label;

	HelmEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static String getRandom() {
		HelmEnum[] values = HelmEnum.values();
		int index = new Random().nextInt(values.length);
		return values[index].getLabel();
	}

	public static String findAssetByName(String name) {
		HelmEnum[] values = HelmEnum.values();
		for (HelmEnum value : values) {
			if (value.getLabel().equals(name)) {
				return HelmAssets.getAsset(value.name());
			}
		}
		return null;
	}
}