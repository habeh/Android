package ir.home.library.progressbutton;

public interface TextSelector {

	String getSelectedText();

	String getLoadingText();

	String getText(boolean selected);

	String getUnselectedText();
}
