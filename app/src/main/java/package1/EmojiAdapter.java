package package1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newkeyboard2.R;

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder> {

    private List<String> EmojiList;
    private LayoutInflater inflater;
    private OnEmojiClickListener listener; // Add a member for the listener

    public interface OnEmojiClickListener {
        void onEmojiClick(String emoji);
    }

    // Update the constructor to accept the listener
    public EmojiAdapter(List<String> EmojiList, LayoutInflater inflater, OnEmojiClickListener listener) {
        this.EmojiList = EmojiList;
        this.inflater = inflater;
        this.listener = listener; // Assign the listener
    }

    @NonNull
    @Override
    public EmojiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.emoji_item, parent, false);
        return new EmojiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmojiViewHolder holder, int position) {
        String emoji = EmojiList.get(position);

        // Bind the emoji to the TextView in the ViewHolder
        holder.EmojiText.setText(emoji);

        // Set the click listener for the emoji
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEmojiClick(emoji); // Notify the listener when an emoji is clicked
            }
        });
    }

    @Override
    public int getItemCount() {
        return EmojiList.size();

    }

    public void getEmojiToFront(String emoji){
        int index = EmojiList.indexOf(emoji);

        EmojiList.remove(index);
        EmojiList.add(0, emoji);

        notifyItemMoved(index, 0); // Move the emoji from its old position to the front
        notifyItemRangeChanged(1, EmojiList.size() - 1); // Update other items
    }

    static class EmojiViewHolder extends RecyclerView.ViewHolder {
        TextView EmojiText;

        public EmojiViewHolder(@NonNull View itemView) {
            super(itemView);
            EmojiText = itemView.findViewById(R.id.IdEmojiText);
        }
    }
}
