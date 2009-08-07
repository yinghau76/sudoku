package idv.patrick.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class Sudoku extends Activity implements OnClickListener {
    private static final String TAG = "Sudoku";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        
        View newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(this);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			startActivity(new Intent(this, Settings.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.about_button:
			startActivity(new Intent(this, About.class));
			break;
		case R.id.new_button:
			openNewGameDialog();
			break;
		}		
	}

	private void openNewGameDialog() {
		new AlertDialog.Builder(this)
			.setTitle(R.string.new_game_title)
			.setItems(R.array.difficulty, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					startGame(which);
				}
			})
			.show();
	}

	protected void startGame(int difficulty) {
		Log.d(TAG, "clicked on" + difficulty);
		Intent intent = new Intent(Sudoku.this, Game.class);
		intent.putExtra(Game.KEY_DIFFICULTY, difficulty);
		startActivity(intent);
	}

    @Override
    protected void onPause() {
        super.onPause();
        Music.stop(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Music.play(this, R.raw.main);
    }    
}