package com.michin.ai.util;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.languagetool.JLanguageTool;
import org.languagetool.language.English;

import com.michin.ai.chat.model.Check;

public class LanguageToolUtil {

	public List<Check> spellCheck(String msg) {
		JLanguageTool langTool = new JLanguageTool(new English());
		List<Check> check = null;
//		langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));
		try {
			check = langTool.check(msg).stream().map(m -> new Check(m)).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return check;
	}

}
