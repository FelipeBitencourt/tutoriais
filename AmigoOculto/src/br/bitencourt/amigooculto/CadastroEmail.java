package br.bitencourt.amigooculto;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroEmail extends Activity {
	TextView text, text_enviado;
	EditText edit_nome;
	EditText edit_email;
	int x = 0, amg;
	ArrayList<String> nome;
	ArrayList<String> email;
	ArrayList<Integer> sorteador;
	int n = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_email);
		text = (TextView) findViewById(R.id.resultado);
		Intent it = getIntent();
		if (it != null) {
			this.amg = it.getIntExtra("amg", 0);
			if (this.amg != 0) {
				text.setText(Integer.toString(this.amg));
			}
		}
		this.nome = new ArrayList<String>();
		this.email = new ArrayList<String>();

		this.edit_nome = (EditText) findViewById(R.id.editText1);
		this.edit_email = (EditText) findViewById(R.id.editText2);
	}

	public void onClickCadastra(View v) {
		if (x >= amg) {
			Toast.makeText(this, "Todos os emails foram cadastrados!",
					Toast.LENGTH_SHORT).show();
			return;
		}

		text_enviado = (TextView) findViewById(R.id.enviado);

		text.setText(Integer.toString(this.amg));
		this.nome.add(x, edit_nome.getText().toString());
		this.email.add(x, edit_email.getText().toString());

		this.edit_nome.setText("");
		this.edit_email.setText("");

		x++;

		text_enviado.setText(Integer.toString(x));
		if (x == amg) {
			for (int i = 0; i < this.nome.size(); i++) {
				this.sorteador = new ArrayList<Integer>();
				this.sorteador.add(i);
			}
			Collections.shuffle(sorteador);
		}
	}

	public void onClickSortear(View v) {

		this.edit_nome.setEnabled(false);
		this.edit_email.setEnabled(false);

		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

		while (this.email.get(n) == this.nome.get(sorteador.get(n))) {
			Collections.shuffle(sorteador);
		}

		String[] recipients = new String[] { this.email.get(n) };
		emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Amigo Oculto");
		emailIntent
				.putExtra(Intent.EXTRA_TEXT, this.nome.get(sorteador.get(n)));
		emailIntent.setType("plain/text");
		this.nome.remove(sorteador.get(n));
		this.email.remove(n);
		n++;
		if (this.email.size() == 0) {
			Toast.makeText(this, "Todos os nomes já foram sorteados!",
					Toast.LENGTH_SHORT).show();
			return;
		}
		startActivity(Intent.createChooser(emailIntent, "Send Email"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_email, menu);
		return true;
	}

}
