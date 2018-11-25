import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ApiService {
  baseUrl = 'https://jsonplaceholder.typicode.com';

  constructor(private http: HttpClient) { }

  getUsers() {
    return this.http.get(this.baseUrl + '/users');
  }

  getPosts() {
    return this.http.get(this.baseUrl + '/posts');
  }

  getPostById(postId) {
    return this.http.get(this.baseUrl + '/posts/' + postId);
  }

  addPost(post) {
    return this.http.post(this.baseUrl + '/posts/', post);
  }

  deletePost(postId) {
    return this.http.delete(this.baseUrl + '/posts/' + postId);
  }

  updatePost(post) {
    return this.http.put(this.baseUrl + '/posts/' + post.id, post);
  }

}
