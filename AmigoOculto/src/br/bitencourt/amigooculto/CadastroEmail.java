package br.bitencourt.amigooculto;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroEmail extends Activity {
	TextView text;
	EditText edit_nome;
	EditText edit_email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_email);
	}

	public void onClickCadastra() {
		text = (TextView) findViewById(R.id.resultado);
		edit_nome = (EditText) findViewById(R.id.editText1);
		edit_email = (EditText) findViewById(R.id.editText2);

		Intent it = getIntent();
		if (it != null) {
			String amg = it.getStringExtra("amg");
			if (amg != null) {
				text.setText(amg);
				int n = Integer.parseInt(amg);
				ArrayList<String> nome = new ArrayList<String>();
				ArrayList<String> email = new ArrayList<String>();

				for (int x = 0; x < n; x++) {
					nome.add(x,edit_nome.getText().toString());
					email.add(x,edit_email.getText().toString());
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_email, menu);
		return true;
	}

}
