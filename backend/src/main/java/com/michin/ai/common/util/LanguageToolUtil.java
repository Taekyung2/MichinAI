package com.michin.ai.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.languagetool.JLanguageTool;
import org.languagetool.language.English;
import org.languagetool.rules.PunctuationMarkAtParagraphEnd;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.spelling.SpellingCheckRule;

import com.michin.ai.chat.model.Check;

public class LanguageToolUtil {

	private JLanguageTool lt;

	public LanguageToolUtil() {
		lt = new JLanguageTool(new English());
		try {
			lt.activateLanguageModelRules(new File("/home/ubuntu/data"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Check> spellCheck(String msg) {
		List<Check> check = null;

		try {
			check = lt.check(msg).stream().map(m -> new Check(m)).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return check;
	}

//	public static void main(String[] args) {
//		String text = "LanguageTool offers spell and grammar checking. Just paste your text here and click the 'Check Text' button. Click the colored phrases for details on potential errors. or use this text too see an few of of the problems that LanguageTool can detecd. What do you thinks of grammar checkers? Please not that they are not perfect. Style issues get a blue marker: It's 5 P.M. in the afternoon. The weather was nice on Thursday, 27 June 2017.";
//		JLanguageTool lt = new JLanguageTool(new English());
//		try {
//			for (RuleMatch match : lt.check(text)) {
//				System.out.println(match);
//				System.out.println(match.getMessage());
//				System.out.println(match.getRule().getCategory());
//				System.out.println(match.getRule().getDescription());
//				System.out.println("---------------------------------------");
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
