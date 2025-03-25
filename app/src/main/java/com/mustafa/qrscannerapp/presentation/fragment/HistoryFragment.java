package com.mustafa.qrscannerapp.presentation.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mustafa.qrscannerapp.R;
import com.mustafa.qrscannerapp.data.local.entity.QRCode;
import com.mustafa.qrscannerapp.presentation.adapters.QRCodeAdapter;
import com.mustafa.qrscannerapp.presentation.viewmodel.QRCodeViewModel;


public class HistoryFragment extends Fragment {
    private QRCodeViewModel viewModel;
    private QRCodeAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(QRCodeViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new QRCodeAdapter(new QRCodeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(QRCode qrCode) {
                // Handle item click (e.g., open URL if it's a link)
            }

            @Override
            public void onFavoriteClick(QRCode qrCode) {
                viewModel.toggleFavorite(qrCode);
            }
        });
        recyclerView.setAdapter(adapter);

        viewModel.getAllQRCodes().observe(getViewLifecycleOwner(), qrCodes -> {
            adapter.setQRCodes(qrCodes);
        });
    }
}