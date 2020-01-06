package com.example.retrofitapk.ModelClasses.PostModel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;


//@Entity(tableName = "post_data_table", foreignKeys = {
//        @ForeignKey(
//                entity = Post.class,
//                parentColumns = "current_page",
//                childColumns = "post_page_id",
//                onUpdate = CASCADE
//        ),
//        @ForeignKey(
//                entity = Post.class,
//                parentColumns = "last_page",
//                childColumns = "post_last_page_id",
//                onUpdate = CASCADE
//        )
//})

//@Entity(tableName = "post_data_table",
//        foreignKeys = @ForeignKey(entity = Post.class,
//                parentColumns = "current_page",
//                childColumns = "post_page_id",
//                onUpdate = CASCADE))
@Entity(tableName = "post_data_table")
public class Datum {

    private Integer post_page_id;

    public Integer getPost_page_id() {
        return post_page_id;
    }

    public void setPost_page_id(Integer post_page_id) {
        this.post_page_id = post_page_id;
    }

    private Integer post_last_page_id;

    public Integer getPost_last_page_id() {
        return post_last_page_id;
    }

    public void setPost_last_page_id(Integer post_last_page_id) {
        this.post_last_page_id = post_last_page_id;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "post_id")
    public Integer id;
    @SerializedName("category_id")
    @Expose
    @Ignore
    public Integer categoryId;
    @SerializedName("sub_category_id")
    @Expose
    @Ignore
    public Integer subCategoryId;
    @SerializedName("language_id")
    @Expose
    @Ignore
    public Integer languageId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    @Ignore
    public String updatedAt;
    @SerializedName("post_content")
    @Expose
    public String postContent;
    @SerializedName("post_status")
    @Expose
    @Ignore
    public String postStatus;
    @SerializedName("post_like_count")
    @Expose
    public Integer postLikeCount;
    @SerializedName("post_comment_count")
    @Expose
    public Integer postCommentCount;
    @SerializedName("post_view_count")
    @Expose
    public Integer postViewCount;
    @SerializedName("post_whatsapp_counter")
    @Expose
    public Integer postWhatsappCounter;
    @SerializedName("media_content")
    @Expose
    public String mediaContent;
    @SerializedName("mediathumb")
    @Expose
    public String mediathumb;
    @SerializedName("userId")
    @Expose
    public String userId;
    @SerializedName("file_type")
    @Expose
    public String fileType;
    @SerializedName("mimetype")
    @Expose
    @Ignore
    public String mimetype;
    @SerializedName("user")
    @Expose
    @Embedded(prefix = "user_")
    public User user;
    @SerializedName("follow")
    @Expose
    @Ignore
    public List<Follow> follow = null;
    @SerializedName("downloaded")
    @Expose
    @Ignore
    public List<Downloaded> downloaded = null;
    @SerializedName("like")
    @Expose
    @Ignore
    public List<Like> like = null;
    @SerializedName("category")
    @Expose
    @Embedded(prefix = "category_")
    public Category category;
    @SerializedName("subcategory")
    @Expose
    @Ignore
    //@Embedded(prefix = "subcategory_")
    public Subcategory subcategory;

    public User getUser() {
        return user;
    }

    public Category getCategory() {
        return category;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPostContent() {
        return postContent;
    }

    public Integer getPostLikeCount() {
        return postLikeCount;
    }

    public Integer getPostCommentCount() {
        return postCommentCount;
    }

    public Integer getPostViewCount() {
        return postViewCount;
    }

    public Integer getPostWhatsappCounter() {
        return postWhatsappCounter;
    }

    public String getMediaContent() {
        return "https://rgyan.com/public/uploads/sposts/"+mediaContent;
    }

    public String getMediathumb() {
        return "https://rgyan.com/public/uploads/sposts/"+mediathumb;
    }

    public String getFileType() {
        return fileType;
    }

    public Integer getId() {
        return id;
    }

    public List<Follow> getFollow() {
        return follow;
    }




}
