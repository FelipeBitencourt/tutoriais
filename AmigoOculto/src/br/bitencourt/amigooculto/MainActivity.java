package br.bitencourt.amigooculto;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edit = (EditText) findViewById(R.id.edit_numeros_amigos);
	}

	public void onClickCadastro(View v) {
		Intent it = new Intent(this, CadastroEmail.class);
		int amigos = Integer.parseInt(edit.getText().toString());
		while (amigos <= 1) {
			Toast.makeText(this, "Você deve cadastrar no mínimo 2 amigos!",
					Toast.LENGTH_SHORT).show();
			amigos = Integer.parseInt(edit.getText().toString());
			return;
		}
		it.putExtra("amg", amigos);
		startActivity(it);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
