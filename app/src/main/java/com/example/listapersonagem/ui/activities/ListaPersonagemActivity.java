package com.example.listapersonagem.ui.activities;

//Importando as referencias

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.example.listapersonagem.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.listapersonagem.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Lista de Personagem";
    private final PersonagemDAO dao = new PersonagemDAO();
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        //substitui titulo
        setTitle(TITLE_APPBAR);
        botaoFAB();
        configuraLista();
    }

    @Override
    protected void onResume() {

        //resume app
        super.onResume();
        atualizaPersonagem();
    }

    private void atualizaPersonagem() {
        //clean list
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void remove(Personagem personagem) {
        dao.remove(personagem);
        adapter.remove(personagem);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Remover");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Personagem personagemEscolhido = adapter.getItem(menuInfo.position);

        //remove o item
        remove(personagemEscolhido);
        return super.onContextItemSelected(item);
    }

    private void configuraLista() {
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        listaDePersonagem(listaDePersonagens);
        configuraItemPerClique(listaDePersonagens);
        registerForContextMenu(listaDePersonagens);
    }


    private void configuraItemPerClique(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //Sobrescrevendo o metodo
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                //Adicionando ao list
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(position);

                //Criando internet para activit
                abreFormularioModoEditar(personagemEscolhido);
            }
        });
    }


    private void abreFormularioModoEditar(Personagem personagem) {
        Intent vaiParaFormulario = new Intent(this, FormularioPersonagemActivity.class);
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagem);
        startActivity(vaiParaFormulario);
    }

    private void listaDePersonagem(ListView listaDePersonagens) {
        //cria um adapter com a referencia da lista
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagens.setAdapter(adapter);
    }

    //cria funcao ao botao
    private void botaoFAB() {
        //cahma metodo
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_novo_personagem);

        //verifica click
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Troca para o Formulario do personagem
                abreFormularioSalvar();
            }
        });
    }

    private void abreFormularioSalvar() {

         //Activit passa ser Formulario do personagem
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }
}


