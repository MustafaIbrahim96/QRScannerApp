package com.mustafa.qrscannerapp.presentation.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.qrscannerapp.R;
import com.mustafa.qrscannerapp.data.local.entity.QRCode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QRCodeAdapter extends RecyclerView.Adapter<QRCodeAdapter.QRCodeViewHolder> {
    private List<QRCode> qrCodes;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(QRCode qrCode);
        void onFavoriteClick(QRCode qrCode);
    }

    public QRCodeAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setQRCodes(List<QRCode> qrCodes) {
        this.qrCodes = qrCodes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QRCodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_qr_code, parent, false);
        return new QRCodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QRCodeViewHolder holder, int position) {
        QRCode qrCode = qrCodes.get(position);

        if (isUrl(qrCode.getContent())) {
            holder.tvContent.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.holo_blue_dark));
            holder.tvContent.setPaintFlags(holder.tvContent.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        } else {
            holder.tvContent.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.primary_text_dark));
            holder.tvContent.setPaintFlags(holder.tvContent.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        }

        holder.bind(qrCode, listener);
    }

    @Override
    public int getItemCount() {
        return qrCodes != null ? qrCodes.size() : 0;
    }

    private boolean isUrl(String content) {
        return content != null && (content.startsWith("http://") || content.startsWith("https://"));
    }

    static class QRCodeViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvContent;
        private final TextView tvTimestamp;
        private final ImageButton btnFavorite;

        public QRCodeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
        }

        public void bind(QRCode qrCode, OnItemClickListener listener) {
            tvContent.setText(qrCode.getContent());
            tvTimestamp.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    .format(new Date(qrCode.getTimestamp())));


            updateFavoriteIcon(qrCode.isFavorite());

            itemView.setOnClickListener(v -> listener.onItemClick(qrCode));
            btnFavorite.setOnClickListener(v -> {
                listener.onFavoriteClick(qrCode);
                updateFavoriteIcon(!qrCode.isFavorite());
            });
        }

        private void updateFavoriteIcon(boolean isFavorite) {
            btnFavorite.setImageResource(isFavorite ?
                    android.R.drawable.star_big_on : android.R.drawable.star_big_off);
            btnFavorite.setContentDescription(isFavorite ?
                    "Remove from favorites" : "Add to favorites");
        }
    }
}
