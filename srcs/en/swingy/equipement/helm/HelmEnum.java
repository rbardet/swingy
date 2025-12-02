package en.swingy.equipement.helm;

import java.util.Random;

public enum HelmEnum {
	IMMORTAL_KINGS_TRIUMPH("Immortal King's Triumph"),
	RAEKORS_WILL("Raekor's Will"),
	HELM_OF_THE_WASTES("Helm of the Wastes"),
	SPIRIT_OF_THE_EARTH("Spirit of the Earth"),
	SAVAGE_MASK("Savage Mask"),

	HELMET_OF_AKKHAN("Helmet of Akkhan"),
	CROWN_OF_VALOR("Crown of Valor"),
	CROWN_OF_THE_LIGHT("Crown of the Light"),
	BURDEN_OF_THE_INVOKER("Burden of the Invoker"),
	ROLANDS_VISAGE("Roland's Visage"),

	NATALYAS_SIGHT("Natalya's Sight"),
	MARAUDERS_VISAGE("Marauder's Visage"),
	ACCURSED_VISAGE("Accursed Visage"),
	THE_SHADOWS_MASK("The Shadow's Mask"),
	DREADLANDS_VISAGE("Dreadlands Visage"),

	INNAS_RADIANCE("Inna's Radiance"),
	SUNWUKOS_CROWN("Sunwuko's Crown"),
	ULIANAS_SPIRIT("Uliana's Spirit"),
	MASK_OF_THE_SEARING_SKY("Mask of the Searing Sky"),
	PATTERNS_OF_JUSTICE_HELM("Patterns of Justice Helm"),

	RATHMAS_SKULL_HELM("Rathma's Skull Helm"),
	INARIUSS_UNDERSTANDING("Inarius's Understanding"),
	PESTILENCE_MASK("Pestilence Mask"),
	TRAGOULS_GUISE("Trag'Oul's Guise"),
	CARNIVAL_MASK("Carnival Mask"),

	TAL_RASHAS_GUISE_OF_WISDOM("Tal Rasha's Guise of Wisdom"),
	FIREBIRDS_PLUME("Firebird's Plume"),
	DELSERES_HOROLOGIUM("Delsere's Horologium"),
	VYRS_SIGHTLESS_SKULL("Vyr's Sightless Skull"),
	TYPHONS_FRONS("Typhon's Frons"),

	ZUNIMASSAS_VISION("Zunimassa's Vision"),
	JADE_HARVESTERS_WISDOM("Jade Harvester's Wisdom"),
	HELLTOOTH_MASK("Helltooth Mask"),
	ARACHYRS_VISAGE("Arachyr's Visage"),
	MUNDUNUGUS_HEADDRESS("Mundunugu's Headdress"),

	BORNS_SKULLS("Born's Skulls"),
	AUGHILDS_PEAK("Aughild's Peak"),
	SAGES_APOGEE("Sage's Apogee"),
	CAINS_INSIGHT("Cain's Insight"),
	ASHEARAS_SIGHT("Asheara's Sight"),
	GUARDIANS_GAZE("Guardian's Gaze"),
	BLACKTHORNES_MASK("Blackthorne's Mask");

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
}
