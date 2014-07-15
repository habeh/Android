package ir.home.library.progressbutton;

import ir.home.library.R;
import ir.home.library.R.drawable;
import ir.home.library.R.styleable;

import java.util.Arrays;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.Button;

public class ProgressButton extends Button {

	private static final int MAX_LEVEL = 10000;
	private Animation animation;
	private boolean autoDisableClickable;

	private boolean hasAnimation;
	private boolean loading;
	private Drawable loadingDrawable;
	private boolean mShouldStartAnimationDrawable;

	private Drawable[] oldCompoundDrawables;
	private TextSelector textSelector;
	private Transformation transformation;

	public ProgressButton(Context context) {
		super(context);
		init(context);
	}

	public ProgressButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public ProgressButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	private void checkState() {
		if (textSelector != null) {
			if (!loading) {
				setText(textSelector.getText(isSelected()));
			} else {
				setText(textSelector.getLoadingText());
			}
		}
	}

	/**
	 * Hide the animated spinner and show the selected / unselected text
	 */
	public void disableLoadingState() {
		setText(textSelector.getText(isSelected()));
		if (oldCompoundDrawables != null) {
			setCompoundDrawablesWithIntrinsicBounds(oldCompoundDrawables[0],
					oldCompoundDrawables[1], oldCompoundDrawables[2],
					oldCompoundDrawables[3]);
		}
		if (autoDisableClickable) {
			setClickable(true);
		}
		loading = false;
	}

	/**
	 * Show the animated Spinner, and hide the previous text
	 */
	public void enableLoadingState() {
		setText(textSelector.getLoadingText());
		oldCompoundDrawables = Arrays.copyOf(getCompoundDrawables(), 4);
		setCompoundDrawables(null, null, null, null);
		if (autoDisableClickable) {
			setClickable(false);
		}
		loading = true;
	}

	public TextSelector getTextSelector() {
		return textSelector;
	}

	private void init(Context context) {
		textSelector = new SimpleTextSelector();
		loadingDrawable = null;
	}

	private void init(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ProgressButton);

		if (a.hasValue(R.styleable.ProgressButton_loadingDrawable)) {
			loadingDrawable = a
					.getDrawable(R.styleable.ProgressButton_loadingDrawable);
		} else {
			loadingDrawable = getResources().getDrawable(
					R.drawable.default_spinner);
		}

		SimpleTextSelector simpleTextSelector = new SimpleTextSelector();

		simpleTextSelector.setLoadingText("");
		simpleTextSelector.setSelectedText((String) getText());
		simpleTextSelector.setUnselectedText((String) getText());

		if (a.hasValue(R.styleable.ProgressButton_selectedText)) {
			simpleTextSelector.setSelectedText(a
					.getString(R.styleable.ProgressButton_selectedText));
		}
		if (a.hasValue(R.styleable.ProgressButton_unselectedText)) {
			simpleTextSelector.setUnselectedText(a
					.getString(R.styleable.ProgressButton_unselectedText));
		}
		if (a.hasValue(R.styleable.ProgressButton_loadingText)) {
			simpleTextSelector.setLoadingText(a
					.getString(R.styleable.ProgressButton_loadingText));
		}

		if (a.hasValue(R.styleable.ProgressButton_loading)) {
			loading = a.getBoolean(R.styleable.ProgressButton_loading, false);
		}

		if (a.hasValue(R.styleable.ProgressButton_autoDisableClickable)) {
			autoDisableClickable = a.getBoolean(
					R.styleable.ProgressButton_autoDisableClickable, false);
		}

		textSelector = simpleTextSelector;
		a.recycle();

		if (loadingDrawable != null) {
			loadingDrawable.setBounds(0, 0,
					loadingDrawable.getIntrinsicWidth(),
					loadingDrawable.getIntrinsicHeight());
			if (!(loadingDrawable instanceof Animatable)) {
				animation = new AlphaAnimation(0f, 1f);
				animation.setRepeatMode(Animation.RESTART);
				animation.setRepeatCount(Animation.INFINITE);
				animation.setDuration(2000);
				animation.setInterpolator(new LinearInterpolator());
				animation.setStartTime(Animation.START_ON_FIRST_FRAME);
				hasAnimation = true;
				transformation = new Transformation();
			}

			if (loading) {
				enableLoadingState();
			} else {
				disableLoadingState();
			}
		}
	}

	/**
	 * 
	 * @return true if a loading animation is shown
	 */
	public boolean isLoading() {
		return loading;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (loadingDrawable != null && loading) {
			canvas.save();
			int left = getWidth() / 2 - loadingDrawable.getMinimumWidth() / 2;
			int top = getHeight() / 2 - loadingDrawable.getMinimumHeight() / 2;
			canvas.translate(left, top);

			loadingDrawable.draw(canvas);
			canvas.restore();

			long time = getDrawingTime();
			if (hasAnimation) {
				animation.getTransformation(time, transformation);
				float scale = transformation.getAlpha();
				try {
					loadingDrawable.setLevel((int) (scale * MAX_LEVEL));
				} finally {
				}
				ViewCompat.postInvalidateOnAnimation(this);
			}
			if (mShouldStartAnimationDrawable
					&& loadingDrawable instanceof Animatable) {
				((Animatable) loadingDrawable).start();
				mShouldStartAnimationDrawable = false;
			}
		}
	}

	/**
	 * Enables disables loading, and sets the selected / unselected state.
	 * Enables loading when state equals null, and selects / unselects the
	 * button based on the states value.
	 * 
	 * @param state
	 *            , null - enables loading, true/ false - disables loading, sets
	 *            selected/ unselected
	 */
	public void setLoadingState(Boolean state) {
		if (state == null) {
			enableLoadingState();
		} else {
			setSelected(state);
			disableLoadingState();
		}
	}

	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		checkState();
	}

	/**
	 * Sets the TextSelector, which holds a loading/ selected/ unselected text
	 * for the button
	 * 
	 * @param textSelector
	 */
	public void setTextSelector(TextSelector textSelector) {
		this.textSelector = textSelector;
		checkState();
	}

}
