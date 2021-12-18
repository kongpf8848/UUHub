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
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("gravatar_id")
    private String gravatarId;
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
        dest.writeString(this.login);
        dest.writeLong(this.id);
        dest.writeString(this.nodeId);
        dest.writeString(this.avatarUrl);
        dest.writeString(this.gravatarId);
        dest.writeString(this.url);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.followersUrl);
        dest.writeString(this.followingUrl);
        dest.writeString(this.gistsUrl);
        dest.writeString(this.starredUrl);
        dest.writeString(this.subscriptionsUrl);
        dest.writeString(this.organizationsUrl);
        dest.writeString(this.reposUrl);
        dest.writeString(this.eventsUrl);
        dest.writeString(this.receivedEventsUrl);
        dest.writeString(this.type);
        dest.writeString(this.name);
        dest.writeString(this.company);
        dest.writeString(this.blog);
        dest.writeString(this.location);
        dest.writeString(this.email);
        dest.writeString(this.bio);
        dest.writeLong(this.publicRepos);
        dest.writeLong(this.publicGists);
        dest.writeLong(this.followers);
        dest.writeLong(this.following);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    protected User(Parcel in) {
        this.login = in.readString();
        this.id = in.readLong();
        this.nodeId=in.readString();
        this.avatarUrl = in.readString();
        this.gravatarId=in.readString();
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public int getPublicGists() {
        return publicGists;
    }

    public void setPublicGists(int publicGists) {
        this.publicGists = publicGists;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}