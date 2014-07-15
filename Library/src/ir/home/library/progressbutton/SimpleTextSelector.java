package ir.home.library.progressbutton;

import android.content.Context;

public class SimpleTextSelector implements TextSelector {

	private String checkedText;
	private String loadingText;
	private String uncheckedText;

	public SimpleTextSelector() {
	}

	public SimpleTextSelector(String checkedText,
	        String loadingText, String uncheckedText) {
		super();
		this.checkedText = checkedText;
		this.loadingText = loadingText;
		this.uncheckedText = uncheckedText;
	}

	public SimpleTextSelector(Context context, int checkedText,
			int uncheckedText) {
		super();
		this.checkedText = (String) context.getText(checkedText);
		this.uncheckedText = (String) context.getText(uncheckedText);
	}

	public SimpleTextSelector(Context context, int checkedText,
			int uncheckedText, int loadingText) {
		super();
		this.loadingText = (String) context.getText(loadingText);
		this.checkedText = (String) context.getText(checkedText);
		this.uncheckedText = (String) context.getText(uncheckedText);
	}

	public String getSelectedText() {
		return checkedText;
	}

	public String getLoadingText() {
		return loadingText;
	}

	public String getText(boolean selected) {
		return selected ? getSelectedText() : getUnselectedText();
	}

	public String getUnselectedText() {
		return uncheckedText;
	}

	public void setLoadingText(String loadingText) {
		this.loadingText = loadingText;
	}

	public void setSelectedText(String checkedText) {
		this.checkedText = checkedText;
	}

	public void setUnselectedText(String uncheckedText) {
		this.uncheckedText = uncheckedText;
	}

}
