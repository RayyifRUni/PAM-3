package id.ac.ub.papb.recycler1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    LayoutInflater inflater;
    Context _context;
    ArrayList<Mahasiswa> data;
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Mahasiswa mahasiswa);
    }

    public MahasiswaAdapter(Context _context, ArrayList<Mahasiswa> data, OnItemClickListener listener) {
        this._context = _context;
        this.data = data;
        this.listener = listener;
        this.inflater = LayoutInflater.from(this._context);
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        Mahasiswa mhs = data.get(position);
        holder.tvNim.setText(mhs.nim);
        holder.tvNama.setText(mhs.nama);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(mhs));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNim;
        TextView tvNama;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tvNim);
            tvNama = itemView.findViewById(R.id.tvNama);
        }
    }
}
