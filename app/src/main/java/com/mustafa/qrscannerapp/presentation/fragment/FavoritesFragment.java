package com.mustafa.qrscannerapp.presentation.fragment;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.mustafa.qrscannerapp.R;
import com.mustafa.qrscannerapp.data.local.entity.QRCode;
import com.mustafa.qrscannerapp.presentation.adapters.QRCodeAdapter;
import com.mustafa.qrscannerapp.presentation.viewmodel.QRCodeViewModel;


public class FavoritesFragment extends Fragment {
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
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new QRCodeAdapter(new QRCodeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(QRCode qrCode) {
                showQRContentDialog(qrCode);
            }

            @Override
            public void onFavoriteClick(QRCode qrCode) {
                viewModel.toggleFavorite(qrCode);
            }
        });

        recyclerView.setAdapter(adapter);

        viewModel.getFavoriteQRCodes().observe(getViewLifecycleOwner(), qrCodes -> {
            adapter.setQRCodes(qrCodes);

            // إظهار رسالة إذا كانت القائمة فارغة
            TextView tvEmpty = view.findViewById(R.id.tvEmpty);
            if (tvEmpty != null) {
                tvEmpty.setVisibility(qrCodes.isEmpty() ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void showQRContentDialog(QRCode qrCode) {
        new AlertDialog.Builder(requireContext())
                .setTitle("QR Content")
                .setMessage(qrCode.getContent())
                .setPositiveButton("Copy", (dialog, which) -> {
                    copyToClipboard(qrCode.getContent());
                })
                .setNegativeButton("Close", null)
                .setNeutralButton("Share", (dialog, which) -> {
                    shareQRContent(qrCode.getContent());
                })
                .show();
    }

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("QR Content", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(requireContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
    }

    private void shareQRContent(String content) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, content);
        startActivity(Intent.createChooser(shareIntent, "Share QR Content"));
    }
}