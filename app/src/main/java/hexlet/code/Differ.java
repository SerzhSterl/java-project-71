package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
	public static String generate(String firstFilePath, String secondFilePath) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		// Чтение и парсинг JSON файлов
		Map<String, Object> firstFileData = objectMapper.readValue(new File(firstFilePath), Map.class);
		Map<String, Object> secondFileData = objectMapper.readValue(new File(secondFilePath), Map.class);

		// Создание результирующей карты для хранения различий
		Map<String, String> result = new TreeMap<>();

		// Объединение ключей из обоих файлов
		for (String key : firstFileData.keySet()) {
			if (secondFileData.containsKey(key)) {
				if (!firstFileData.get(key).equals(secondFileData.get(key))) {
					result.put("- " + key + ": " + firstFileData.get(key), null);
					result.put("+ " + key + ": " + secondFileData.get(key), null);
				} else {
					result.put(key + ": " + firstFileData.get(key), null);
				}
			} else {
				result.put("- " + key + ": " + firstFileData.get(key), null);
			}
		}

		// Проверка на ключи, которые есть только во втором файле
		for (String key : secondFileData.keySet()) {
			if (!firstFileData.containsKey(key)) {
				result.put("+ " + key + ": " + secondFileData.get(key), null);
			}
		}

		// Формирование строки результата
		StringBuilder resultString = new StringBuilder("{\n");
		for (String line : result.keySet()) {
			resultString.append("  ").append(line).append("\n");
		}
		resultString.append("}");

		return resultString.toString();
	}
}