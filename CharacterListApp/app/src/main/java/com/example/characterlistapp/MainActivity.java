package com.example.characterlistapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CharacterAdapter characterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the character list with at least 10 characters
        List<Character> characterList = new ArrayList<>();
        characterList.add(new Character("John Snow", "A member of the Night's Watch, later revealed to be a Targaryen.", R.drawable.john_snow));
        characterList.add(new Character("Daenerys Targaryen", "The Mother of Dragons and rightful heir to the Iron Throne.", R.drawable.daenerys_targaryen));
        characterList.add(new Character("Tyrion Lannister", "The witty and intelligent youngest son of Tywin Lannister.", R.drawable.tyrion_lannister));
        characterList.add(new Character("Arya Stark", "A skilled assassin seeking to avenge her family.", R.drawable.arya_stark));
        characterList.add(new Character("Sansa Stark", "The Lady of Winterfell and a survivor of great hardships.", R.drawable.sansa_stark));
        characterList.add(new Character("Cersei Lannister", "The ruthless Queen who seeks to protect her power.", R.drawable.cersei_lannister));
        characterList.add(new Character("Jaime Lannister", "The Kingslayer and skilled swordsman.", R.drawable.jaime_lannister));
        characterList.add(new Character("Bran Stark", "The Three-Eyed Raven with the power to see the past and future.", R.drawable.bran_stark));
        characterList.add(new Character("Jorah Mormont", "A loyal companion to Daenerys Targaryen.", R.drawable.jorah_mormont));
        characterList.add(new Character("The Night King", "The leader of the White Walkers, a force of evil.", R.drawable.the_night_king));


        // Set up the adapter
        characterAdapter = new CharacterAdapter(characterList);
        recyclerView.setAdapter(characterAdapter);

        // Initialize the SearchView and set up search filtering
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                characterAdapter.filter(query); // Apply filter on submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                characterAdapter.filter(newText); // Apply filter as text changes
                return false;
            }
        });
    }
}
