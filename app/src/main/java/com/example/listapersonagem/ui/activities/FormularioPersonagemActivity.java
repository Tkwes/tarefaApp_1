package com.example.listapersonagem.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.example.listapersonagem.model.Personagem;

import static com.example.listapersonagem.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    //Criando variaveis Constantes

    private static final String TITLE_APPBAR_EDITA_PERSONAGEM = "Editar Personagem";
    private static final String TITLE_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";

    //criando EditText (variavel)
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;

    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        inicializacaoCampos();

        //Configura o botao Salvar
        configuraBotao();
        carregaPersonagem();
    }

    private void carregaPersonagem() {

        //pega o internet e joga na variavel
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {

            //altera titulo
            setTitle(TITLE_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);

            //atribui edittext
            preencheCampos();
        } else {

            //altera titulo
            setTitle(TITLE_APPBAR_NOVO_PERSONAGEM);

            //cria um personagem se necessario
            personagem = new Personagem();
        }
    }

    private void preencheCampos() {
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }

    private void configuraBotao() {

        //Atribuie funcao do botao
        Button botaoSalvar = findViewById(R.id.button_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                //Salva/edita  formulario
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        preenchePersonagem();
        //validacao

        if (personagem.IdValido()) {

            //informacoes editadas
            dao.edita(personagem);
        } else {

            //Salvando detalhes glovais do personagens

            dao.salva(personagem);
        }
        //stop metodo
        finish();
    }

    private void inicializacaoCampos() {

        //refereviais do atctive as variaves

        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);
        campoNascimento = findViewById(R.id.editText_nascimento);
    }

    private void preenchePersonagem() {

        //atribuido variaveis a propriedades globais
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();

        //atrelando variaves a propriedade correta
        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
    }
}