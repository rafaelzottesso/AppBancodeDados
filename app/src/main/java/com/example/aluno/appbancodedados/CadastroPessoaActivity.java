package com.example.aluno.appbancodedados;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.util.List;

public class CadastroPessoaActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private Button cadastrar;

    private TextView pessoas;
    private Button listarPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);

        //SugarContext.init(getApplicationContext());
        //SchemaGenerator schemaGenerator = new SchemaGenerator(this);
        //schemaGenerator.createDatabase(new SugarDb(this).getDB());

        nome = (EditText) findViewById(R.id.editTextNome);
        email = (EditText) findViewById(R.id.editTextEmail);

        cadastrar = (Button) findViewById(R.id.buttonCadastrar);

        pessoas = (TextView) findViewById(R.id.textViewPessoas);
        listarPessoas = (Button) findViewById(R.id.buttonListar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Receber os dados dos campos de texto
                String n = nome.getText().toString();
                String e = email.getText().toString();

                // Cria um objeto de pessoa
                Pessoa p = new Pessoa();
                p.setNome(n);
                p.setEmail(e);

                // Salva no banco de dados
                p.save();

                // Limpa os campos
                nome.setText("");
                email.setText("");

            }
        });

        listarPessoas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Lista todas as pessoas
                List<Pessoa> listaDePessoas = Pessoa.listAll(Pessoa.class);

                // Cria uma String para concatenar o nome das pessoas
                String lista = "";

                // Faz um foreach nessa lista de pessoas
                for (Pessoa p: listaDePessoas ) {

                    lista = lista + p.getNome() + "\n";
                    
                }

                // Coloca a lista de pessoas no TextView
                pessoas.setText(lista);

            }
        });

    }
}
