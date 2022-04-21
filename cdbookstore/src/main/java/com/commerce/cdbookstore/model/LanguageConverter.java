package com.commerce.cdbookstore.model;

import static com.commerce.cdbookstore.model.Language.DEUTSCH;
import static com.commerce.cdbookstore.model.Language.ENGLISH;
import static com.commerce.cdbookstore.model.Language.FINISH;
import static com.commerce.cdbookstore.model.Language.FRENCH;
import static com.commerce.cdbookstore.model.Language.GERMAN;
import static com.commerce.cdbookstore.model.Language.ITALIAN;
import static com.commerce.cdbookstore.model.Language.PORTUGUESE;
import static com.commerce.cdbookstore.model.Language.RUSSIAN;
import static com.commerce.cdbookstore.model.Language.SPANISH;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LanguageConverter implements AttributeConverter<Language, String> {

	// =================================
	// = Business methods =
	// =================================

	@Override
	public String convertToDatabaseColumn(Language language) {
		switch (language) {
		case DEUTSCH:
			return "DE";

		case ENGLISH:
			return "EN";

		case FINISH:
			return "FI";

		case FRENCH:
			return "FR";

		case GERMAN:
			return "GM";

		case ITALIAN:
			return "IT";

		case PORTUGUESE:
			return "PT";

		case RUSSIAN:
			return "RU";

		case SPANISH:
			return "SP";

		default:
			throw new IllegalArgumentException("Unknown " + language);
		}
	}

	@Override
	public Language convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "DE":
			return DEUTSCH;

		case "EN":
			return ENGLISH;

		case "FI":
			return FINISH;

		case "FR":
			return FRENCH;

		case "GM":
			return GERMAN;

		case "IT":
			return ITALIAN;

		case "PT":
			return PORTUGUESE;

		case "RU":
			return RUSSIAN;

		case "SP":
			return SPANISH;

		default:
			throw new IllegalArgumentException("Unknown " + dbData);
		}
	}

}
