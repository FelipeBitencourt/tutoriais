package br.bitencourt.amigooculto;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
	List<String> nome;
	List<String> email;
	List<String> clone;
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
		nome = new LinkedList<String>();
		email = new LinkedList<String>();
		clone = new LinkedList<String>();

		edit_nome = (EditText) findViewById(R.id.editText1);
		edit_email = (EditText) findViewById(R.id.editText2);
	}

	public void onClickCadastra(View v) {
		if (x >= amg) {
			Toast.makeText(this, "Todos os emails foram cadastrados!",
					Toast.LENGTH_SHORT).show();
			return;
		}

		text_enviado = (TextView) findViewById(R.id.enviado);

		text.setText(Integer.toString(this.amg));
		nome.add(edit_nome.getText().toString());
		clone.add(edit_nome.getText().toString());
		email.add(edit_email.getText().toString());

		edit_nome.setText("");
		edit_email.setText("");

		x++;

		text_enviado.setText(Integer.toString(x));
		if (x == amg) {
			/*
			 * sorteador = new ArrayList<Integer>(); for (int i = 0; i <
			 * nome.size(); i++) { sorteador.add(i); }
			 */
			long seed = System.nanoTime();
			System.out.println("Nomes antes do shuffle: " + nome);
			Collections.shuffle(nome);

			System.out.println("Nomes depois do shuffle: " + nome);
		}
	}

	public void onClickSortear(View v) {

		if (email.isEmpty()) {
			Toast.makeText(this, "Todos os nomes já foram sorteados!",
					Toast.LENGTH_SHORT).show();
			return;
		}
		if (nome.size() != 1) {
			while (nome.get(0).equals(clone.get(0))) {
				Collections.shuffle(nome);
				System.out.println("Realizou uma troca!");
			}
		}

		edit_nome.setEnabled(false);
		edit_email.setEnabled(false);

		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

		String[] recipients = new String[] { email.get(0) };
		emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Amigo Oculto");
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Seu amigo secreto é o(a) "
				+ nome.get(0));
		emailIntent.setType("plain/text");
		System.out.println("Nomes antes da exclusão: " + nome);
		startActivity(Intent.createChooser(emailIntent, "Send Email"));
		nome.remove(0);
		email.remove(0);
		System.out.println("Nomes depois da exclusão: " + nome);
		System.out.println("CLONE: " + clone);
		return;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_email, menu);
		return true;
	}

}