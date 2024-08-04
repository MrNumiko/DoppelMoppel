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

        binding.btnUpdateList.setBackgroundColor(Color.YELLOW);

        binding.btnClear.setBackgroundColor(Color.RED);
        binding.btnClear.setOnClickListener(v -> clearInputs());
    }

    private void enterParticipant() {
        String inputParticipant = binding.inputParticipants.getEditableText().toString();
        if (!inputParticipant.isEmpty()) {
            binding.txtEnteredParticipants.append("\n" + inputParticipant);
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