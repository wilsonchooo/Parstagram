package com.example.parstagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.w3c.dom.Text;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void clear(){
        posts.clear();
        notifyDataSetChanged();;
    }

    public void addAll(List<Post> postss){
        posts.addAll(postss);
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUsername;
        private ImageView IVimage;
        private TextView tvDescription;
        private Button btn;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvUsername= itemView.findViewById(R.id.tvUsername);
            IVimage=itemView.findViewById(R.id.IVImage);
            tvDescription=itemView.findViewById(R.id.ivDescription);
            btn=itemView.findViewById(R.id.ivLikeBtn);

        }

        public void bind(Post post) {
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            btn.setText(""+post.getLikes());
            ParseFile image = post.getImage();
            if(image !=null){
                Glide.with(context).load(post.getImage().getUrl()).into(IVimage);
            }
        }
    }
}
