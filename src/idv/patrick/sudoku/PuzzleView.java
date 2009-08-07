package idv.patrick.sudoku;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class PuzzleView extends View {
	private static final String TAG = "Sudoku";
	private final Game game;
	private float width;
	private float height;
	private int selX;
	private int selY;
	private final Rect selRect = new Rect(;)
	
	public PuzzleView(Context context) {
		super(context);
		this.game = (Game) context;
		
		setFocusable(true);
		setFocusableInTouchMode(true);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w / 9f;
		height = h / 9f;
		Object selRect;
		getRect(selX, selY, selRect);
		
		Log.d(TAG, "onSizeChanged: width = " + width + ", height = " + height);
	}

	private void getRect(int x, int y, Rect rect) {

	}	
}
