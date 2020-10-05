package com.michin.ai.common.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.languagetool.JLanguageTool;
import org.languagetool.language.English;
import org.languagetool.rules.CategoryId;

import com.michin.ai.chat.model.Check;

public class LanguageToolUtil {

	private JLanguageTool langTool;

	public LanguageToolUtil() {
		langTool = new JLanguageTool(new English());
		try {
			langTool.activateLanguageModelRules(new File("data/ngrams-en"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Check> spellCheck(String msg) {
		List<Check> check = null;

		try {
			check = langTool.check(msg).stream().map(m -> new Check(m)).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return check;
	}

	public static void main(String[] args) {
		System.out.println(new LanguageToolUtil().spellCheck(
				"LanguageTool offers spell and grammar checking. Just paste your text here and click the 'Check Text' button. Click the colored phrases for details on potential errors. or use this text too see an few of of the problems that LanguageTool can detecd. What do you thinks of grammar checkers? Please not that they are not perfect. Style issues get a blue marker: It's 5 P.M. in the afternoon. The weather was nice on Thursday, 27 June 2017."));

	}

}
