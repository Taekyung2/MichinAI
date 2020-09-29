package com.michin.ai.common.util.notification;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
public class Attachments {
	private Props props;
	private List<Attachment> attachments;

	@Builder
	public Attachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Attachments(Exception e, String uri, String params) {
		attachments = new ArrayList<>();
		Attachment attach = new Attachment(e, uri, params);
		attachments.add(attach);

		props = new Props(e, uri, params);
	}
}

@Getter
@AllArgsConstructor
@Builder
@ToString
class Attachment {
	private String pretext;
	private String color;
	private String author_name;
	private String author_icon;
	private String title;
	private String text;

	private String footer;

	public Attachment() {
		this.color = "#ff5d52";
		this.pretext = "@here";
		this.author_name = "Back-End ERROR";
		this.author_icon = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRO_K5_S6MDApkQ-jfChAWwy_iyWn-Nl-DhbA&usqp=CAU";
	}

	public Attachment(String errorMsg, String errorText) {
		this();
		this.title = errorMsg;
		this.text = errorText;
	}

	public Attachment(Exception e) {
		this();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		this.title = e.getLocalizedMessage();

		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		this.text = sw.toString();

		this.footer = format.format(System.currentTimeMillis());
	}

	public Attachment(Exception e, String uri, String params) {
		this();

		this.title = e.getClass().getSimpleName();

		StringBuilder sb = new StringBuilder();
		sb.append("**Error Message**").append('\n').append('\n').append("```");
		sb.append(e.getMessage()).append("```").append('\n').append('\n');

		sb.append("**Reqeust URL**").append('\n').append('\n');
		sb.append(uri).append('\n').append('\n');

		sb.append("**Parameters**").append('\n').append('\n');
		sb.append(params.toString()).append('\n').append('\n');

		this.text = sb.toString();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		this.footer = format.format(System.currentTimeMillis());
	}

}

@Getter
@NoArgsConstructor
class Props {
	private String card;

	public Props(Exception e, String uri, String params) {
		StringBuilder text = new StringBuilder();

		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		text.append("**Stack Trace**").append("\n").append('\n').append("```");
		text.append(sw.toString().substring(0, 6000) + "...").append('\n').append('\n');

		this.card = text.toString();
	}
}
