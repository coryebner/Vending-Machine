package business.config;

class ConfigurationBuilder {
	private static String buildType(String type) throws ConfigurationException {
		if (type == Configuration.SFFPC || type == Configuration.SFFPCI || type == Configuration.SFFPPI) {
			return type + "\n"
					+ "names Coke Pepsi 7-up Sprite Fanta Crush\n"
					+ "prices 100 100 100 100 100 100\n"
					+ "quantities 20 20 20 20 20 20\n"
					+ "coinracks 0 0 0 0 0 0\n"
					+ "coinstorage 0 0 0 0 0 0\n"
					+ "billstorage 0 0 0 0 0\n"
					+ "locale CA\n";
		}
		
		if (type == Configuration.COMPM
			|| type == Configuration.COMPMI
			|| type == Configuration.TOCPI
			|| type == Configuration.TOCPMI)
		{
			return type + "\n"
					+ "names Coke Pepsi 7-up Sprite Fanta Crush Mug Barqs DietCoke DietPepsi Lemonade Water\n"
					+ "prices 100 100 100 100 100 100 100 100 100 100 100 100\n"
					+ "quantities 20 20 20 20 20 20 20 20 20 20 20 20\n"
					+ "coinracks 0 0 0 0 0 0\n"
					+ "coinstorage 0 0 0 0 0 0\n"
					+ "billstorage 0 0 0 0 0\n"
					+ "locale CA\n";
		}
		
		if (type == Configuration.COMCM
			|| type == Configuration.COMCMI
			|| type == Configuration.TOCCMI)
		{
			return type + "\n"
				+ "names Coke Pepsi 7-up Sprite Fanta Crush Mug Barqs DietCoke DietPepsi Lemonade Water "
				+ "pop13 pop14 pop15 pop16 pop17 pop18 pop19 pop20 pop21 pop22 pop23 pop24\n"
				+ "prices 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100\n"
				+ "quantities 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20\n"
				+ "coinracks 0 0 0 0 0 0\n"
				+ "coinstorage 0 0 0 0 0 0\n"
				+ "billstorage 0 0 0 0 0\n"
				+ "locale CA\n";
		}
		
		if (type == Configuration.TOCCp || type == Configuration.TOCCpI) {
			return type + "\n"
					+ "names Coke Pepsi 7-up Sprite Fanta Crush Mug Barqs DietCoke DietPepsi Lemonade Water "
					+ "candy1 candy2 candy3 candy4 candy5 candy6 candy7 candy8 candy9 candy10 candy11 candy12 "
					+ "candy13 candy14 candy15 candy16 candy17 candy18 candy19 candy20 candy21 candy22 candy23 candy24 "
					+ "candy25 candy26 candy27 candy28 candy29 candy30 candy31 candy32 candy33 candy34 candy35 candy36\n"
					+ "prices 100 100 100 100 100 100 100 100 100 100 100 100 "
					+ "100 100 100 100 100 100 100 100 100 100 100 100 "
					+ "100 100 100 100 100 100 100 100 100 100 100 100\n"
					+ "quantities 20 20 20 20 20 20 20 20 20 20 20 20 "
					+ "20 20 20 20 20 20 20 20 20 20 20 20 "
					+ "20 20 20 20 20 20 20 20 20 20 20 20\n"
					+ "coinracks 0 0 0 0 0 0\n"
					+ "coinstorage 0 0 0 0 0 0\n"
					+ "billstorage 0 0 0 0 0\n"
					+ "locale CA\n";
			
		}
		
		throw new ConfigurationException("Invalid machine type " + type);
	}
	
	private static String buildLog(String logFrequency) {
		return "logfrequency " + logFrequency + "\n";
	}
	
	public static String build(String type, String logFrequency) throws ConfigurationException {
		return buildType(type) + buildLog(logFrequency);
	}
}
