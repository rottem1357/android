package com.example.characterlistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private final List<Character> characterList;
    private final List<Character> characterListFull; // To keep a copy of the full list for filtering

    // Constructor
    public CharacterAdapter(List<Character> characterList) {
        this.characterList = characterList;
        this.characterListFull = new ArrayList<>(characterList); // Create a copy of the full list
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_list_item, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.characterName.setText(character.getName());
        holder.characterDescription.setText(character.getDescription());
        holder.characterImage.setImageResource(character.getImageResourceId());

        // Add click listener for itemView
        holder.itemView.setOnClickListener(v -> {
            // Display Toast with the character's name when clicked
            Toast.makeText(v.getContext(), "Clicked: " + character.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    // ViewHolder class for RecyclerView
    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView characterName;
        TextView characterDescription;
        ImageView characterImage;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            characterName = itemView.findViewById(R.id.character_name);
            characterDescription = itemView.findViewById(R.id.character_description);
            characterImage = itemView.findViewById(R.id.character_image);
        }
    }

    // Filter method
    public void filter(String text) {
        List<Character> filteredList = new ArrayList<>();
        if (text.isEmpty()) {
            filteredList.addAll(characterListFull); // No filter applied, show full list
        } else {
            String filterPattern = text.toLowerCase().trim();
            for (Character character : characterListFull) {
                if (character.getName().toLowerCase().contains(filterPattern)) {
                    filteredList.add(character);
                }
            }
        }

        // Calculate changes in the list and apply efficient notifications
        applyListChanges(filteredList);
    }

    private void applyListChanges(List<Character> newList) {
        // Compare the current list to the new filtered list
        for (int i = characterList.size() - 1; i >= 0; i--) {
            if (!newList.contains(characterList.get(i))) {
                // Remove items that are no longer in the filtered list
                characterList.remove(i);
                notifyItemRemoved(i);
            }
        }

        for (int i = 0; i < newList.size(); i++) {
            if (!characterList.contains(newList.get(i))) {
                // Add new items to the filtered list
                characterList.add(i, newList.get(i));
                notifyItemInserted(i);
            }
        }
    }
}
