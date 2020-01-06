package com.example.retrofitapk.Adapters;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitapk.ModelClasses.PostModel.Datum;
import com.example.retrofitapk.ModelClasses.PostModel.Post;
import com.example.retrofitapk.MySpannable;
import com.example.retrofitapk.R;
import com.example.retrofitapk.ShowPostActivity;
import com.example.retrofitapk.VideoActivity;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;

public class PostDataAdapter extends RecyclerView.Adapter<PostDataAdapter.DataViewHolder> {

    private int lastPosition = -1;
    private Post post;
    private List<Datum> dataList;
    public SimpleExoPlayer player;
    public int pageId=1;
    ShowPostActivity activity;
    public boolean loading = false;
    int loadPage = 0;

    public PostDataAdapter(ShowPostActivity activity, Post post) {
        this.dataList = post.getData();
        this.activity = activity;
        this.post = post;
    }

    @Override
    public PostDataAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.post_list_item, null);
        PostDataAdapter.DataViewHolder dataViewHolder = new PostDataAdapter.DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        if (position == dataList.size()-1){
            loading = true;
            Toast.makeText(activity, "The last item position is-"+position, Toast.LENGTH_SHORT).show();
            try {
                Log.e("PostPage", ": "+post.getCurrentPage());
                holder.progressBar.setVisibility(View.VISIBLE);
                holder.progressBar.setIndeterminate(true);
                if (post.getCurrentPage()== post.getLastPage()){
                    Toast.makeText(activity, "All posts are loaded.", Toast.LENGTH_SHORT).show();
                    holder.progressBar.setVisibility(View.GONE);
                    holder.progressBar.setIndeterminate(true);
                    loading = false;
                }else {
                    int lastVisibleItem = activity.lastVisiblePosition;
                    Log.e("PostDataAdapter", "onAvailable, visible item- "+lastVisibleItem);
                    if (activity.isConnected && loading && position==dataList.size()-1 && post.getCurrentPage()<post.getLastPage()){
                        Log.e("PostDataAdapter", "onAvailable loading after post id- "+dataList.get(dataList.size()-1).getId());
                        pageId = post.getCurrentPage()+1;
                        loading = true;
                        Toast.makeText(activity, "Posts loading for page-"+pageId, Toast.LENGTH_SHORT).show();
                        activity.loadPost(pageId);
                    }else {
                        Log.e("PostDataAdapter", "onAvailable notLoading post after post id- "+dataList.get(dataList.size()-1).getId());
                    }
                }
            }catch (NullPointerException e){}
        }else {
            loading = false;
        }

        if (!loading){
            holder.progressBar.setVisibility(View.GONE);
            holder.progressBar.setIndeterminate(false);
        }

        Animation animation = AnimationUtils.loadAnimation(activity,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;

        try {
            holder.userName.setText(dataList.get(position).getUser().getName());
            holder.userStatus.setText(dataList.get(position).getUser().getStatus());
            String postContent = dataList.get(position).getPostContent();
            if (postContent.equals("")||postContent.equals(null)){
                holder.postContent.setVisibility(View.GONE);
            }else {
                holder.postContent.setVisibility(View.VISIBLE);

            }
            holder.postContent.setText(postContent);
            if (postContent.length()>200){
                holder.postContent.setMinLines(3);
                makeTextViewResizable(holder.postContent, 3, "See More", true);
            }


            //holder.postContent.setText(postContent);
            holder.categoryEn.setText(dataList.get(position).getCategory().getCategoryEn());
            holder.createdAt.setText(dataList.get(position).getCreatedAt());
            holder.whatsappCounter.setText(String.valueOf(dataList.get(position).getPostWhatsappCounter()));
            holder.likeCount.setText(String.valueOf(dataList.get(position).getPostLikeCount()));
            holder.commentCount.setText(String.valueOf(dataList.get(position).getPostCommentCount()));
            holder.viewCount.setText(String.valueOf(dataList.get(position).getPostViewCount()));


            Glide.with(activity)
                    .load(dataList.get(position).getMediaContent())
                    .apply(fitCenterTransform()).into(holder.mediaContent);
            Glide.with(activity).load(dataList.get(position).getUser().getAvatar()).apply(fitCenterTransform()).into(holder.userAvatar);
            String fileType = dataList.get(position).getFileType();
            if (fileType.contains("2")){
                holder.playBtn.setVisibility(View.VISIBLE);
                Glide.with(activity).load(dataList.get(position).getMediathumb()).into(holder.mediaContent);

                holder.playBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        Intent videoIntent = new Intent(activity, VideoActivity.class);
//                        videoIntent.putExtra("Post_ID", dataList.get(position).getId());
//                        videoIntent.putExtra("Post_Content", dataList.get(position).getPostContent());
//                        videoIntent.putExtra("Post_Video_Url", dataList.get(position).getMediaContent());
//
//                        activity.startActivity(videoIntent);

                        holder.playerView.setVisibility(View.VISIBLE);
                        holder.playBtn.setVisibility(View.INVISIBLE);
                        holder.mediaContent.setVisibility(View.INVISIBLE);

                        player = (SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(activity);
                        holder.playerView.setPlayer(player);

                        player.setPlayWhenReady(true);

                        Uri uri = Uri.parse(dataList.get(position).getMediaContent());
                        MediaSource mediaSource = buildMediaSource(uri);
                        player.prepare(mediaSource, true, false);

                        holder.playerView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                            @Override
                            public void onViewAttachedToWindow(View v) {
                                player.setPlayWhenReady(true);
                            }

                            @Override
                            public void onViewDetachedFromWindow(View v) {
                                player.setPlayWhenReady(false);

                            }
                        });
                    }
                });

            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    public void addPost(Post post){
        this.post = post;
        int index = dataList.size();
        Log.e("Adapter", "Index-"+index+ ", CurrentPage-"+post.getCurrentPage()+", postID- "+post.getData().get(0).getId());
        dataList.addAll(index, post.getData());
        notifyDataSetChanged();
        loadPage = 0;
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
    }

    @Override
    public int getItemCount() {
        return dataList.size(); // size of the list items
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull DataViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView userName, userStatus, postContent, categoryEn, createdAt, whatsappCounter, likeCount, commentCount, viewCount;
        ImageView mediaContent, playBtn;
        CircleImageView userAvatar;
        PlayerView playerView;
        ProgressBar progressBar;

        private DataViewHolder(final View itemView) {
            super(itemView);

            userAvatar = itemView.findViewById(R.id.user_avatar);
            mediaContent = itemView.findViewById(R.id.media_content);
            userName = itemView.findViewById(R.id.user_name);
            userStatus = itemView.findViewById(R.id.user_status);
            postContent = itemView.findViewById(R.id.post_content);
            categoryEn = itemView.findViewById(R.id.category_en);
            createdAt = itemView.findViewById(R.id.created_at);
            whatsappCounter = itemView.findViewById(R.id.whatsapp_counter);
            likeCount = itemView.findViewById(R.id.like_count);
            commentCount = itemView.findViewById(R.id.comment_count);
            viewCount = itemView.findViewById(R.id.view_count);
            playBtn = itemView.findViewById(R.id.play_btn);

            playerView = itemView.findViewById(R.id.post_video);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

    }

    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {


            ssb.setSpan(new MySpannable(false){
                @Override
                public void onClick(View widget) {
                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "See Less", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 3, ".. See More", true);
                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }

}