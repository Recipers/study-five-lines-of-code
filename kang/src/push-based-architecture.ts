class PushBasedWebSite {
  constructor(private url: string) {}
  generateLink(name: string, id: string) {
    return this.url + name + id;
  }
}

class PushBasedUser {
  constructor(private username: string)  {}
  generateLink(website: PushBasedWebSite, id: string) {
    return website.generateLink(this.username, id);
  }
}

class PushBasedBlogPost {
  constructor(private author: PushBasedUser, private id: string) {}
  generateLink(website: PushBasedWebSite) {
    return this.author.generateLink(website, this.id);
  }
}

function PushBasedGeneratePostLink(website: PushBasedWebSite, post: PushBasedBlogPost) {
  return post.generateLink(website);
}
