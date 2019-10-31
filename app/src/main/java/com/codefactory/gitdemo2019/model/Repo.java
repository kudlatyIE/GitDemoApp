package com.codefactory.gitdemo2019.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_repo")
public class Repo {

    @PrimaryKey
    @NonNull
    private int id;

    @NonNull
    private String name;
    @NonNull
    private String full_name;

    private String description;
    @NonNull
    private String html_url;

    public Repo(int id, @NonNull String name, @NonNull String full_name, String description, @NonNull String html_url) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
        this.description = description;
        this.html_url = html_url;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getFull_name() {
        return full_name;
    }

    public String getDescription() {
        return description;
    }

    @NonNull
    public String getHtml_url() {
        return html_url;
    }

    @Override
    public String toString() {
        return name.concat(description);
    }
}

/*
{
        "id": 71135531,
        "node_id": "MDEwOlJlcG9zaXRvcnk3MTEzNTUzMQ==",
        "name": "kongfig",
        "full_name": "ApplauseOSS/kongfig",
        "private": false,
        "owner": {
            "login": "ApplauseOSS",
            "id": 20522207,
            "node_id": "MDEyOk9yZ2FuaXphdGlvbjIwNTIyMjA3",
            "avatar_url": "https://avatars1.githubusercontent.com/u/20522207?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/ApplauseOSS",
            "html_url": "https://github.com/ApplauseOSS",
            "followers_url": "https://api.github.com/users/ApplauseOSS/followers",
            "following_url": "https://api.github.com/users/ApplauseOSS/following{/other_user}",
            "gists_url": "https://api.github.com/users/ApplauseOSS/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/ApplauseOSS/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/ApplauseOSS/subscriptions",
            "organizations_url": "https://api.github.com/users/ApplauseOSS/orgs",
            "repos_url": "https://api.github.com/users/ApplauseOSS/repos",
            "events_url": "https://api.github.com/users/ApplauseOSS/events{/privacy}",
            "received_events_url": "https://api.github.com/users/ApplauseOSS/received_events",
            "type": "Organization",
            "site_admin": false
        },
        "html_url": "https://github.com/ApplauseOSS/kongfig",
        "description": "Declarative configuration for Kong",
        "fork": true,
        "url": "https://api.github.com/repos/ApplauseOSS/kongfig",
        "forks_url": "https://api.github.com/repos/ApplauseOSS/kongfig/forks",
        "keys_url": "https://api.github.com/repos/ApplauseOSS/kongfig/keys{/key_id}",
        "collaborators_url": "https://api.github.com/repos/ApplauseOSS/kongfig/collaborators{/collaborator}",
        "teams_url": "https://api.github.com/repos/ApplauseOSS/kongfig/teams",
        "hooks_url": "https://api.github.com/repos/ApplauseOSS/kongfig/hooks",
        "issue_events_url": "https://api.github.com/repos/ApplauseOSS/kongfig/issues/events{/number}",
        "events_url": "https://api.github.com/repos/ApplauseOSS/kongfig/events",
        "assignees_url": "https://api.github.com/repos/ApplauseOSS/kongfig/assignees{/user}",
        "branches_url": "https://api.github.com/repos/ApplauseOSS/kongfig/branches{/branch}",
        "tags_url": "https://api.github.com/repos/ApplauseOSS/kongfig/tags",
        "blobs_url": "https://api.github.com/repos/ApplauseOSS/kongfig/git/blobs{/sha}",
        "git_tags_url": "https://api.github.com/repos/ApplauseOSS/kongfig/git/tags{/sha}",
        "git_refs_url": "https://api.github.com/repos/ApplauseOSS/kongfig/git/refs{/sha}",
        "trees_url": "https://api.github.com/repos/ApplauseOSS/kongfig/git/trees{/sha}",
        "statuses_url": "https://api.github.com/repos/ApplauseOSS/kongfig/statuses/{sha}",
        "languages_url": "https://api.github.com/repos/ApplauseOSS/kongfig/languages",
        "stargazers_url": "https://api.github.com/repos/ApplauseOSS/kongfig/stargazers",
        "contributors_url": "https://api.github.com/repos/ApplauseOSS/kongfig/contributors",
        "subscribers_url": "https://api.github.com/repos/ApplauseOSS/kongfig/subscribers",
        "subscription_url": "https://api.github.com/repos/ApplauseOSS/kongfig/subscription",
        "commits_url": "https://api.github.com/repos/ApplauseOSS/kongfig/commits{/sha}",
        "git_commits_url": "https://api.github.com/repos/ApplauseOSS/kongfig/git/commits{/sha}",
        "comments_url": "https://api.github.com/repos/ApplauseOSS/kongfig/comments{/number}",
        "issue_comment_url": "https://api.github.com/repos/ApplauseOSS/kongfig/issues/comments{/number}",
        "contents_url": "https://api.github.com/repos/ApplauseOSS/kongfig/contents/{+path}",
        "compare_url": "https://api.github.com/repos/ApplauseOSS/kongfig/compare/{base}...{head}",
        "merges_url": "https://api.github.com/repos/ApplauseOSS/kongfig/merges",
        "archive_url": "https://api.github.com/repos/ApplauseOSS/kongfig/{archive_format}{/ref}",
        "downloads_url": "https://api.github.com/repos/ApplauseOSS/kongfig/downloads",
        "issues_url": "https://api.github.com/repos/ApplauseOSS/kongfig/issues{/number}",
        "pulls_url": "https://api.github.com/repos/ApplauseOSS/kongfig/pulls{/number}",
        "milestones_url": "https://api.github.com/repos/ApplauseOSS/kongfig/milestones{/number}",
        "notifications_url": "https://api.github.com/repos/ApplauseOSS/kongfig/notifications{?since,all,participating}",
        "labels_url": "https://api.github.com/repos/ApplauseOSS/kongfig/labels{/name}",
        "releases_url": "https://api.github.com/repos/ApplauseOSS/kongfig/releases{/id}",
        "deployments_url": "https://api.github.com/repos/ApplauseOSS/kongfig/deployments",
        "created_at": "2016-10-17T12:22:32Z",
        "updated_at": "2018-11-20T16:02:14Z",
        "pushed_at": "2018-11-20T16:02:12Z",
        "git_url": "git://github.com/ApplauseOSS/kongfig.git",
        "ssh_url": "git@github.com:ApplauseOSS/kongfig.git",
        "clone_url": "https://github.com/ApplauseOSS/kongfig.git",
        "svn_url": "https://github.com/ApplauseOSS/kongfig",
        "homepage": "",
        "size": 845,
        "stargazers_count": 0,
        "watchers_count": 0,
        "language": "JavaScript",
        "has_issues": false,
        "has_projects": true,
        "has_downloads": true,
        "has_wiki": false,
        "has_pages": false,
        "forks_count": 0,
        "mirror_url": null,
        "archived": false,
        "disabled": false,
        "open_issues_count": 0,
        "license": {
            "key": "mit",
            "name": "MIT License",
            "spdx_id": "MIT",
            "url": "https://api.github.com/licenses/mit",
            "node_id": "MDc6TGljZW5zZTEz"
        },
        "forks": 0,
        "open_issues": 0,
        "watchers": 0,
        "default_branch": "master",
        "permissions": {
            "admin": false,
            "push": false,
            "pull": true
        }
    },
 */
