package com.example.doppel_moppel.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.doppel_moppel.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnConfirm.setBackgroundColor(Color.GREEN);
        binding.btnConfirm.setOnClickListener(v -> enterParticipant());

        binding.btnDeleteParticipant.setBackgroundColor(Color.YELLOW);
        binding.btnDeleteParticipant.setOnClickListener(v -> updateList());

        binding.btnClear.setBackgroundColor(Color.RED);
        binding.btnClear.setOnClickListener(v -> clearInputs());
    }

    private void enterParticipant() {
        String inputParticipant = binding.inputParticipants.getEditableText().toString();
        if (!inputParticipant.isEmpty()) {
            binding.txtEnteredParticipants.append("\n- " + inputParticipant);
        }
    }

    private void updateList() {
        String participantToDelete = binding.inputParticipants.getEditableText().toString();
        String currentList = binding.txtEnteredParticipants.getEditableText().toString();
        ArrayList<String> tmp = new ArrayList<>();
        if (!participantToDelete.isEmpty() && !currentList.isEmpty()) {
            if (currentList.contains("- " + participantToDelete)) {
                String[] currentListSplit = currentList.split("\n");
                for (String participants : currentListSplit) {
                    if (!participants.equals("- " + participantToDelete)) {
                        tmp.add(participants);
                    }
                }
            }
            binding.txtEnteredParticipants.setText("");
            for (String participants : tmp) {
                if (!participants.isEmpty()) {
                    binding.txtEnteredParticipants.append("\n" + participants);
                }
            }
        }
    }

    private void clearInputs() {
        binding.txtEnteredParticipants.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}