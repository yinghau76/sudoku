package idv.patrick.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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
	private final Rect selRect = new Rect();
	
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
		getRect(selX, selY, selRect);
		
		Log.d(TAG, "onSizeChanged: width = " + width + ", height = " + height);
	}

	private void getRect(int x, int y, Rect rect) {
	      rect.set(
	    		  (int) (x * width), 
	    		  (int) (y * height), 
	    		  (int) (x * width + width), 
	    		  (int) (y * height + height));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// Draw the background...
		Paint background = new Paint();
		background.setColor(getResources().getColor(R.color.puzzle_background));
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);
		
		// Draw the board...
		Paint dark = new Paint();
		dark.setColor(getResources().getColor(R.color.puzzle_dark));
		Paint hilite = new Paint();
		hilite.setColor(getResources().getColor(R.color.puzzle_hilite));
		Paint light = new Paint();
		light.setColor(getResources().getColor(R.color.puzzle_light));
		
		for (int i = 0; i < 9; i++) {
			canvas.drawLine(0, i * height, getWidth(), i * height, light);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilite);
			canvas.drawLine(i * width, 0, i * width, getHeight(), light);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilite);
		}
	}
}
