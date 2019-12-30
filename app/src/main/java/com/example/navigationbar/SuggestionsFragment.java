package com.example.navigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SuggestionsFragment extends Fragment {
    private DocumentReference docRef;

    public EditText sugTyped;
    private int key = -1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            key = -1;
        } else {
            key = savedInstanceState.getInt("Key", -1);
        }

        View view = inflater.inflate(R.layout.fragment_suggestions, container, false);

        final Button btnSendSuggest = view.findViewById(R.id.BtnSuggestionSend);
        sugTyped = view.findViewById(R.id.editTextSugest);

        btnSendSuggest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                docRef = FirebaseFirestore.getInstance().document("Suggestions/Latest");
                key++;

                String keyP = String.valueOf(key);

                Map<String, Object> dataToSave = new HashMap<>();
                String suggestion = sugTyped.getText().toString();
                dataToSave.put(keyP, suggestion);
                Toast.makeText(getActivity(), "Key " + keyP, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Suggestion Sent!", Toast.LENGTH_SHORT).show();

                docRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Thank You :)", Toast.LENGTH_SHORT).show();
                        sugTyped.getText().clear();
                    }

                });

            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Key", key);
    }
}

