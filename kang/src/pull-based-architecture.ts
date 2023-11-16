class PullBasedWebSite {
  constructor(private url: string) {}
  getUrl() { return this.url; }
}

class PullBasedUser {
  constructor(private username: string)  {}
  getUsername() { return this.username; }
}

class PullBasedBlogPost {
  constructor(private author: PullBasedUser, private id: string) {}
  getId() { return this.id; }
  getAuthor() { return this.author; }
}

function PullBasedGeneratePostLink(website: PullBasedWebSite, post: PullBasedBlogPost) {
  let url = website.getUrl();
  let user = post.getAuthor();
  let name = user.getUsername();
  let postId = post.getId();
  return url + name + postId;
}
