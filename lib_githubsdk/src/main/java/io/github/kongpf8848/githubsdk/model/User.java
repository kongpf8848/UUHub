package io.github.kongpf8848.githubsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 {
     "login": "kongpf8848",
     "id": 25379134,
     "node_id": "MDQ6VXNlcjI1Mzc5MTM0",
     "avatar_url": "https://avatars.githubusercontent.com/u/25379134?v=4",
     "gravatar_id": "",
     "url": "https://api.github.com/users/kongpf8848",
     "html_url": "https://github.com/kongpf8848",
     "followers_url": "https://api.github.com/users/kongpf8848/followers",
     "following_url": "https://api.github.com/users/kongpf8848/following{/other_user}",
     "gists_url": "https://api.github.com/users/kongpf8848/gists{/gist_id}",
     "starred_url": "https://api.github.com/users/kongpf8848/starred{/owner}{/repo}",
     "subscriptions_url": "https://api.github.com/users/kongpf8848/subscriptions",
     "organizations_url": "https://api.github.com/users/kongpf8848/orgs",
     "repos_url": "https://api.github.com/users/kongpf8848/repos",
     "events_url": "https://api.github.com/users/kongpf8848/events{/privacy}",
     "received_events_url": "https://api.github.com/users/kongpf8848/received_events",
     "type": "User",
     "site_admin": false,
     "name": "Jack kong",
     "company": null,
     "blog": "",
     "location": "china shanghai",
     "email": "kongpf8848@gmail.com",
     "hireable": null,
     "bio": "Android developer",
     "twitter_username": null,
     "public_repos": 24,
     "public_gists": 0,
     "followers": 17,
     "following": 0,
     "created_at": "2017-01-27T02:31:47Z",
     "updated_at": "2021-12-04T11:19:02Z",
     "private_gists": 0,
     "total_private_repos": 1,
     "owned_private_repos": 1,
     "disk_usage": 230831,
     "collaborators": 0,
     "two_factor_authentication": false,
     "plan": {
     "name": "free",
     "space": 976562499,
     "collaborators": 0,
     "private_repos": 10000
 }
 }
 */

public class User implements Parcelable {

    private String login;
    private Long id;
    private String node_id;
    @SerializedName("avatar_url")
    private String avatarUrl;
    private String url;
    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("followers_url")
    private String followersUrl;
    @SerializedName("following_url")
    private String followingUrl;

    @SerializedName("gists_url")
    private String gistsUrl;
    @SerializedName("starred_url")
    private String starredUrl;
    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;
    @SerializedName("organizations_url")
    private String organizationsUrl;
    @SerializedName("repos_url")
    private String reposUrl;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("received_events_url")
    private String receivedEventsUrl;
    private String type;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String bio;
    @SerializedName("public_repos")
    private int publicRepos;
    @SerializedName("public_gists")
    private int publicGists;
    private int followers;
    private int following;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);

    }

    protected User(Parcel in) {
        this.login = in.readString();
        this.id = in.readLong();
        this.node_id=in.readString();
        this.avatarUrl = in.readString();
        this.url=in.readString();
        this.htmlUrl = in.readString();
        this.followersUrl = in.readString();
        this.followingUrl=in.readString();
        this.gistsUrl=in.readString();
        this.starredUrl=in.readString();
        this.subscriptionsUrl=in.readString();
        this.organizationsUrl=in.readString();
        this.reposUrl=in.readString();
        this.eventsUrl=in.readString();
        this.receivedEventsUrl=in.readString();
        this.type=in.readString();
        this.name = in.readString();
        this.company = in.readString();
        this.blog=in.readString();
        this.location=in.readString();
        this.email=in.readString();
        this.bio=in.readString();
        this.publicRepos = in.readInt();
        this.publicGists=in.readInt();
        this.followers=in.readInt();
        this.following=in.readInt();
        this.createdAt=in.readString();
        this.updatedAt=in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            return ((User)obj).id.equals(id);
        }
        return super.equals(obj);
    }
}