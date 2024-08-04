package com.example.doppel_moppel.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.doppel_moppel.databinding.FragmentHomeBinding;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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

        binding.btnImport.setBackgroundColor(Color.CYAN);
        binding.btnImport.setOnClickListener(v -> readFromFile(this.requireContext()));

        binding.btnExport.setBackgroundColor(Color.CYAN);
        binding.btnExport.setOnClickListener(v -> writeToFile(this.requireContext()));
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

    private void writeToFile(Context context) {
        try {
            String data = binding.txtEnteredParticipants.getEditableText().toString();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        if (!ret.isEmpty()) {
            binding.txtEnteredParticipants.setText(ret);
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